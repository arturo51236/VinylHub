package es.arturonb.modelos;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Component
public class Usuario {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuarios")
    @SequenceGenerator(name = "seq_usuarios", sequenceName = "id_usuario", allocationSize = 1)
    private int id;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "contrasenia", nullable = false)
    private String contrasenia;
    private String rol;
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<Post> posts = new ArrayList<Post>();
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_wantlist")
    private Wantlist wantlist;

    public Usuario() {
    }

    public Usuario(String email, String contrasenia) {
        this.email = email;
        this.contrasenia = contrasenia;
    }

    public Usuario(String nombre, String email, String contrasenia) {
        this.nombre = nombre;
        this.email = email;
        this.contrasenia = contrasenia;
    }

    public Usuario(String nombre, String email, String contrasenia, String rol, Wantlist wantlist) {
        this.nombre = nombre;
        this.email = email;
        this.contrasenia = contrasenia;
        this.rol = rol;
        this.wantlist = wantlist;
    }

    public Usuario(int id, String nombre, String email, String contrasenia, String rol, List<Post> posts, Wantlist wantlist) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.contrasenia = contrasenia;
        this.rol = rol;
        this.posts = posts;
        this.wantlist = wantlist;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Wantlist getWantlist() {
        return wantlist;
    }

    public void setWantlist(Wantlist wantlist) {
        this.wantlist = wantlist;
    }
}
