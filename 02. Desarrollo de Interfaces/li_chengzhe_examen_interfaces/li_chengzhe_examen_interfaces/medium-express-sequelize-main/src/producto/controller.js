import { productoService } from './service.js';

const findAll = async (req, res) => {
  res.status(200).json(await productoService.findAll());
}

const findOneById = async (req, res) => {
  res.status(200).json(await productoService.findOneById(+req.params.id));
}

const create = async (req, res) => {
  res.status(201).json(await productoService.create(req.body));
}

// PUT
const update = async (req, res) => {
  await productoService.update(req.body, req.params.id);
  res.status(200).json("Se ha actualizado con exito el producto " + req.params.id);
}
// END PUT

// DELETE:

const eliminar = async (req, res) => {
  await productoService.eliminar(req.params.id)
  res.status(200).json("Se ha eliminado con exito el producto " + req.params.id);
}

// END DELETE

export const productoController = {
  findAll,
  findOneById,
  create,
  update,
  eliminar
}