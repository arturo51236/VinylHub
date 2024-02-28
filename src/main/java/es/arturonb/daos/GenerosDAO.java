package es.arturonb.daos;

import es.arturonb.modelos.Genero;

import java.util.List;

public interface GenerosDAO {
    List<Genero> getGeneros();
    Genero getGeneroById(int id);
    Genero getGeneroByNombre(String nombre);
    int getMostRecentIdGeneroInsertion();
    boolean insert(Genero genero);
    boolean update(Genero genero);
    boolean remove(Genero genero);
}
