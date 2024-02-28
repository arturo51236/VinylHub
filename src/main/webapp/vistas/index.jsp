<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>VinylHub</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Fira+Sans&display=swap');
        @import url('https://fonts.googleapis.com/css2?family=Montserrat+Alternates&display=swap');

        body {
            font-family: 'Fira Sans', serif;
        }

        .logo {
            width: 70px;
            height: 70px;
        }

        .carousel-inner .carousel-item {
            height: 500px;
        }

        .carousel-item img {
            position: absolute;
            object-fit: cover;
            top: 0;
            bottom: 0;
            left: 0;
            min-height: 500px;
            margin: auto;
        }

        .head-text {
            font-family: "Montserrat Alternates", sans-serif;
            font-weight: 400;
            font-style: normal;
        }
    </style>
</head>
<body class="bg-dark-subtle">
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
                    <c:choose>
                        <c:when test="${usuario != null}">
                            <div class="container d-flex flex-row align-items-center">
                                <form:form action="nuevoDisco" method="get">
                                    <button class="btn btn-warning" type="submit">Nuevo post</button>
                                </form:form>
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
                        </c:when>
                        <c:when test="${usuario == null}">
                            <form:form action="login" method="get">
                                <button class="btn btn-warning" type="submit">Iniciar sesión</button>
                            </form:form>
                        </c:when>
                    </c:choose>
                </div>
            </div>
        </nav>
    </header>
    <main class="bg-dark-subtle d-flex flex-column w-100">
        <div class="container bg-dark-subtle">
            <div class="row d-flex flex-row align-items-center">
                <div class="col-8">
                    <div id="carouselExampleAutoplaying" class="carousel slide" data-bs-ride="carousel" data-bs-theme="dark">
                        <div class="carousel-inner">
                            <div class="carousel-item active">
                                <img src="${pageContext.request.contextPath}/static/images/carousel1.jpg" class="d-block w-100">
                            </div>
                            <div class="carousel-item">
                                <img src="${pageContext.request.contextPath}/static/images/carousel2.jpg" class="d-block w-100">
                            </div>
                            <div class="carousel-item">
                                <img src="${pageContext.request.contextPath}/static/images/carousel3.jpg" class="d-block w-100">
                            </div>
                            <div class="carousel-item">
                                <img src="${pageContext.request.contextPath}/static/images/carousel4.jpg" class="d-block w-100">
                            </div>
                            <div class="carousel-item">
                                <img src="${pageContext.request.contextPath}/static/images/carousel5.jpg" class="d-block w-100">
                            </div>
                            <div class="carousel-item">
                                <img src="${pageContext.request.contextPath}/static/images/carousel6.jpg" class="d-block w-100">
                            </div>
                            <div class="carousel-item">
                                <img src="${pageContext.request.contextPath}/static/images/carousel7.jpg" class="d-block w-100">
                            </div>
                            <div class="carousel-item">
                                <img src="${pageContext.request.contextPath}/static/images/carousel8.jpg" class="d-block w-100">
                            </div>
                        </div>
                        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Anterior</span>
                        </button>
                        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Siguiente</span>
                        </button>
                    </div>
                </div>
                <div class="col-4 d-flex flex-column text-center text-white p-2 rounded-4 border border-2 border-white" style="background-color: #31373d">
                    <h1 class="head-text">Novedades</h1>
                    <p>Estas son las referencias más recientes, descúbrelas.</p>
                </div>
            </div>
        </div>
        <div class="container-fluid" style="background-color: #2b3035">
            <div class="row text-center m-auto text-white mt-4 head-text border-bottom">
                <h2 class="mb-3">Los más visitados - Rock</h2>
            </div>
            <div class="row">
                <div class="col-12">
                    <div class="row row-cols-1 row-cols-md-6 g-4 d-flex flex-row m-auto align-items-center">
                        <c:forEach var="discoRock" items="${top6Rock}">
                            <div class="col">
                                <div class="card my-4 mx-4 m-auto">
                                    <img src="${pageContext.request.contextPath}/static/images-discos/${discoRock.foto}" class="card-img-top">
                                    <div class="card-body">
                                        <div style="height: 60px">
                                            <a class="card-title" href="verPost/${discoRock.post.id}" style="font-size: 19px; text-decoration: none">${discoRock.nombre}</a>
                                        </div>
                                        <p class="card-text">${discoRock.artista}</p>
                                    </div>
                                    <div class="card-footer">
                                        <small class="text-body-secondary">Número de visitas: ${discoRock.post.cantidadVisitas}</small>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <div class="row text-center m-auto text-white mt-4 head-text border-bottom">
                <h2 class="mb-3">Los más visitados - Electrónica</h2>
            </div>
            <div class="row">
                <div class="col-12">
                    <div class="row row-cols-1 row-cols-md-6 g-4 d-flex flex-row m-auto align-items-center mb-4">
                        <c:forEach var="discoElectronica" items="${top6Electronica}">
                            <div class="col">
                                <div class="card my-4 mx-4 m-auto">
                                    <img src="${pageContext.request.contextPath}/static/images-discos/${discoElectronica.foto}" class="card-img-top">
                                    <div class="card-body">
                                        <div style="height: 60px">
                                            <a class="card-title" href="verPost/${discoElectronica.post.id}" style="font-size: 19px; text-decoration: none">${discoElectronica.nombre}</a>
                                        </div>
                                        <p class="card-text">${discoElectronica.artista}</p>
                                    </div>
                                    <div class="card-footer">
                                        <small class="text-body-secondary">Número de visitas: ${discoElectronica.post.cantidadVisitas}</small>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <footer class="d-flex flex-wrap justify-content-between align-items-center py-3 my-4 border-top-dark bg-dark-subtle">
        <p class="col-md-4 mb-0 text-black ms-5">&copy; Arturo Navarro Balbuena 2024</p>

        <a href="${pageContext.request.contextPath}/" class="col-md-3 d-flex align-items-center justify-content-center mb-3 mb-md-0 link-body-emphasis text-decoration-none">
            <img class="logo" src="${pageContext.request.contextPath}/static/images/vinylhub-nobg.png">
        </a>

        <ul class="nav col-md-4 justify-content-end me-5">
            <li class="nav-item"><a href="#" class="nav-link px-2 text-black">Home</a></li>
            <li class="nav-item"><a href="#" class="nav-link px-2 text-black">Features</a></li>
            <li class="nav-item"><a href="#" class="nav-link px-2 text-black">Pricing</a></li>
            <li class="nav-item"><a href="#" class="nav-link px-2 text-black">FAQs</a></li>
            <li class="nav-item"><a href="#" class="nav-link px-2 text-black">About</a></li>
        </ul>
    </footer>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>
</body>
</html>