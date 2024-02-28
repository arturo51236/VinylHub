package es.arturonb.modelos;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "wantlists")
@Component
public class Wantlist {
    @Id
    private int id;
    @OneToOne(mappedBy = "wantlist")
    private Usuario usuario;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "wantlist_post", joinColumns = @JoinColumn(name = "id_wantlist"), inverseJoinColumns = @JoinColumn(name = "id_post"))
    private List<Post> posts = new ArrayList<Post>();

    public Wantlist() {
    }

    public Wantlist(int id) {
        this.id = id;
    }

    public Wantlist(int id, Usuario usuario, List<Post> posts) {
        this.id = id;
        this.usuario = usuario;
        this.posts = posts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
