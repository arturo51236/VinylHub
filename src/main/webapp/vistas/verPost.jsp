<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${post.disco.artista} - ${post.disco.nombre}</title>
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

        #img-disco {
            height: 300px;
            width: 300px;
        }

        .head-text {
            font-family: "Montserrat Alternates", sans-serif;
            font-style: normal;
        }
    </style>
</head>
<body>
    <header>
        <nav class="navbar navbar-expand-lg bg-body-tertiary" data-bs-theme="dark">
            <div class="container-fluid m-auto">
                <li class="nav-item h-100 d-flex mt-2 mt-sm-3 mt-md-3 mt-lg-0 align-items-center">
                    <a class="nav-link text-white mx-2" href="${pageContext.request.contextPath}/"><img class="logo" src="${pageContext.request.contextPath}/static/images/vinylhub-nobg.png"></a>
                    <a class="navbar-brand ms-3" href="${pageContext.request.contextPath}/" style="font-size: 25px;">VinylHub</a>
                </li>
                <div class="container d-flex flex-column text-center m-auto">
                    <form:form action="../search" class="d-flex" role="search" method="get">
                        <input class="form-control me-2" name="valor" type="search" placeholder="Search" aria-label="Search">
                        <button class="btn btn-success" type="submit">Buscar</button>
                    </form:form>
                </div>
                <div>
                    <c:choose>
                        <c:when test="${usuario != null}">
                            <div class="container d-flex flex-row align-items-center">
                                <form:form action="../nuevoDisco" method="get">
                                    <button class="btn btn-warning" type="submit">Nuevo post</button>
                                </form:form>
                                <div class="text-white ms-3">
                                    <ul class="navbar-nav">
                                        <li class="nav-item dropdown">
                                            <button class="btn btn-dark dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                                                ${usuario.nombre}
                                            </button>
                                            <ul class="dropdown-menu dropdown-menu-dark">
                                                <li><a class="dropdown-item" href="../wantlist/${usuario.wantlist.id}">Ver mi wantlist</a></li>
                                                <c:if test="${usuario.rol == 'admin'}">
                                                    <li><a class="dropdown-item" href="../admin">Opciones admin</a></li>
                                                </c:if>
                                                <li><a class="dropdown-item" href="../logout">Cerrar sesión</a></li>
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
                            <form:form action="../login" method="get">
                                <button class="btn btn-warning" type="submit">Iniciar sesión</button>
                            </form:form>
                        </c:when>
                    </c:choose>
                </div>
            </div>
        </nav>
    </header>
    <main>
        <div class="container bg-dark pb-5">
            <div class="row" style="border-left: 20px solid #212529">
                <div class="col-9 d-flex flex-row bg-white mt-3" style="border-start-start-radius: 15px; border-start-end-radius: 15px;">
                    <div>
                        <img id="img-disco" class="m-4 ms-3" style="border-radius: 15px" src="${pageContext.request.contextPath}/static/images-discos/${post.disco.foto}">
                    </div>
                    <div class="mt-3">
                        <h1 class="head-text">${post.disco.artista} - ${post.disco.nombre}</h1>
                        <h5 class="mt-3">Sello: ${post.sello}</h5>
                        <h5 class="mt-3">Año: ${post.disco.anio}</h5>
                        <h5 class="mt-3">País: ${post.disco.pais}</h5>
                        <h5 class="mt-3">Formato: ${post.disco.formato.nombre}</h5>
                        <h5 class="mt-3">Género: ${post.disco.genero.nombre}</h5>
                    </div>
                </div>
                <div class="col-3 bg-dark d-flex">
                    <c:choose>
                        <c:when test="${usuario != null}">
                            <form:form action="../aniadirAWantlist" method="post" class="w-100 mt-3">
                                <c:choose>
                                    <c:when test="${repetido == false}">
                                        <input type="hidden" name="idPost" value="${post.id}">
                                        <button type="submit" class="btn btn-warning w-100">Añadir a mi wantlist</button>
                                    </c:when>
                                    <c:when test="${repetido == true}">
                                        <div class="d-flex flex-row bg-white rounded-3">
                                            <h5 class="m-auto p-3">Ya tienes este disco en tu wantlist</h5>
                                        </div>
                                    </c:when>
                                </c:choose>
                            </form:form>
                        </c:when>
                        <c:when test="${usuario == null}">
                            <form:form action="../login" method="get" class="w-100 mt-3">
                                <button type="submit" class="btn btn-warning w-100">Añadir a mi wantlist</button>
                            </form:form>
                        </c:when>
                    </c:choose>
                </div>
            </div>
            <div class="row" style="border-left: 20px solid #212529">
                <div class="col-9 bg-white" style="border-end-start-radius: 15px; border-end-end-radius: 15px;">
                    <h5 class="ms-3">Notas: </h5>
                    <c:choose>
                        <c:when test="${post.disco.notas != null}">
                            <p class="ms-3">${post.disco.notas}</p>
                        </c:when>
                        <c:when test="${post.disco.notas == null}">
                            <p class="ms-3">El disco no contiene notas.</p>
                        </c:when>
                    </c:choose>
                </div>
                <div class="col-3 bg-dark">

                </div>
            </div>
            <div class="row">
                <div class="col-9 text-center text-white py-3">
                    <h2 class="head-text my-2">Discos relacionados</h2>
                </div>
            </div>
            <div class="row" style="border-left: 20px solid #212529">
                <div class="col-9 bg-white mb-2" style="border-radius: 15px">
                    <div class="row row-cols-1 row-cols-md-5 g-4 d-flex flex-row m-auto align-items-center my-3 mt-0">
                        <c:forEach var="disco" items="${discosRelacionados}">
                                <div class="col">
                                    <div class="card m-auto" data-bs-theme="dark" style="width: 170px; height: 350px;">
                                        <img src="${pageContext.request.contextPath}/static/images-discos/${disco.foto}" class="card-img-top">
                                        <div class="card-body">
                                            <div style="height: 60px">
                                                <a class="card-title" href="../verPost/${disco.post.id}" style="font-size: 17px; text-decoration: none">${disco.nombre}</a>
                                            </div>
                                            <p class="card-text">${disco.artista}</p>
                                        </div>
                                        <div class="card-footer">
                                            <small class="text-body-secondary">Número de visitas: ${disco.post.cantidadVisitas}</small>
                                        </div>
                                    </div>
                                </div>
                        </c:forEach>
                    </div>
                </div>
                <div class="col-3 bg-dark">

                </div>
            </div>
        </div>
    </main>
    <footer class="d-flex flex-wrap justify-content-between align-items-center py-3 bg-dark">
        <p class="col-md-4 mb-0 text-white ms-5">&copy; Arturo Navarro Balbuena 2024</p>

        <a href="${pageContext.request.contextPath}/" class="col-md-3 d-flex align-items-center justify-content-center mb-3 mb-md-0 link-body-emphasis text-decoration-none">
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
</body>
</html>
