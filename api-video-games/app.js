//ESTO ES COMO UN IMPORT, LO SACA EN EL NODE MODULES
//ES COMO EL MAIN
const express = require("express");
//creamos la aplicacion instanciando express
const app = express();

//es el port 3000 y es por default, pero se puede cambiar
const PORT = 3000;

//declaramos un objeto
const games = [
    {"id": 1, "name": "Among Us", "genre": "strategy"},
    {"id": 2, "name": "Fornite", "genre": "action"},
    {"id": 3, "name": "Minecraft", "genre": "adventure"},
    {"id": 4, "name": "GTA V", "genre": "action and adventure"},
    {"id": 5, "name": "Call of Duty", "genre": "action"}
];

//creamos un endpoint pasandole nuestro objecto de games, y le pasamos un callback que tiene dos parametros, request y response, y le decimos que nos devuelva un json con el objeto games
app.get("/games", (req, res) => {
    res.json(games);
});

app.get("/games2", (req, res) => {
    res.json(games);
});
//pasamos el puerto y le pasamos una funcion de callback, que es lo que va a hacer cuando se levante el servidor
app.listen(PORT, () => {
    console.log("App is running on port " + PORT);
});

//la arquitectura la vamos a seguir ocupando, lo vamos a aplicar a aca de lo de java

//el package json es como el pom.xml de java, es un archivo de configuracion, donde se declaran las dependencias, scripts, etc.
//node es un entorno de ejecucion de javascript, que nos permite ejecutar javascript en el servidor, y no solo en el navegador

