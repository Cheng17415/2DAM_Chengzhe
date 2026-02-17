import express from 'express';
import { productoController } from '../producto/controller.js';

const productoRouter = express.Router();

productoRouter.get('/', productoController.findAll);
productoRouter.get('/:id', productoController.findOneById);
productoRouter.post('/', productoController.create);
productoRouter.put('/:id', productoController.update);
productoRouter.delete('/:id', productoController.eliminar);

export default productoRouter;