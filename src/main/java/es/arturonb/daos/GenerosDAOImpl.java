package es.arturonb.daos;

import es.arturonb.modelos.Genero;
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
public class GenerosDAOImpl implements GenerosDAO {
    @Autowired
    private Transacciones tr;

    @Override
    public List<Genero> getGeneros() {
        List<Genero> arrGeneros = new ArrayList<>();

        tr.inTransaction(entityManager -> {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Genero> cq = cb.createQuery(Genero.class);
            cq.select(cq.from(Genero.class));
            arrGeneros.addAll(entityManager.createQuery(cq).getResultList());
        });

        return arrGeneros;
    }

    @Override
    public Genero getGeneroById(int id) {
        AtomicReference<Genero> genero = new AtomicReference<>();

        tr.inTransaction(entityManager -> {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Genero> cq = cb.createQuery(Genero.class);
            Root<Genero> root = cq.from(Genero.class);
            cq.select(root).where(cb.equal(root.get("id"), id));
            genero.set(entityManager.createQuery(cq).getSingleResult());
        });

        return genero.get();
    }

    @Override
    public Genero getGeneroByNombre(String nombre) {
        AtomicReference<Genero> genero = new AtomicReference<>();

        tr.inTransaction(entityManager -> {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Genero> cq = cb.createQuery(Genero.class);
            Root<Genero> root = cq.from(Genero.class);
            cq.select(root).where(cb.equal(root.get("nombre"), nombre));
            genero.set(entityManager.createQuery(cq).getSingleResult());
        });

        return genero.get();
    }

    @Override
    public int getMostRecentIdGeneroInsertion() {
        AtomicInteger latestId = new AtomicInteger();

        tr.inTransaction(entityManager -> {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Genero> cq = cb.createQuery(Genero.class);
            Root<Genero> root = cq.from(Genero.class);
            cq.orderBy(cb.desc(root.get("id")));
            latestId.set(entityManager.createQuery(cq).setMaxResults(1).getSingleResult().getId());
        });

        return latestId.get();
    }

    @Override
    public boolean insert(Genero genero) {
        return tr.inTransaction(entityManager -> {
            entityManager.persist(genero);
        });
    }

    @Override
    public boolean update(Genero genero) {
        return tr.inTransaction(entityManager -> {
            entityManager.merge(genero);
        });
    }

    @Override
    public boolean remove(Genero genero) {
        return tr.inTransaction(entityManager -> {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaDelete<Genero> cd = cb.createCriteriaDelete(Genero.class);
            Root<Genero> root = cd.from(Genero.class);
            cd.where(cb.equal(root.get("id"), genero.getId()));
            entityManager.createQuery(cd).executeUpdate();
        });
    }
}
