package es.arturonb.controladores;

import es.arturonb.daos.DiscosDAO;
import es.arturonb.daos.FormatosDAO;
import es.arturonb.daos.PostsDAO;
import es.arturonb.daos.UsuariosDAO;
import es.arturonb.modelos.Disco;
import es.arturonb.modelos.Formato;
import es.arturonb.modelos.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ControladorFormatos {
    @Autowired
    Usuario usuario;
    @Autowired
    Formato formato;
    @Autowired
    FormatosDAO daoF;
    @Autowired
    DiscosDAO daoD;
    @Autowired
    PostsDAO daoP;
    @Autowired
    UsuariosDAO daoU;

    @GetMapping("/adminFormatos")
    public String verFormatosAdmin(Model modelo) {
        List<Formato> listaFormatos = daoF.getFormatos();
        if (!listaFormatos.isEmpty()) {
            modelo.addAttribute("adminFormatos", listaFormatos);
        } else {
            modelo.addAttribute("adminFormatosNull", true);
        }
        return "adminFormatos";
    }

    @GetMapping("insertarFormato")
    public String verInsertarFormato() {
        return "insertarFormato";
    }

    @PostMapping("insertarFormato")
    public String insertarFormato(@RequestParam("nombre") String nombre) {
        formato.setId(daoF.getMostRecentIdFormatoInsertion() + 1);
        formato.setNombre(nombre);
        if (!daoF.insert(formato)) {
            return "error";
        }
        return "redirect:/adminFormatos";
    }

    @PostMapping("eliminarFormato")
    public String eliminarFormato(@RequestParam("idFormato") int id) {
        List<Disco> discosByFormato = daoD.getAllByIdFormato(id);

        for (Disco disco : discosByFormato) {
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

        if (!daoF.remove(daoF.getFormatoById(id))) {
            return "error";
        }

        return "redirect:/adminFormatos";
    }

    @GetMapping("modificarFormato")
    public String verModificarFormato(@RequestParam("idFormato") int id, Model modelo) {
        modelo.addAttribute("formato", daoF.getFormatoById(id));
        return "modificarFormato";
    }

    @PostMapping("modificarFormato")
    public String modificarFormato(@RequestParam("nombre") String nombre, @RequestParam("idFormato") int id) {
        formato = daoF.getFormatoById(id);
        formato.setNombre(nombre);
        if (!daoF.update(formato)) {
            return "error";
        }

        return "redirect:/adminFormatos";
    }
}
