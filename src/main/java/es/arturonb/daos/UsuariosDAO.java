package es.arturonb.daos;

import es.arturonb.modelos.Usuario;

import java.util.List;

public interface UsuariosDAO {
    List<Usuario> getAll();
    Usuario getUsuarioById(int id);
    Usuario getUsuarioByEmail(String email);
    int getMostRecentIdUsuarioInsertion();
    boolean checkCredentials(String email, String password);
    boolean insert(Usuario usuario);
    boolean update(Usuario usuario);
    boolean remove(Usuario usuario);
}
