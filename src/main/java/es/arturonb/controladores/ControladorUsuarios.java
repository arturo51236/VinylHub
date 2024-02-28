package es.arturonb.controladores;

import es.arturonb.daos.UsuariosDAO;
import es.arturonb.daos.WantlistsDAO;
import es.arturonb.modelos.Post;
import es.arturonb.modelos.Usuario;
import es.arturonb.modelos.Wantlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;

@Controller
@SessionAttributes("usuario")
public class ControladorUsuarios {
    @Autowired
    Usuario usuario;
    @Autowired
    Wantlist wantlist;
    @Autowired
    UsuariosDAO daoU;
    @Autowired
    WantlistsDAO daoW;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String verLogin(Model modelo) {
        modelo.addAttribute("usuario", usuario);
        return "login";
    }

    @PostMapping("/login")
    public String iniciarSesion(@ModelAttribute("usuario") Usuario usuario, @RequestParam("email") String email, @RequestParam("contrasenia") String password, Model modelo, SessionStatus status) {
        if (daoU.getUsuarioByEmail(email) != null) {
            if (daoU.checkCredentials(email, password)) {
                modelo.addAttribute("usuario", daoU.getUsuarioByEmail(email));
                if (daoU.getUsuarioByEmail(email).getRol().equals("admin")) {
                    modelo.addAttribute("admin", true);
                }
                return "redirect:/";
            } else {
                status.setComplete();
                modelo.addAttribute("usuario", null);
                return "error";
            }
        } else {
            status.setComplete();
            modelo.addAttribute("usuario", null);
            return "error";
        }
    }

    @GetMapping("/register")
    public String verRegistro(Model modelo) {
        modelo.addAttribute("usuario", usuario);
        return "registro";
    }

    @PostMapping("/register")
    public String registro(@ModelAttribute("usuario") Usuario usuario, @RequestParam("nombre") String nombre, @RequestParam("email") String email, @RequestParam("contrasenia") String password, Model modelo) {
        int nuevoIdWantlist = daoU.getMostRecentIdUsuarioInsertion()+1;
        wantlist.setId(nuevoIdWantlist);
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setContrasenia(passwordEncoder.encode(password));
        usuario.setRol("user");
        usuario.setWantlist(wantlist);

        if (daoW.insert(wantlist)) {
            if (daoU.insert(usuario)) {
                modelo.addAttribute("usuario", usuario);
                return "redirect:/";
            } else {
                return "error";
            }
        } else {
            return "error";
        }
    }

    @GetMapping("/logout")
    public String cerrarSesion(SessionStatus status) {
        status.setComplete();
        return "redirect:/";
    }

    @GetMapping("/admin")
    public String verAdmin() {
        return "admin";
    }

    @GetMapping("/adminUsuarios")
    public String verUsuariosAdmin(Model modelo) {
        List<Usuario> listaUsuarios = daoU.getAll();
        if (!listaUsuarios.isEmpty()) {
            modelo.addAttribute("adminUsuarios", listaUsuarios);
        } else {
            modelo.addAttribute("adminUsuariosNull", true);
        }

        return "adminUsuarios";
    }

    @PostMapping("/eliminarUsuario")
    public String eliminarUsuario(@RequestParam("idUsuario") int idUsuario) {
        usuario = daoU.getUsuarioById(idUsuario);
        List<Post> listaPostsUsuario = usuario.getPosts();

        for (Post post : listaPostsUsuario) {
            post.setUsuario(null);
        }

        usuario.setPosts(listaPostsUsuario);

        if (daoU.update(usuario)) {
            if (daoU.remove(daoU.getUsuarioById(idUsuario))) {
                daoW.remove(daoW.getWantlistById(usuario.getWantlist().getId()));
            } else {
                return "error";
            }
        } else {
            return "error";
        }

        return "redirect:/adminUsuarios";
    }

    @GetMapping("modificarUsuario")
    public String verModificarUsuario(@RequestParam("idUsuario") int id, Model modelo) {
        modelo.addAttribute("usuario", daoU.getUsuarioById(id));
        return "modificarUsuario";
    }

    @PostMapping("modificarUsuario")
    public String modificarUsuario(@RequestParam("nombre") String nombre, @RequestParam("email") String email, @RequestParam("contrasenia") String contrasenia, @RequestParam("rol") String rol, @RequestParam("idUsuario") int id) {
        usuario = daoU.getUsuarioById(id);
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setContrasenia(passwordEncoder.encode(contrasenia));
        usuario.setRol(rol);

        if (!daoU.update(usuario)) {
            return "error";
        }

        return "redirect:/adminUsuarios";
    }
}
