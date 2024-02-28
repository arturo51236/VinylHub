package es.arturonb.daos;

import es.arturonb.modelos.Disco;
import es.arturonb.modelos.Genero;
import es.arturonb.modelos.Post;
import es.arturonb.servicios.Transacciones;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

@Repository
public class DiscosDAOImpl implements DiscosDAO {
    @Autowired
    private Transacciones tr;

    @Override
    public List<Disco> getAll() {
        List<Disco> arrDiscos = new ArrayList<>();

        tr.inTransaction(entityManager -> {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Disco> cq = cb.createQuery(Disco.class);
            cq.select(cq.from(Disco.class));
            arrDiscos.addAll(entityManager.createQuery(cq).getResultList());
        });

        return arrDiscos;
    }

    @Override
    public List<Disco> filterDiscosByNombreOrArtista(String cadena) {
        List<Disco> arrDiscos = new ArrayList<>();

        tr.inTransaction(entityManager -> {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Disco> cq = cb.createQuery(Disco.class);
            Root<Disco> root = cq.from(Disco.class);
            Predicate nombreLike = cb.like(cb.upper(root.get("nombre")), "%" + cadena.toUpperCase() + "%");
            Predicate artistaLike = cb.like(cb.upper(root.get("artista")), "%" + cadena.toUpperCase() + "%");
            Predicate nombreOArtista = cb.or(nombreLike, artistaLike);
            cq.select(root).where(nombreOArtista);
            arrDiscos.addAll(entityManager.createQuery(cq).getResultList());
        });

        // Elimina los duplicados en caso de que existan
        Set<Disco> setDiscos = new HashSet<>(arrDiscos);
        return new ArrayList<>(setDiscos);
    }

    @Override
    public List<Disco> getMasVisitadosByGenero(String genero) {
        List<Disco> arrDiscos = new ArrayList<>();

        tr.inTransaction(entityManager -> {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Disco> cq = cb.createQuery(Disco.class);
            Root<Disco> root = cq.from(Disco.class);
            Join<Disco, Genero> generoJoin = root.join("genero");
            Join<Disco, Post> postJoin = root.join("post");
            cq.orderBy(cb.desc(postJoin.get("cantidadVisitas")));
            cq.select(root).where(cb.equal(generoJoin.get("nombre"), genero));
            arrDiscos.addAll(entityManager.createQuery(cq).setMaxResults(6).getResultList());
        });

        return arrDiscos;
    }

    @Override
    public List<Disco> getRelacionadosByGenero(String genero) {
        List<Disco> arrDiscos = new ArrayList<>();

        tr.inTransaction(entityManager -> {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Disco> cq = cb.createQuery(Disco.class);
            Root<Disco> root = cq.from(Disco.class);
            Join<Disco, Genero> generoJoin = root.join("genero");
            cq.select(root).where(cb.equal(generoJoin.get("nombre"), genero));
            arrDiscos.addAll(entityManager.createQuery(cq).setMaxResults(6).getResultList());
        });

        return arrDiscos;
    }

    @Override
    public List<Disco> getAllByIdGenero(int idGenero) {
        List<Disco> arrDiscos = new ArrayList<>();

        tr.inTransaction(entityManager -> {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Disco> cq = cb.createQuery(Disco.class);
            Root<Disco> root = cq.from(Disco.class);
            Join<Disco, Genero> generoJoin = root.join("genero");
            cq.select(root).where(cb.equal(generoJoin.get("id"), idGenero));
            arrDiscos.addAll(entityManager.createQuery(cq).getResultList());
        });

        return arrDiscos;
    }

    @Override
    public List<Disco> getAllByIdFormato(int idFormato) {
        List<Disco> arrDiscos = new ArrayList<>();

        tr.inTransaction(entityManager -> {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Disco> cq = cb.createQuery(Disco.class);
            Root<Disco> root = cq.from(Disco.class);
            Join<Disco, Genero> generoJoin = root.join("formato");
            cq.select(root).where(cb.equal(generoJoin.get("id"), idFormato));
            arrDiscos.addAll(entityManager.createQuery(cq).getResultList());
        });

        return arrDiscos;
    }

    @Override
    public Disco getDiscoById(int id) {
        AtomicReference<Disco> disco = new AtomicReference<>();

        tr.inTransaction(entityManager -> {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Disco> cq = cb.createQuery(Disco.class);
            Root<Disco> root = cq.from(Disco.class);
            cq.select(root).where(cb.equal(root.get("id"), id));
            disco.set(entityManager.createQuery(cq).getSingleResult());
        });

        return disco.get();
    }

    @Override
    public Disco getDiscoByIdPost(int idPost) {
        AtomicReference<Disco> disco = new AtomicReference<>();

        tr.inTransaction(entityManager -> {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Disco> cq = cb.createQuery(Disco.class);
            Root<Disco> root = cq.from(Disco.class);
            Join<Disco, Post> postJoin = root.join("post");
            cq.select(root).where(cb.equal(postJoin.get("id"), idPost));
            disco.set(entityManager.createQuery(cq).getSingleResult());
        });

        return disco.get();
    }

    @Override
    public boolean insert(Disco disco) {
        return tr.inTransaction(entityManager -> {
            entityManager.persist(disco);
        });
    }

    @Override
    public boolean update(Disco disco) {
        return tr.inTransaction(entityManager -> {
            entityManager.merge(disco);
        });
    }

    @Override
    public boolean remove(Disco disco) {
        return tr.inTransaction(entityManager -> {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaDelete<Disco> cd = cb.createCriteriaDelete(Disco.class);
            Root<Disco> root = cd.from(Disco.class);
            cd.where(cb.equal(root.get("id"), disco.getId()));
            entityManager.createQuery(cd).executeUpdate();
        });
    }
}
