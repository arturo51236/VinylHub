<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>VinylHub - Nuevo post</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Fira+Sans&display=swap');
        @import url('https://fonts.googleapis.com/css2?family=Montserrat+Alternates&display=swap');

        body {
            font-family: 'Fira Sans', serif;
            background-color: #ced4da;
        }

        .logo {
            width: 70px;
            height: 70px;
        }
    </style>
</head>
<body class="bg-dark">
    <header>
        <nav class="navbar navbar-expand-lg bg-body-tertiary" data-bs-theme="dark">
            <div class="container-fluid m-auto">
                <li class="nav-item h-100 d-flex mt-2 mt-sm-3 mt-md-3 mt-lg-0 align-items-center">
                    <a class="nav-link text-white mx-2" href="${pageContext.request.contextPath}/"><img class="logo" src="${pageContext.request.contextPath}/static/images/vinylhub-nobg.png"></a>
                    <a class="navbar-brand ms-3" href="${pageContext.request.contextPath}/" style="font-size: 25px;">VinylHub</a>
                </li>
                <div class="container d-flex flex-column text-center m-auto">
                    <form:form action="search" class="d-flex" role="search" method="get">
                        <input class="form-control me-2" name="valor" type="search" placeholder="Search" aria-label="Search">
                        <button class="btn btn-success" type="submit">Buscar</button>
                    </form:form>
                </div>
                <div>
                    <div class="container d-flex flex-row align-items-center">
                        <div class="text-white ms-3">
                            <ul class="navbar-nav">
                                <li class="nav-item dropdown">
                                    <button class="btn btn-dark dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                                        ${usuario.nombre}
                                    </button>
                                    <ul class="dropdown-menu dropdown-menu-dark">
                                        <li><a class="dropdown-item" href="wantlist/${usuario.wantlist.id}">Ver mi wantlist</a></li>
                                        <c:if test="${usuario.rol == 'admin'}">
                                            <li><a class="dropdown-item" href="admin">Opciones admin</a></li>
                                        </c:if>
                                        <li><a class="dropdown-item" href="logout">Cerrar sesión</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                        <div>
                            <img src="${pageContext.request.contextPath}/static/images/user.png" style="width: 100px; height: 60px;">
                        </div>
                    </div>
                </div>
            </div>
        </nav>
    </header>
    <main class="bg-dark-subtle">
        <div class="container">
            <form method="post" action="nuevoDisco">
                <div class="row">
                    <div class="col-2 d-flex flex-column mt-4">
                        <h4 class="m-auto">Foto: <span style="color: red">*</span></h4>
                        <div class="mt-2">
                            <img src="${pageContext.request.contextPath}/static/images/newfoto.png" class="w-100 h-100" id="aniadirFoto">
                            <input type="file" style="display: none;" id="inputFileImage" required>
                            <input type="hidden" path="foto" name="foto">
                        </div>
                    </div>
                    <div class="col-10 mt-4">
                        <label for="inputArtista" class="col-form-label m-auto text-center">Nombre de formación o artista: <span style="color: red">*</span></label><br>
                        <input path="artista" type="text" name="artista" class="form-control" id="inputArtista" required>
                        <label for="inputNombre" class="col-form-label m-auto text-center">Nombre de la canción / disco: <span style="color: red">*</span></label><br>
                        <input path="nombre" type="text" name="nombre" class="form-control" id="inputNombre" required>
                        <label for="inputAnio" class="col-form-label m-auto text-center">Año de lanzamiento: <span style="color: red">*</span></label><br>
                        <input path="anio" type="number" name="anio" class="form-control" id="inputAnio" min="1948" max="2024" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12">
                        <label for="inputPais" class="col-form-label m-auto text-center mt-2">País de lanzamiento: <span style="color: red">*</span></label><br>
                        <input path="pais" type="text" name="pais" class="form-control" id="inputPais" required>
                        <label for="inputGenero" class="col-form-label m-auto text-center">Género: <span style="color: red">*</span></label><br>
                        <select path="genero" name="genero" id="inputGenero" class="form-select">
                            <c:forEach var="gener" items="${generos}">
                                <option value="${gener.nombre}">${gener.nombre}</option>
                            </c:forEach>
                        </select>
                        <label for="inputFormato" class="col-form-label m-auto text-center">Formato: <span style="color: red">*</span></label><br>
                        <select path="formato" name="formato" id="inputFormato" class="form-select">
                            <c:forEach var="forma" items="${formatos}">
                                <option value="${forma.nombre}">${forma.nombre}</option>
                            </c:forEach>
                        </select>
                        <label for="inputSello" class="col-form-label m-auto text-center">Sello: <span style="color: red">*</span></label><br>
                        <input path="sello" type="text" name="sello" class="form-control" id="inputSello" required>
                        <label for="inputNotas" class="col-form-label m-auto text-center">Notas:</label><br>
                        <textarea path="notas" type="textarea" name="notas" class="form-control mb-4" id="inputNotas"></textarea>
                        <input type="submit" class="btn btn-success w-100 mb-5" value="Crear nuevo disco" id="btnCrearDisco">
                    </div>
                </div>
            </form>
        </div>
    </main>
    <footer class="d-flex flex-wrap justify-content-between align-items-center py-3 border-top bg-dark">
        <p class="col-md-4 mb-0 text-white ms-5">&copy; Arturo Navarro Balbuena 2024</p>

        <a href="${pageContext.request.contextPath}/" class="col-md-3 d-flex align-items-center justify-content-center mb-3 mb-md-0 mt-3 link-body-emphasis text-decoration-none">
            <img class="logo" src="${pageContext.request.contextPath}/static/images/vinylhub-nobg.png">
        </a>

        <ul class="nav col-md-4 justify-content-end me-5">
            <li class="nav-item"><a href="#" class="nav-link px-2 text-white">Home</a></li>
            <li class="nav-item"><a href="#" class="nav-link px-2 text-white">Features</a></li>
            <li class="nav-item"><a href="#" class="nav-link px-2 text-white">Pricing</a></li>
            <li class="nav-item"><a href="#" class="nav-link px-2 text-white">FAQs</a></li>
            <li class="nav-item"><a href="#" class="nav-link px-2 text-white">About</a></li>
        </ul>
    </footer>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/static/js/foto.js"></script>
</body>
</html>