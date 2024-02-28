package es.arturonb.controladores;

import es.arturonb.daos.*;
import es.arturonb.modelos.*;
import es.arturonb.servicios.InsertarDatos;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@CrossOrigin(origins = "*", methods = {RequestMethod.POST})
@SessionAttributes("usuario")
public class ControladorDiscos {
    @Autowired
    Disco disco;
    @Autowired
    Post post;
    @Autowired
    DiscosDAO daoD;
    @Autowired
    PostsDAO daoP;
    @Autowired
    GenerosDAO daoG;
    @Autowired
    FormatosDAO daoF;
    @Autowired
    UsuariosDAO daoU;
    @Autowired
    private InsertarDatos id;

    @RequestMapping("/")
    public String verIndex(Model modelo) {
        // Si no hay nada en las tablas de la base de datos se llama al método para insertar los datos básicos
        if (daoU.getAll().isEmpty() && daoP.getAll().isEmpty() && daoG.getGeneros().isEmpty() && daoF.getFormatos().isEmpty()) {
            id.insertarDatos();
        }

        modelo.addAttribute("top6Electronica", daoD.getMasVisitadosByGenero("Electrónica"));
        modelo.addAttribute("top6Rock", daoD.getMasVisitadosByGenero("Rock"));
        return "index";
    }

    @GetMapping("/nuevoDisco")
    public String mandarNuevoDisco(Model modelo) {
        modelo.addAttribute("generos", daoG.getGeneros());
        modelo.addAttribute("formatos", daoF.getFormatos());
        return "nuevoDisco";
    }

    @GetMapping("/adminDiscos")
    public String verDiscosAdmin(Model modelo) {
        List<Disco> listaDiscos = daoD.getAll();
        if (!listaDiscos.isEmpty()) {
            modelo.addAttribute("adminDiscos", listaDiscos);
        } else {
            modelo.addAttribute("adminDiscosNull", true);
        }
        return "adminDiscos";
    }

    @PostMapping("/nuevoDisco")
    public String crearNuevoDisco(HttpServletRequest request, Model modelo) {
        Date date = new Date();
        post.setFechaCreacion(date);
        post.setCantidadVisitas(0);
        post.setSello(request.getParameter("sello"));
        post.setUsuario((Usuario) modelo.getAttribute("usuario"));

        if (daoP.insert(post)) {
            disco.setArtista(request.getParameter("artista"));
            disco.setNombre(request.getParameter("nombre"));
            disco.setFoto(request.getParameter("foto"));
            disco.setAnio(Integer.parseInt(request.getParameter("anio")));
            disco.setPais(request.getParameter("pais"));
            disco.setGenero(daoG.getGeneroByNombre(request.getParameter("genero")));
            disco.setFormato(daoF.getFormatoByNombre(request.getParameter("formato")));
            disco.setNotas(request.getParameter("notas"));
            disco.setPost(post);
            if (daoD.insert(disco)) {
                return "redirect:/";
            } else {
                return "error";
            }
        } else {
            return "error";
        }
    }

// TODO:
//
//    @PostMapping("/guardarFoto")
//    public @ResponseBody ResponseEntity<JSONObject> guardarFoto(@RequestParam("foto") MultipartFile foto) {
//        JSONObject resultado = new JSONObject();
//
//        if (foto.isEmpty()) {
//            resultado.put("resultado", "La foto está corrupta");
//            return ResponseEntity.badRequest().body(resultado);
//        }
//
//        try {
//            String carpetaDestino = "/static/images-discos";
//            Path uploadPath = Paths.get(carpetaDestino).toAbsolutePath().normalize();
//
//            byte[] bytes = foto.getBytes();
//            Path filePath = uploadPath.resolve(Objects.requireNonNull(foto.getOriginalFilename()));
//            Files.write(filePath, bytes);
//
//            resultado.put("resultado", "Ok");
//            return ResponseEntity.ok().body(resultado);
//        } catch (Exception e) {
//            resultado.put("resultado", "La foto no se agregó correctamente");
//            return ResponseEntity.status(500).body(resultado);
//        }
//    }

    @GetMapping("/search")
    public String search(@RequestParam("valor") String valor, Model modelo) {
        modelo.addAttribute("resultados", daoD.filterDiscosByNombreOrArtista(valor));
        return "buscar";
    }

    @GetMapping("modificarDisco")
    public String verModificarDisco(@RequestParam("idDisco") int id, Model modelo) {
        modelo.addAttribute("disco", daoD.getDiscoById(id));
        modelo.addAttribute("generos", daoG.getGeneros());
        modelo.addAttribute("formatos", daoF.getFormatos());
        modelo.addAttribute("discoM", disco);
        return "modificarDisco";
    }

    @PostMapping("/modificarDisco")
    public String modificarDisco(@ModelAttribute("discoM") Disco disco) {
        if (!daoD.update(disco)) {
            return "error";
        }
        return "redirect:/adminDiscos";
    }
}
