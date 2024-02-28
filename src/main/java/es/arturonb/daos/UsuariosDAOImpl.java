package es.arturonb.daos;

import es.arturonb.modelos.Usuario;
import es.arturonb.servicios.Transacciones;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Repository
public class UsuariosDAOImpl implements UsuariosDAO {
    @Autowired
    private Transacciones tr;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Usuario> getAll() {
        List<Usuario> arrUsuarios = new ArrayList<>();

        tr.inTransaction(entityManager -> {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
            Root<Usuario> root = cq.from(Usuario.class);
            cq.select(root);
            arrUsuarios.addAll(entityManager.createQuery(cq).getResultList());
        });

        return arrUsuarios;
    }

    @Override
    public Usuario getUsuarioById(int id) {
        AtomicReference<Usuario> usuario = new AtomicReference<>();

        tr.inTransaction(entityManager -> {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
            Root<Usuario> root = cq.from(Usuario.class);
            cq.select(root).where(cb.equal(root.get("id"), id));
            usuario.set(entityManager.createQuery(cq).getSingleResult());
        });

        return usuario.get();
    }

    @Override
    public Usuario getUsuarioByEmail(String email) {
        AtomicReference<Usuario> usuario = new AtomicReference<>();

        tr.inTransaction(entityManager -> {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
            Root<Usuario> root = cq.from(Usuario.class);
            cq.select(root).where(cb.equal(root.get("email"), email));
            List<Usuario> listaUsuario = entityManager.createQuery(cq).getResultList();

            if (!listaUsuario.isEmpty()) {
                usuario.set(listaUsuario.get(0));
            } else {
                usuario.set(null);
            }
        });

        return usuario.get();
    }

    @Override
    public int getMostRecentIdUsuarioInsertion() {
        AtomicInteger latestId = new AtomicInteger();

        tr.inTransaction(entityManager -> {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
            Root<Usuario> root = cq.from(Usuario.class);
            cq.orderBy(cb.desc(root.get("id")));
            latestId.set(entityManager.createQuery(cq).setMaxResults(1).getSingleResult().getId());
        });

        return latestId.get();
    }

    @Override
    public boolean checkCredentials(String email, String password) {
        AtomicReference<Usuario> usuario = new AtomicReference<>();

        tr.inTransaction(entityManager -> {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
            Root<Usuario> root = cq.from(Usuario.class);
            cq.where(cb.equal(root.get("email"), email));
            usuario.set(entityManager.createQuery(cq).getSingleResult());
        });

        return passwordEncoder.matches(password, usuario.get().getContrasenia());
    }

    @Override
    public boolean insert(Usuario usuario) {
        return tr.inTransaction(entityManager -> {
            entityManager.persist(usuario);
        });
    }

    @Override
    public boolean update(Usuario usuario) {
        return tr.inTransaction(entityManager -> {
            entityManager.merge(usuario);
        });
    }

    @Override
    public boolean remove(Usuario usuario) {
        return tr.inTransaction(entityManager -> {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaDelete<Usuario> cd = cb.createCriteriaDelete(Usuario.class);
            Root<Usuario> root = cd.from(Usuario.class);
            cd.where(cb.equal(root.get("id"), usuario.getId()));
            entityManager.createQuery(cd).executeUpdate();
        });
    }
}
