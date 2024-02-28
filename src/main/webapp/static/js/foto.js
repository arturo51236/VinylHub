var nuevaFoto = document.getElementById('aniadirFoto');
var inputNuevaFoto = document.getElementById('inputFileImage');

nuevaFoto.addEventListener('click', function() {
    inputNuevaFoto.click();
});

inputNuevaFoto.addEventListener('change', function() {
    if (inputNuevaFoto.files && inputNuevaFoto.files[0]) {
        var reader = new FileReader();

        reader.onload = function(e) {
            nuevaFoto.src = e.target.result;
        };

        reader.readAsDataURL(inputNuevaFoto.files[0]);

        var hiddenInput = document.getElementsByName('foto')[0];
        hiddenInput.value = inputNuevaFoto.files[0].name;
    }
});

// TODO:
//
// var botonCrearDisco = document.getElementById('btnCrearDisco');
//
// botonCrearDisco.addEventListener('click', function () {
//     mandarFotoAControlador();
// });
//
// function mandarFotoAControlador() {
//     const datos = new FormData();
//     datos.append('foto', inputNuevaFoto.files[0]);
//     const options = {
//         method: "POST",
//         body: datos,
//         headers: {
//             'Content-Type': 'multipart/form-data'
//         }
//     };
//
//     let fixedURI = window.location.href.substring(0, window.location.href.lastIndexOf('/'));
//     fetch(fixedURI + '/guardarFoto', options)
//         .then(respuesta => respuesta.json())
//         .then(status => {
//             console.log(status.resultado)
//         })
//         .catch(error => console.log(error));
// }