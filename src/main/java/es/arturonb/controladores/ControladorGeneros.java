package es.arturonb.controladores;

import es.arturonb.daos.DiscosDAO;
import es.arturonb.daos.GenerosDAO;
import es.arturonb.daos.PostsDAO;
import es.arturonb.daos.UsuariosDAO;
import es.arturonb.modelos.Disco;
import es.arturonb.modelos.Genero;
import es.arturonb.modelos.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ControladorGeneros {
    @Autowired
    Usuario usuario;
    @Autowired
    Genero genero;
    @Autowired
    GenerosDAO daoG;
    @Autowired
    DiscosDAO daoD;
    @Autowired
    PostsDAO daoP;
    @Autowired
    UsuariosDAO daoU;

    @GetMapping("/adminGeneros")
    public String verGenerosAdmin(Model modelo) {
        List<Genero> listaGeneros = daoG.getGeneros();
        if (!listaGeneros.isEmpty()) {
            modelo.addAttribute("adminGeneros", listaGeneros);
        } else {
            modelo.addAttribute("adminGenerosNull", true);
        }
        return "adminGeneros";
    }

    @GetMapping("insertarGenero")
    public String verInsertarGenero() {
        return "insertarGenero";
    }

    @PostMapping("insertarGenero")
    public String insertarGenero(@RequestParam("nombre") String nombre) {
        genero.setId(daoG.getMostRecentIdGeneroInsertion() + 1);
        genero.setNombre(nombre);
        if (!daoG.insert(genero)) {
            return "error";
        }
        return "redirect:/adminGeneros";
    }

    @PostMapping("eliminarGenero")
    public String eliminarGenero(@RequestParam("idGenero") int id) {
        List<Disco> discosByGenero = daoD.getAllByIdGenero(id);

        for (Disco disco : discosByGenero) {
            if (daoD.remove(disco)) {
                usuario = daoP.getPostByIdAdmin(disco.getPost().getId()).getUsuario();
                usuario.getPosts().remove(daoP.getPostByIdAdmin(disco.getPost().getId()));

                if (!daoU.update(usuario)) {
                    return "error";
                } else {
                    daoP.remove(daoP.getPostByIdAdmin(disco.getPost().getId()));
                }
            }
        }

        if (!daoG.remove(daoG.getGeneroById(id))) {
            return "error";
        }

        return "redirect:/adminGeneros";
    }

    @GetMapping("modificarGenero")
    public String verModificarGenero(@RequestParam("idGenero") int id, Model modelo) {
        modelo.addAttribute("genero", daoG.getGeneroById(id));
        return "modificarGenero";
    }

    @PostMapping("modificarGenero")
    public String modificarGenero(@RequestParam("nombre") String nombre, @RequestParam("idGenero") int id) {
        genero = daoG.getGeneroById(id);
        genero.setNombre(nombre);
        if (!daoG.update(genero)) {
            return "error";
        }

        return "redirect:/adminGeneros";
    }
}
