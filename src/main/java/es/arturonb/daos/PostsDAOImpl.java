package es.arturonb.daos;

import es.arturonb.modelos.Post;
import es.arturonb.servicios.Transacciones;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Repository
public class PostsDAOImpl implements PostsDAO {
    @Autowired
    private Transacciones tr;
    @Autowired
    DiscosDAO daoD;

    @Override
    public List<Post> getAll() {
        List<Post> arrPosts = new ArrayList<>();

        tr.inTransaction(entityManager -> {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Post> cq = cb.createQuery(Post.class);
            Root<Post> root = cq.from(Post.class);
            cq.select(root);
            arrPosts.addAll(entityManager.createQuery(cq).getResultList());
        });

        return arrPosts;
    }

    @Override
    public Post getPostById(int id) {
        AtomicReference<Post> post = new AtomicReference<>();

        tr.inTransaction(entityManager -> {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Post> cq = cb.createQuery(Post.class);
            Root<Post> root = cq.from(Post.class);
            cq.select(root).where(cb.equal(root.get("id"), id));
            post.set(entityManager.createQuery(cq).getSingleResult());
        });

        if (post.get().getDisco() == null) {
            post.get().setDisco(daoD.getDiscoByIdPost(id));
        }

        return post.get();
    }

    @Override
    public Post getPostByIdAdmin(int id) {
        AtomicReference<Post> post = new AtomicReference<>();

        tr.inTransaction(entityManager -> {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Post> cq = cb.createQuery(Post.class);
            Root<Post> root = cq.from(Post.class);
            cq.select(root).where(cb.equal(root.get("id"), id));
            post.set(entityManager.createQuery(cq).getSingleResult());
        });

        return post.get();
    }

    @Override
    public boolean insert(Post post) {
        return tr.inTransaction(entityManager -> {
            entityManager.persist(post);
        });
    }

    @Override
    public boolean update(Post post) {
        return tr.inTransaction(entityManager -> {
            entityManager.merge(post);
        });
    }

    @Override
    public boolean modifyNuevaCantidadVisitas(int id) {
        return tr.inTransaction(entityManager -> {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaUpdate<Post> cu = cb.createCriteriaUpdate(Post.class);
            Root<Post> root = cu.from(Post.class);
            Post post = getPostById(id);
            cu.set("cantidadVisitas", post.getCantidadVisitas() + 1);
            cu.where(cb.equal(root.get("id"), id));
            entityManager.createQuery(cu).executeUpdate();
        });
    }

    @Override
    public boolean remove(Post post) {
        return tr.inTransaction(entityManager -> {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaDelete<Post> cd = cb.createCriteriaDelete(Post.class);
            Root<Post> root = cd.from(Post.class);
            cd.where(cb.equal(root.get("id"), post.getId()));
            entityManager.createQuery(cd).executeUpdate();
        });
    }
}
