package es.arturonb.controladores;

import es.arturonb.daos.DiscosDAO;
import es.arturonb.daos.PostsDAO;
import es.arturonb.daos.UsuariosDAO;
import es.arturonb.daos.WantlistsDAO;
import es.arturonb.modelos.Disco;
import es.arturonb.modelos.Post;
import es.arturonb.modelos.Usuario;
import es.arturonb.modelos.Wantlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@SessionAttributes("usuario")
public class ControladorPosts {
    @Autowired
    Usuario usuario;
    @Autowired
    Post post;
    @Autowired
    Disco disco;
    @Autowired
    Wantlist wantlist;
    @Autowired
    DiscosDAO daoD;
    @Autowired
    PostsDAO daoP;
    @Autowired
    UsuariosDAO daoU;
    @Autowired
    WantlistsDAO daoW;

    @GetMapping("/verPost/{id}")
    public String verPost(@PathVariable("id") int id, Model modelo) {
        boolean repetido = false;
        if (modelo.getAttribute("usuario") != null) {
            Usuario usuarioSesion = (Usuario) modelo.getAttribute("usuario");
            wantlist = daoW.getWantlistById(usuarioSesion.getWantlist().getId());

            for (Post post : wantlist.getPosts()) {
                if (post.getId() == id) {
                    repetido = true;
                }
            }
        }

        daoP.modifyNuevaCantidadVisitas(id);
        disco = daoP.getPostById(id).getDisco();
        List<Disco> discosRelacionados = daoD.getRelacionadosByGenero(disco.getGenero().getNombre());

        // Si en la lista se encuentra el mismo post al que se accede se elimina
        discosRelacionados.removeIf(disco -> disco.getPost().getId() == id);

        // En caso de que no se encuentre se elimina el disco de la última posición para que sean 5 discos
        if (discosRelacionados.size() == 6) {
            discosRelacionados.remove(5);
        }

        modelo.addAttribute("repetido", repetido);
        modelo.addAttribute("discosRelacionados", discosRelacionados);
        modelo.addAttribute("post", daoP.getPostById(id));
        return "verPost";
    }

    @GetMapping("/adminPosts")
    public String verPostsAdmin(Model modelo) {
        List<Post> listaPosts = daoP.getAll();
        if (!listaPosts.isEmpty()) {
            modelo.addAttribute("adminPosts", listaPosts);
        } else {
            modelo.addAttribute("adminPostsNull", true);
        }
        return "adminPosts";
    }

    @PostMapping("eliminarPost")
    public String eliminarPost(@RequestParam("idPost") int id) {
         if (daoD.remove(daoD.getDiscoByIdPost(id))) {
            usuario = daoP.getPostByIdAdmin(id).getUsuario();
            usuario.getPosts().remove(daoP.getPostByIdAdmin(id));

            if (!daoU.update(usuario)) {
                return "error";
            } else {
                daoP.remove(daoP.getPostByIdAdmin(id));
            }
        } else {
            return "error";
        }

        return "redirect:/adminPosts";
    }

    @GetMapping("modificarPost")
    public String verModificarPost(@RequestParam("idPost") int id, Model modelo) {
        modelo.addAttribute("post", daoP.getPostById(id));
        return "modificarPost";
    }

    @PostMapping("modificarPost")
    public String modificarPost(@RequestParam("cantdidadVisitas") int cantidadVisitas, @RequestParam("sello") String sello, @RequestParam("idPost") int id) {
        post = daoP.getPostById(id);
        post.setCantidadVisitas(cantidadVisitas);
        post.setSello(sello);
        if (!daoP.update(post)) {
            return "error";
        }

        return "redirect:/adminPosts";
    }
}
