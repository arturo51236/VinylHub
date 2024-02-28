package es.arturonb.modelos;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "posts")
@Component
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_posts")
    @SequenceGenerator(name = "seq_posts", sequenceName = "custom_id_post", initialValue = 900000, allocationSize = 1)
    private int id;
    @Column(name = "fecha_creacion")
    private Date fechaCreacion;
    private String sello;
    @Column(name = "cantidad_visitas")
    private int cantidadVisitas;
    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private Usuario usuario;
    @OneToOne(mappedBy = "post", cascade = CascadeType.ALL)
    private Disco disco;
    @ManyToMany(mappedBy = "posts", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Wantlist> wantlists = new ArrayList<>();

    public Post() {
    }

    public Post(Date fechaCreacion, String sello, int cantidadVisitas, Usuario usuario) {
        this.fechaCreacion = fechaCreacion;
        this.sello = sello;
        this.cantidadVisitas = cantidadVisitas;
        this.usuario = usuario;
    }

    public Post(int id, Date fechaCreacion, String sello, int cantidadVisitas, Usuario usuario) {
        this.id = id;
        this.fechaCreacion = fechaCreacion;
        this.sello = sello;
        this.cantidadVisitas = cantidadVisitas;
        this.usuario = usuario;
    }

    public Post(int id, Date fechaCreacion, String sello, int cantidadVisitas, Usuario usuario, List<Wantlist> wantlists) {
        this.id = id;
        this.fechaCreacion = fechaCreacion;
        this.sello = sello;
        this.cantidadVisitas = cantidadVisitas;
        this.usuario = usuario;
        this.wantlists = wantlists;
    }

    public Post(int id, Date fechaCreacion, String sello, int cantidadVisitas, Usuario usuario, Disco disco, List<Wantlist> wantlists) {
        this.id = id;
        this.fechaCreacion = fechaCreacion;
        this.sello = sello;
        this.cantidadVisitas = cantidadVisitas;
        this.usuario = usuario;
        this.disco = disco;
        this.wantlists = wantlists;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getSello() {
        return sello;
    }

    public void setSello(String sello) {
        this.sello = sello;
    }

    public int getCantidadVisitas() {
        return cantidadVisitas;
    }

    public void setCantidadVisitas(int cantidadVisitas) {
        this.cantidadVisitas = cantidadVisitas;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Disco getDisco() {
        return disco;
    }

    public void setDisco(Disco disco) {
        this.disco = disco;
    }

    public List<Wantlist> getWantlists() {
        return wantlists;
    }

    public void setWantlists(List<Wantlist> wantlists) {
        this.wantlists = wantlists;
    }
}
