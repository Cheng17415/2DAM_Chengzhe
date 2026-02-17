import {productoRepository} from './repository.js';

const findAll = async () => {
    const productos = await productoRepository.findAll();
    return {
      productos: productos
    }
}

const findOneById = async (id) => {
  const producto = await productoRepository.findOneById(id);
  return {
    producto: producto
  }
}

const findFabrProd = async (id_fabricante) => {
  return await productoRepository.findFabrProd(id_fabricante);
}

const create = async (producto) => {
  const newProducto = await productoRepository.create(producto);
  return {
    producto: newProducto
  }
}

const update = async (producto, id_producto) => {
  const modProducto = await productoRepository.update(producto, id_producto);
  return {
    producto: modProducto
  }
}

const eliminar = async (id_producto) => {
  return await productoRepository.eliminar(id_producto);
}
export const productoService = {
  findAll,
  findOneById,
  create,
  findFabrProd,
  update,
  eliminar
}