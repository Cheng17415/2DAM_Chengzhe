import { Fabricante } from "./entities/Fabricante.entity.js";

const findAll = async () => {
    return await Fabricante.findAll();
}

const findOneById = async (id) => {
  return await Fabricante.findOne({ where: { id_fabricante: id }});
}

const create = async(fabricante) => {
  return Fabricante.create(fabricante);
}

const update = async(fabricante, id_fabricante) => {
    return await Fabricante.update(fabricante,{
        where:{id_fabricante:id_fabricante}
    });
}

const eliminar = async(id_fabricante) => {
    return await Fabricante.destroy({where:{id_fabricante:id_fabricante}});
}

export const fabricanteRepository ={
  findAll,
  findOneById,
  create,
  update,
  eliminar
}