package es.arturonb.servicios;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Consumer;

@Service
public class Transacciones {
    @Transactional
    public boolean inTransaction(Consumer<EntityManager> accion) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            accion.accept(em);
            transaction.commit();
            return true;
        } catch (Exception ex) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException(ex);
        } finally {
            em.close();
        }
    }
}