import { Producto } from "./entities/Producto.entity.js";
import {Fabricante} from "../fabricante/entities/Fabricante.entity.js";

const findAll = async () => {
    return await Producto.findAll();
}

const findOneById = async (id_producto) => {
  return await Producto.findOne({ where: { id_producto: id_producto }});
}

const findFabrProd = async (id_fabricante) => {
  return await Producto.findAll({ where: { id_fabricante: id_fabricante }});
}

const create = async (producto) => {
  return Producto.create(producto);
}

const update = async(producto, id_producto) => {
  return await Producto.update(producto,{
    where:{id_producto:id_producto}
  });
}

const eliminar = async(id_producto) => {
  return await Producto.destroy({where:{id_producto:id_producto}});
}

export const productoRepository ={
  findAll,
  findOneById,
  create,
  findFabrProd,
  update,
  eliminar
}