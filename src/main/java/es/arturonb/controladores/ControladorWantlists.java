package es.arturonb.controladores;

import es.arturonb.daos.PostsDAO;
import es.arturonb.daos.WantlistsDAO;
import es.arturonb.modelos.Post;
import es.arturonb.modelos.Usuario;
import es.arturonb.modelos.Wantlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("usuario")
public class ControladorWantlists {
    @Autowired
    Wantlist wantlist;
    @Autowired
    WantlistsDAO daoW;
    @Autowired
    PostsDAO daoP;

    @PostMapping("/aniadirAWantlist")
    public String aniadirAWantlist(@RequestParam("idPost") int idPost, Model modelo) {
        boolean repetido = false;
        Usuario usuarioSesion = (Usuario) modelo.getAttribute("usuario");
        wantlist = daoW.getWantlistById(usuarioSesion.getWantlist().getId());
        wantlist.setUsuario(usuarioSesion);
        if (wantlist.getPosts() != null) {
            List<Post> postList = wantlist.getPosts();

            for (Post post : postList) {
                if (post.getId() == idPost) {
                    repetido = true;
                }
            }

            if (!repetido) {
                postList.add(daoP.getPostById(idPost));
                wantlist.setPosts(postList);
            }
        } else {
            List<Post> postList = new ArrayList<>();
            postList.add(daoP.getPostById(idPost));
            wantlist.setPosts(postList);
        }

        if (!repetido) {
            if (daoW.update(wantlist)) {
                return "redirect:/verPost/".concat(String.valueOf(idPost));
            } else {
                return "error";
            }
        } else {
            return "redirect:/verPost/".concat(String.valueOf(idPost));
        }
    }

    @PostMapping("/eliminarDeWantlist")
    public String eliminarDeWantlist(@RequestParam("idPost") int idPost, Model modelo) {
        Usuario usuarioSesion = (Usuario) modelo.getAttribute("usuario");
        wantlist = daoW.getWantlistById(usuarioSesion.getWantlist().getId());
        List<Post> postList = wantlist.getPosts();
        postList.removeIf(post -> post.getId() == idPost);
        wantlist.setPosts(postList);

        if (daoW.update(wantlist)) {
            return "redirect:/wantlist/".concat(String.valueOf(usuarioSesion.getWantlist().getId()));
        } else {
            return "error";
        }
    }

    @GetMapping("/wantlist/{id}")
    public String verWantlistUsuario(@PathVariable("id") int id, Model modelo) {
        wantlist = daoW.getWantlistById(id);
        if (!wantlist.getPosts().isEmpty()) {
            modelo.addAttribute("miWantlist", wantlist.getPosts());
        } else {
            modelo.addAttribute("wantlistNull", true);
        }

        return "wantlist";
    }
}
