import express from 'express';
import { fabricanteController } from '../fabricante/controller.js';

const fabricanteRouter = express.Router();

fabricanteRouter.get('/', fabricanteController.findAll);
fabricanteRouter.get('/:id', fabricanteController.findOneById);
fabricanteRouter.post('/', fabricanteController.create);
fabricanteRouter.put('/:id', fabricanteController.update);
fabricanteRouter.delete('/:id', fabricanteController.eliminar);

export default fabricanteRouter;