<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>VinylHub - Admin</title>
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
<body>
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
                                        <li><a class="dropdown-item" href="wantlist/${usuario.id}">Ver mi wantlist</a></li>
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
    <main>
        <div class="container" style="min-height: 80vh;">
            <div class="row m-3">
                <div class="col-8 d-flex flex-row m-auto">
                    <form:form method="get" action="adminPosts" class="w-100">
                        <button class="btn btn-success p-3 rounded-4 w-100" type="submit">Posts</button>
                    </form:form>
                </div>
            </div>
            <div class="row m-3">
                <div class="col-8 d-flex flex-row m-auto">
                    <form:form method="get" action="adminDiscos" class="w-100">
                        <button class="btn btn-success p-3 rounded-4 w-100" type="submit">Discos</button>
                    </form:form>
                </div>
            </div>
            <div class="row m-3">
                <div class="col-8 d-flex flex-row m-auto">
                    <form:form method="get" action="adminUsuarios" class="w-100">
                        <button class="btn btn-success p-3 rounded-4 w-100">Usuarios</button>
                    </form:form>
                </div>
            </div>
            <div class="row m-3">
                <div class="col-8 d-flex flex-row m-auto">
                    <form:form method="get" action="adminFormatos" class="w-100">
                        <button class="btn btn-success p-3 rounded-4 w-100">Formatos</button>
                    </form:form>
                </div>
            </div>
            <div class="row m-3">
                <div class="col-8 d-flex flex-row m-auto">
                    <form:form method="get" action="adminGeneros" class="w-100">
                        <button class="btn btn-success p-3 rounded-4 w-100">Géneros</button>
                    </form:form>
                </div>
            </div>
        </div>
    </main>
    <footer class="d-flex flex-wrap justify-content-between align-items-center py-3 border-top bg-dark sticky-bottom">
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
</body>
</html>
