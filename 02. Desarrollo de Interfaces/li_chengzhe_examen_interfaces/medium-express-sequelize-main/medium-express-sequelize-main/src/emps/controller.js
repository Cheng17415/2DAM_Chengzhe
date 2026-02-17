import { empService } from './service.js';

// GET:

const findAll = async (req, res) => {
  res.status(200).json(await empService.findAll());
}

const findOneById = async (req, res) => {
  res.status(200).json(await empService.findOneById(+req.params.empno));
}

// END GET

// POST:

const create = async (req, res) => {
  res.status(201).json(await empService.create(req.body));
}

// END POST

export const userController = {
  findAll,
  findOneById,
  create,
}