package es.arturonb.daos;

import es.arturonb.modelos.Wantlist;

public interface WantlistsDAO {
    Wantlist getWantlistById(int id);
    boolean insert(Wantlist wantlist);
    boolean update(Wantlist wantlist);
    boolean remove(Wantlist wantlist);
}
