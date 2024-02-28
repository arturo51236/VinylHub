package es.arturonb.modelos;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "generos")
@Component
public class Genero implements Serializable {
    @Id
    @Column(name = "id")
    private int id;
    private String nombre;
    @OneToMany(mappedBy = "genero", cascade = CascadeType.REMOVE)
    private List<Disco> discos = new ArrayList<Disco>();

    public Genero() {
    }

    public Genero(int id, String nombre, List<Disco> discos) {
        this.id = id;
        this.nombre = nombre;
        this.discos = discos;
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

    public List<Disco> getDiscos() {
        return discos;
    }

    public void setDiscos(List<Disco> discos) {
        this.discos = discos;
    }
}
