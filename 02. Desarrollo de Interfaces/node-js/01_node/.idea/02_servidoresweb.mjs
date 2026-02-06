import { createServer } from 'node:http';

const server = createServer((req, res) => {
    res.writeHead(200, { 'Content-Type': 'text/html' });
    res.end(`
    <!DOCTYPE html>
    <html>
      <head>
        <title>Simple Page</title>
      </head>
      <body>
        <h1>Hello from Node.js!</h1>
      </body>
    </html>
  `);
});

server.listen(3000, '127.0.0.1', () => {
    console.log('Server running at http://127.0.0.1:3000/');
});