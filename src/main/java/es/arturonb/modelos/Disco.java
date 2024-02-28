package es.arturonb.modelos;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Entity
@Table(name = "discos")
@Component
public class Disco {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_discos")
    @SequenceGenerator(name = "seq_discos", sequenceName = "id_disco", allocationSize = 1)
    private int id;
    @Column(name = "artista", nullable = false)
    private String artista;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "foto", nullable = false)
    private String foto;
    @Column(name = "anio")
    private int anio;
    private String pais;
    private String notas;
    @ManyToOne
    @JoinColumn(name = "id_formato", referencedColumnName = "id", nullable = false)
    private Formato formato;
    @ManyToOne
    @JoinColumn(name = "id_genero", referencedColumnName = "id", nullable = false)
    private Genero genero;
    @OneToOne
    @JoinColumn(name = "id_post")
    private Post post;

    public Disco() {
    }

    public Disco(String artista, String nombre, String foto, int anio, String pais, String notas) {
        this.artista = artista;
        this.nombre = nombre;
        this.foto = foto;
        this.anio = anio;
        this.pais = pais;
        this.notas = notas;
    }

    public Disco(String artista, String nombre, String foto, int anio, String pais, String notas, Formato formato, Genero genero) {
        this.artista = artista;
        this.nombre = nombre;
        this.foto = foto;
        this.anio = anio;
        this.pais = pais;
        this.notas = notas;
        this.formato = formato;
        this.genero = genero;
    }

    public Disco(String artista, String nombre, String foto, int anio, String pais, String notas, Formato formato, Genero genero, Post post) {
        this.artista = artista;
        this.nombre = nombre;
        this.foto = foto;
        this.anio = anio;
        this.pais = pais;
        this.notas = notas;
        this.formato = formato;
        this.genero = genero;
        this.post = post;
    }

    public Disco(int id, String artista, String nombre, String foto, int anio, String pais, String notas, Formato formato, Genero genero, Post post) {
        this.id = id;
        this.artista = artista;
        this.nombre = nombre;
        this.foto = foto;
        this.anio = anio;
        this.pais = pais;
        this.notas = notas;
        this.formato = formato;
        this.genero = genero;
        this.post = post;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public Formato getFormato() {
        return formato;
    }

    public void setFormato(Formato formato) {
        this.formato = formato;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Disco disco = (Disco) obj;
        return id == disco.id && anio == disco.anio && Objects.equals(artista, disco.artista) && Objects.equals(nombre, disco.nombre) && Objects.equals(foto, disco.foto) && Objects.equals(pais, disco.pais) && Objects.equals(notas, disco.notas) && Objects.equals(formato, disco.formato) && Objects.equals(genero, disco.genero) && Objects.equals(post, disco.post);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, artista, nombre, foto, anio, pais, notas, formato, genero, post);
    }
}
