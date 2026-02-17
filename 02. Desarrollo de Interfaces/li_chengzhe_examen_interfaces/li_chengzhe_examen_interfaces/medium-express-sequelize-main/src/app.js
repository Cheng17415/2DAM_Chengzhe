import express from 'express';
import fabricanteRouter from './routes/fabricantes.js';
import productoRouter from './routes/productos.js';
import { sequelize } from './db/db.config.js';
import { Fabricante } from './fabricante/entities/Fabricante.entity.js';
import { Producto } from './producto/entities/Producto.entity.js';

const app = express();

const PORT = 3000;

app.use(express.json());

app.get('/', (req, res) => {
  res.send('Hola mundo! Los end points disponibles son /fabricante y /producto.');
});

// END POINT: fabricante
app.use('/fabricante', fabricanteRouter);

// END POINT: producto
app.use('/producto', productoRouter);

try{
  Fabricante.hasMany(Producto, {
    foreignKey: 'id_fabricante'
  });

  Producto.belongsTo(Fabricante, {
    foreignKey: 'id_fabricante'
  });
  //Si es true, siempre que ejecutemos crea las tablas
  await sequelize.sync({ force: false });
  console.log('Conexion con la BBDD establecida');
} catch(error) {
  console.log('BBDD no conectado', error);
}

export default app.listen(PORT | 3000, () => {
  console.log(`Servidor escuchando en el puerto ${PORT}`);
}); 