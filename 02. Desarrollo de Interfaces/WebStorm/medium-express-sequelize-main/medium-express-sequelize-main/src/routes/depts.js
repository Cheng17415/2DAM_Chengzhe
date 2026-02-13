import express from 'express';
import { deptController } from '../depts/controller.js';

const deptRouter = express.Router();

deptRouter.get('/', deptController.findAll);
deptRouter.get('/:id', deptController.findOneById);
deptRouter.post('/', deptController.create);

export default deptRouter;