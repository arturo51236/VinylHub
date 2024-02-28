package es.arturonb.daos;

import es.arturonb.modelos.Formato;

import java.util.List;

public interface FormatosDAO {
    List<Formato> getFormatos();
    Formato getFormatoById(int id);
    Formato getFormatoByNombre(String nombre);
    int getMostRecentIdFormatoInsertion();
    boolean insert(Formato formato);
    boolean update(Formato formato);
    boolean remove(Formato formato);
}
