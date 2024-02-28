package es.arturonb.servicios;

import es.arturonb.modelos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class InsertarDatos {
    @Autowired
    private Transacciones tr;

    public void insertarDatos() {
        // Wantlist necesaria para crear el usuario (admin)
        Wantlist wantlist = new Wantlist();
        wantlist.setId(1);

        tr.inTransaction(entityManager -> {
            entityManager.persist(wantlist);
        });

        // Usuario administrador
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Usuario admin = new Usuario();
        admin.setNombre("Arturo");
        admin.setEmail("arturonb51236@gmail.com");
        admin.setContrasenia(passwordEncoder.encode("12345"));
        admin.setRol("admin");
        admin.setWantlist(wantlist);

        tr.inTransaction(entityManager -> {
            entityManager.persist(admin);
        });

        // Wantlist necesaria para crear el usuario (normal)
        Wantlist wantlist2 = new Wantlist();
        wantlist.setId(2);

        tr.inTransaction(entityManager -> {
            entityManager.persist(wantlist2);
        });

        // Usuario normal
        Usuario user = new Usuario();
        user.setNombre("Alonso");
        user.setEmail("alonso@gmail.com");
        user.setContrasenia(passwordEncoder.encode("12345"));
        user.setRol("user");
        user.setWantlist(wantlist2);

        tr.inTransaction(entityManager -> {
            entityManager.persist(user);
        });

        // Formatos
        Formato form1 = new Formato();
        form1.setId(1);
        form1.setNombre("Vinilo");

        Formato form2 = new Formato();
        form2.setId(2);
        form2.setNombre("CD");

        Formato form3 = new Formato();
        form3.setId(3);
        form3.setNombre("Digital");

        Formato form4 = new Formato();
        form4.setId(4);
        form4.setNombre("Casete");

        tr.inTransaction(entityManager -> {
            entityManager.persist(form1);
        });

        tr.inTransaction(entityManager -> {
            entityManager.persist(form2);
        });

        tr.inTransaction(entityManager -> {
            entityManager.persist(form3);
        });

        tr.inTransaction(entityManager -> {
            entityManager.persist(form4);
        });

        // Generos
        Genero gene1 = new Genero();
        gene1.setId(1);
        gene1.setNombre("Electrónica");

        Genero gene2 = new Genero();
        gene2.setId(2);
        gene2.setNombre("Rock");

        tr.inTransaction(entityManager -> {
            entityManager.persist(gene1);
        });

        tr.inTransaction(entityManager -> {
            entityManager.persist(gene2);
        });

        // Posts (necesarios para poder crear los discos)
        Post post1 = new Post();
        post1.setCantidadVisitas(0);
        post1.setFechaCreacion(new Date());
        post1.setSello("Track Record");
        post1.setUsuario(admin);

        Post post2 = new Post();
        post2.setCantidadVisitas(0);
        post2.setFechaCreacion(new Date());
        post2.setSello("Geffen Records");
        post2.setUsuario(admin);

        Post post3 = new Post();
        post3.setCantidadVisitas(0);
        post3.setFechaCreacion(new Date());
        post3.setSello("Asylum Records");
        post3.setUsuario(admin);

        Post post4 = new Post();
        post4.setCantidadVisitas(0);
        post4.setFechaCreacion(new Date());
        post4.setSello("Harvest");
        post4.setUsuario(admin);

        Post post5 = new Post();
        post5.setCantidadVisitas(0);
        post5.setFechaCreacion(new Date());
        post5.setSello("A&M Records");
        post5.setUsuario(admin);

        Post post6 = new Post();
        post6.setCantidadVisitas(0);
        post6.setFechaCreacion(new Date());
        post6.setSello("Warner Bros. Records");
        post6.setUsuario(admin);

        Post post7 = new Post();
        post7.setCantidadVisitas(0);
        post7.setFechaCreacion(new Date());
        post7.setSello("Pulse");
        post7.setUsuario(user);

        Post post8 = new Post();
        post8.setCantidadVisitas(0);
        post8.setFechaCreacion(new Date());
        post8.setSello("Techogold");
        post8.setUsuario(user);

        Post post9 = new Post();
        post9.setCantidadVisitas(0);
        post9.setFechaCreacion(new Date());
        post9.setSello("Bit Progressive Music");
        post9.setUsuario(user);

        Post post10 = new Post();
        post10.setCantidadVisitas(0);
        post10.setFechaCreacion(new Date());
        post10.setSello("Dreams Corporation");
        post10.setUsuario(user);

        Post post11 = new Post();
        post11.setCantidadVisitas(0);
        post11.setFechaCreacion(new Date());
        post11.setSello("Intense Records");
        post11.setUsuario(user);

        Post post12 = new Post();
        post12.setCantidadVisitas(0);
        post12.setFechaCreacion(new Date());
        post12.setSello("Fog Area Records");
        post12.setUsuario(user);

        tr.inTransaction(entityManager -> {
            entityManager.persist(post1);
        });

        tr.inTransaction(entityManager -> {
            entityManager.persist(post2);
        });

        tr.inTransaction(entityManager -> {
            entityManager.persist(post3);
        });

        tr.inTransaction(entityManager -> {
            entityManager.persist(post4);
        });

        tr.inTransaction(entityManager -> {
            entityManager.persist(post5);
        });

        tr.inTransaction(entityManager -> {
            entityManager.persist(post6);
        });

        tr.inTransaction(entityManager -> {
            entityManager.persist(post7);
        });

        tr.inTransaction(entityManager -> {
            entityManager.persist(post8);
        });

        tr.inTransaction(entityManager -> {
            entityManager.persist(post9);
        });

        tr.inTransaction(entityManager -> {
            entityManager.persist(post10);
        });

        tr.inTransaction(entityManager -> {
            entityManager.persist(post11);
        });

        tr.inTransaction(entityManager -> {
            entityManager.persist(post12);
        });

        // Discos
        Disco disc1 = new Disco();
        disc1.setNombre("Behind Blue Eyes");
        disc1.setArtista("The Who");
        disc1.setFoto("behindblueeyes.jpg");
        disc1.setAnio(1971);
        disc1.setNotas("The front cover image was taken by photographer Ethan A. Russell on the way to a gig in Leicester.");
        disc1.setPais("UK");
        disc1.setGenero(gene2);
        disc1.setFormato(form1);
        disc1.setPost(post1);

        Disco disc2 = new Disco();
        disc2.setNombre("Don't Cry (Original)");
        disc2.setArtista("Guns N' Roses");
        disc2.setFoto("gunsdontcry.jpg");
        disc2.setAnio(1991);
        disc2.setNotas("Album sales: 7,000,000.");
        disc2.setPais("US");
        disc2.setGenero(gene2);
        disc2.setFormato(form1);
        disc2.setPost(post2);

        Disco disc3 = new Disco();
        disc3.setNombre("Hotel California");
        disc3.setArtista("Eagles");
        disc3.setFoto("hotelcalifornia.jpg");
        disc3.setAnio(1976);
        disc3.setNotas("Album sales: 16,000,000.");
        disc3.setPais("US");
        disc3.setGenero(gene2);
        disc3.setFormato(form1);
        disc3.setPost(post3);

        Disco disc4 = new Disco();
        disc4.setNombre("Money");
        disc4.setArtista("Pink Floyd");
        disc4.setFoto("pinkfloydmoney.jpg");
        disc4.setAnio(1973);
        disc4.setNotas("Early releases included two posters and two color stickers inside the gatefold sleeve, all designed by the U.K. graphics and design company Hipgnosis.");
        disc4.setPais("US");
        disc4.setGenero(gene2);
        disc4.setFormato(form1);
        disc4.setPost(post4);

        Disco disc5 = new Disco();
        disc5.setNombre("Every Breath You Take");
        disc5.setArtista("The Police");
        disc5.setFoto("policeeverybreath.jpg");
        disc5.setAnio(1983);
        disc5.setNotas("The album's original cover artwork, designed by Norman Moore, was available in 36 variations, with different arrangements of the colour stripes and showing different photographs of the band members.");
        disc5.setPais("UK");
        disc5.setGenero(gene2);
        disc5.setFormato(form1);
        disc5.setPost(post5);

        Disco disc6 = new Disco();
        disc6.setNombre("Losing My Religion");
        disc6.setArtista("R.E.M.");
        disc6.setFoto("remlosingmyreligion.jpg");
        disc6.setAnio(1991);
        disc6.setNotas("The lead single to R.E.M.'s quadruple platinum album 'Out Of Time'.");
        disc6.setPais("UK");
        disc6.setGenero(gene2);
        disc6.setFormato(form1);
        disc6.setPost(post6);

        Disco disc7 = new Disco();
        disc7.setNombre("Interceptor (D-Cay Mix)");
        disc7.setArtista("Hypetraxx Vs. D-Cay");
        disc7.setFoto("dcayinterceptor.jpg");
        disc7.setAnio(1999);
        disc7.setNotas("I thought the day would never come. No place to hide, nowhere to run. I just hope that I can find a way, to make it through another day. Find a way. Find a way. Interceptor.");
        disc7.setPais("Alemania");
        disc7.setGenero(gene1);
        disc7.setFormato(form1);
        disc7.setPost(post7);

        Disco disc8 = new Disco();
        disc8.setNombre("Back To Earth");
        disc8.setArtista("Duke Hopkins");
        disc8.setFoto("dukebacktoearth.jpg");
        disc8.setAnio(1999);
        disc8.setNotas("Produced by Marc Steinmeier & Olaf Cramer.");
        disc8.setPais("Alemania");
        disc8.setGenero(gene1);
        disc8.setFormato(form1);
        disc8.setPost(post8);

        Disco disc9 = new Disco();
        disc9.setNombre("Lost In The Moon");
        disc9.setArtista("DJ Javi Colas");
        disc9.setFoto("javilostinthemoon.jpg");
        disc9.setAnio(2007);
        disc9.setNotas(null);
        disc9.setPais("España");
        disc9.setGenero(gene1);
        disc9.setFormato(form1);
        disc9.setPost(post9);

        Disco disc10 = new Disco();
        disc10.setNombre("Colours (Original)");
        disc10.setArtista("DJ Jesse");
        disc10.setFoto("jessecolours.jpg");
        disc10.setAnio(2004);
        disc10.setNotas(null);
        disc10.setPais("España");
        disc10.setGenero(gene1);
        disc10.setFormato(form1);
        disc10.setPost(post10);

        Disco disc11 = new Disco();
        disc11.setNombre("Always (Extended Mix)");
        disc11.setArtista("Jo-Ann");
        disc11.setFoto("joannalways.jpg");
        disc11.setAnio(1995);
        disc11.setNotas("Rights Society: SIAE.");
        disc11.setPais("Italia");
        disc11.setGenero(gene1);
        disc11.setFormato(form1);
        disc11.setPost(post11);

        Disco disc12 = new Disco();
        disc12.setNombre("Moonwalker");
        disc12.setArtista("Dee Mark");
        disc12.setFoto("moonwalkerdee.jpg");
        disc12.setAnio(2001);
        disc12.setNotas(null);
        disc12.setPais("UK");
        disc12.setGenero(gene1);
        disc12.setFormato(form1);
        disc12.setPost(post12);

        tr.inTransaction(entityManager -> {
            entityManager.persist(disc1);
        });

        tr.inTransaction(entityManager -> {
            entityManager.persist(disc2);
        });

        tr.inTransaction(entityManager -> {
            entityManager.persist(disc3);
        });

        tr.inTransaction(entityManager -> {
            entityManager.persist(disc4);
        });

        tr.inTransaction(entityManager -> {
            entityManager.persist(disc5);
        });

        tr.inTransaction(entityManager -> {
            entityManager.persist(disc6);
        });

        tr.inTransaction(entityManager -> {
            entityManager.persist(disc7);
        });

        tr.inTransaction(entityManager -> {
            entityManager.persist(disc8);
        });

        tr.inTransaction(entityManager -> {
            entityManager.persist(disc9);
        });

        tr.inTransaction(entityManager -> {
            entityManager.persist(disc10);
        });

        tr.inTransaction(entityManager -> {
            entityManager.persist(disc11);
        });

        tr.inTransaction(entityManager -> {
            entityManager.persist(disc12);
        });
    }
}
