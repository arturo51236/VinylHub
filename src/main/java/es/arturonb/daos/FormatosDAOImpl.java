package es.arturonb.daos;

import es.arturonb.modelos.Formato;
import es.arturonb.servicios.Transacciones;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Repository
public class FormatosDAOImpl implements FormatosDAO {
    @Autowired
    private Transacciones tr;

    @Override
    public List<Formato> getFormatos() {
        List<Formato> arrFormatos = new ArrayList<>();

        tr.inTransaction(entityManager -> {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Formato> cq = cb.createQuery(Formato.class);
            cq.select(cq.from(Formato.class));
            arrFormatos.addAll(entityManager.createQuery(cq).getResultList());
        });

        return arrFormatos;
    }

    @Override
    public Formato getFormatoById(int id) {
        AtomicReference<Formato> formato = new AtomicReference<>();

        tr.inTransaction(entityManager -> {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Formato> cq = cb.createQuery(Formato.class);
            Root<Formato> root = cq.from(Formato.class);
            cq.select(root).where(cb.equal(root.get("id"), id));
            formato.set(entityManager.createQuery(cq).getSingleResult());
        });

        return formato.get();
    }

    @Override
    public Formato getFormatoByNombre(String nombre) {
        AtomicReference<Formato> formato = new AtomicReference<>();

        tr.inTransaction(entityManager -> {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Formato> cq = cb.createQuery(Formato.class);
            Root<Formato> root = cq.from(Formato.class);
            cq.select(root).where(cb.equal(root.get("nombre"), nombre));
            formato.set(entityManager.createQuery(cq).getSingleResult());
        });

        return formato.get();
    }

    @Override
    public int getMostRecentIdFormatoInsertion() {
        AtomicInteger latestId = new AtomicInteger();

        tr.inTransaction(entityManager -> {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Formato> cq = cb.createQuery(Formato.class);
            Root<Formato> root = cq.from(Formato.class);
            cq.orderBy(cb.desc(root.get("id")));
            latestId.set(entityManager.createQuery(cq).setMaxResults(1).getSingleResult().getId());
        });

        return latestId.get();
    }

    @Override
    public boolean insert(Formato formato) {
        return tr.inTransaction(entityManager -> {
            entityManager.persist(formato);
        });
    }

    @Override
    public boolean update(Formato formato) {
        return tr.inTransaction(entityManager -> {
            entityManager.merge(formato);
        });
    }

    @Override
    public boolean remove(Formato formato) {
        return tr.inTransaction(entityManager -> {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaDelete<Formato> cd = cb.createCriteriaDelete(Formato.class);
            Root<Formato> root = cd.from(Formato.class);
            cd.where(cb.equal(root.get("id"), formato.getId()));
            entityManager.createQuery(cd).executeUpdate();
        });
    }
}
