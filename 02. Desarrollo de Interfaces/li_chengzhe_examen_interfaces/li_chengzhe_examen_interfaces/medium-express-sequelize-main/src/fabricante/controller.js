import { fabricanteService } from './service.js';

// GET:

const findAll = async (req, res) => {
  res.status(200).json(await fabricanteService.findAll());
}

const findOneById = async (req, res) => {
  res.status(200).json(await fabricanteService.findOneById(+req.params.id));
}

// END GET

// POST:

const create = async (req, res) => {
  res.status(201).json(await fabricanteService.create(req.body));
}

// END POST

// PUT:

const update = async (req, res) => {
  await fabricanteService.update(req.body, req.params.id);
  res.status(200).json("Se ha modificado con exito el fabricante " + req.params.id);
}
// END PUT

// DELETE:

const eliminar = async (req, res) => {
  await fabricanteService.eliminar(req.params.id)
  res.status(200).json("Se ha eliminado con exito el fabricante " + req.params.id);
}

// END DELETE

export const fabricanteController = {
  findAll,
  findOneById,
  create,
  update,
  eliminar
}