package es.arturonb.daos;

import es.arturonb.modelos.Wantlist;
import es.arturonb.servicios.Transacciones;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.concurrent.atomic.AtomicReference;

@Repository
public class WantlistsDAOImpl implements WantlistsDAO {
    @Autowired
    private Transacciones tr;

    @Override
    public Wantlist getWantlistById(int id) {
        AtomicReference<Wantlist> wantlist = new AtomicReference<>();

        tr.inTransaction(entityManager -> {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Wantlist> cq = cb.createQuery(Wantlist.class);
            Root<Wantlist> root = cq.from(Wantlist.class);
            cq.select(root).where(cb.equal(root.get("id"), id));
            wantlist.set(entityManager.createQuery(cq).getSingleResult());
        });

        return wantlist.get();
    }

    @Override
    public boolean insert(Wantlist wantlist) {
        return tr.inTransaction(entityManager -> {
            entityManager.persist(wantlist);
        });
    }

    @Override
    public boolean update(Wantlist wantlist) {
        return tr.inTransaction(entityManager -> {
            entityManager.merge(wantlist);
        });
    }

    @Override
    public boolean remove(Wantlist wantlist) {
        return tr.inTransaction(entityManager -> {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaDelete<Wantlist> cd = cb.createCriteriaDelete(Wantlist.class);
            Root<Wantlist> root = cd.from(Wantlist.class);
            cd.where(cb.equal(root.get("id"), wantlist.getId()));
            entityManager.createQuery(cd).executeUpdate();
        });
    }
}
