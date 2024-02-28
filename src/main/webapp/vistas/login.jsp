<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>VinylHub - Inicio</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Fira+Sans&display=swap');
        @import url('https://fonts.googleapis.com/css2?family=Montserrat+Alternates&display=swap');

        body {
            font-family: 'Fira Sans', serif;
            background-color: #ced4da;
        }

        .head-text {
            font-family: "Montserrat Alternates", sans-serif;
            font-weight: 400;
            font-style: normal;
        }
    </style>
</head>
<body>
    <main class="d-flex justify-content-center align-items-center w-100" style="height: 100vh;">
        <div class="container d-flex flex-column align-items-center">
            <div class="m-auto d-flex flex-column rounded-3 border border-3 border-white" style="background-color: #2b3035; width: 30%;">
                <div class="row text-center m-auto text-white mt-4 head-text">
                    <h2>Inicio de sesión</h2>
                </div>
                <%--@elvariable id="usuario" type="es.arturonb.modelos.Usuario"--%>
                <form:form method="post" action="login" modelAttribute="usuario">
                    <div class="row my-3 m-auto">
                        <label for="inputEmail" class="col-form-label m-auto text-center text-white">Email:</label><br>
                        <div class="col-sm-10 w-100">
                            <form:input type="email" name="email" class="form-control" id="inputEmail" placeholder="Introduce tu email" path="email"></form:input>
                        </div>
                    </div>
                    <div class="row my-3 m-auto">
                        <label for="inputPassword" class="col-form-label m-auto text-center text-white">Contraseña:</label><br>
                        <div class="col-sm-10 w-100 mb-4">
                            <form:input type="password" name="password" class="form-control" id="inputPassword" placeholder="Introduce tu contraseña" path="contrasenia"></form:input>
                        </div>
                    </div>
                    <div class="row m-auto">
                        <div class="col-sm-10 w-100 mb-3">
                            <button type="submit" class="btn btn-primary w-100">Acceder</button>
                        </div>
                    </div>
                </form:form>
                <div class="row my-3 m-auto text-center">
                    <label class="text-white">¿Aún no tienes cuenta en VinylHub?</label><a href="register">Registro</a>
                </div>
            </div>
        </div>
    </main>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>
</body>
</html>
