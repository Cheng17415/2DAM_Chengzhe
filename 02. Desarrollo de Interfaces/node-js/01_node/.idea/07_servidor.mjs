import net from 'node:net';

// Crear servidor TCP
const server = net.createServer((socket) => {
    console.log('Cliente conectado');

    // Evento al recibir datos del cliente
    socket.on('data', (data) => {
        console.log('Datos recibidos:', data.toString());

        // Enviar datos de vuelta al cliente
        socket.write('Datos recibidos por el servidor: ' + data.toString());
    });

    // Evento cuando se cierra la conexión del cliente
    socket.on('close', () => {
        console.log('Cliente desconectado');
    });

    // Manejar errores de conexión
    socket.on('error', (err) => {
        console.error('Error en conexión:', err);
    });
});

const puerto = 3000;
server.listen(puerto, () => {
    console.log(`Servidor TCP iniciado en puerto ${puerto}`);
});