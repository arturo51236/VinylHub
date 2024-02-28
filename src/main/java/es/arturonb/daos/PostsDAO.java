package es.arturonb.daos;

import es.arturonb.modelos.Post;

import java.util.List;

public interface PostsDAO {
    List<Post> getAll();
    Post getPostById(int id);
    Post getPostByIdAdmin(int id);
    boolean insert(Post post);
    boolean update(Post post);
    boolean modifyNuevaCantidadVisitas(int id);
    boolean remove(Post post);
}
