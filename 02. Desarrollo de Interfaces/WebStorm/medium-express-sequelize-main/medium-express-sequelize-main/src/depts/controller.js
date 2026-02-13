import { deptService } from './service.js';

const findAll = async (req, res) => {
  res.status(200).json(await deptService.findAll());
}

const findOneById = async (req, res) => {
  res.status(200).json(await deptService.findOneById(+req.params.deptno));
}

const create = async (req, res) => {
  res.status(201).json(await deptService.create(req.body));
}

export const deptController = {
  findAll,
  findOneById,
  create
}