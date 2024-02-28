package es.arturonb.daos;

import es.arturonb.modelos.Disco;

import java.util.List;

public interface DiscosDAO {
    List<Disco> getAll();
    List<Disco> filterDiscosByNombreOrArtista(String cadena);
    List<Disco> getMasVisitadosByGenero(String genero);
    List<Disco> getRelacionadosByGenero(String genero);
    List<Disco> getAllByIdGenero(int idGenero);
    List<Disco> getAllByIdFormato(int idFormato);
    Disco getDiscoById(int id);
    Disco getDiscoByIdPost(int idPost);
    boolean insert(Disco disco);
    boolean update(Disco disco);
    boolean remove(Disco disco);
}
