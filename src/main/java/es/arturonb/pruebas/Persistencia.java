package es.arturonb.pruebas;

import es.arturonb.modelos.Usuario;
import es.arturonb.modelos.Wantlist;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Persistencia {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        Wantlist wantlist = new Wantlist();
        wantlist.setId(1);

        transaction.begin();
        em.persist(wantlist);
        transaction.commit();

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Usuario admin = new Usuario();
        admin.setNombre("Arturo");
        admin.setEmail("arturonb51236@gmail.com");
        admin.setContrasenia(passwordEncoder.encode("12345"));
        admin.setRol("admin");
        admin.setWantlist(wantlist);

        transaction.begin();
        em.persist(admin);
        transaction.commit();

        em.close();
        emf.close();
    }
}
