import express from 'express';
const app = express();
const PORT = 3000;

// Middleware de registro de solicitudes
app.use((req, res, next) => {
    console.log(`Solicitud recibida en: ${req.url}`);
    next();
});

// Ruta GET en la raíz
app.get('/', (req, res) => {
    res.send('¡Hola, mundo desde Express.js!');
});

// Ruta POST para usuarios
app.post('/usuarios', (req, res) => {
    res.send('Información de usuario recibida y procesada');
});

// Ruta dinámica para usuarios
app.get('/usuarios/:id', (req, res) => {
    const { id } = req.params;
    res.send(`Solicitado usuario con ID: ${id}`);
});

// Middleware de manejo de errores
app.use((err, req, res, next) => {
    console.error(err.stack);
    res.status(500).send('Algo salió mal');
});

// Iniciar el servidor
app.listen(PORT, () => {
    console.log(`Servidor Express escuchando en el puerto ${PORT}`);
});