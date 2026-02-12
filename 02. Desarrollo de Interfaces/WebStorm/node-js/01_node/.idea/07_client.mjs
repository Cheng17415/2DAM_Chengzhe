import net from 'node:net';

const client = new net.Socket();

const puerto = 3000;
const host = 'localhost'; // Cambia por el host donde está el servidor

client.connect(puerto, host, () => {
    console.log('Conectado al servidor TCP');
    client.write('Hola desde el cliente TCP');
});

client.on('data', (data) => {
    console.log('Datos recibidos del servidor:', data.toString());
    client.end();
});

client.on('close', () => {
    console.log('Conexión cerrada');
});

client.on('error', (err) => {
    console.error('Error de conexión:', err);
});