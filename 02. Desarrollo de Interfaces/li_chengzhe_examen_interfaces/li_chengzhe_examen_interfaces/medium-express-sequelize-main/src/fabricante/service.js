import {fabricanteRepository} from './repository.js';
import {productoService} from '../producto/service.js';

const findAll = async () => {
    const fabricantes = await fabricanteRepository.findAll();
    return {
      fabricantes: fabricantes
    }
}

const findOneById = async (id_fabricante) => {
  console.log('fabricante id service ', id_fabricante)
  const fabricante = await fabricanteRepository.findOneById(id_fabricante);
  const productos = await findFabrProd(id_fabricante);
  return {
    id_fabricante: fabricante.id_fabricante,
    nombre: fabricante.nombre,
    nif_cif: fabricante.nif_cif,
    pais: fabricante.pais,
    sitio_web: fabricante.sitio_web,
    productos: productos
  }
}

const findFabrProd = async (id_fabricante) => {
  return await productoService.findFabrProd(id_fabricante);
}

const create = async (fabricanteObj) => {
  const newFabricante = await fabricanteRepository.create(fabricanteObj);
  return {
    fabricante: newFabricante
  }
}

const update = async (fabricanteObj, id_fabricante) => {
  const modFabricante = await fabricanteRepository.update(fabricanteObj,id_fabricante);
  return {
    fabricante: modFabricante
  }
}

const eliminar = async (id_fabricante) => {
    return await fabricanteRepository.eliminar(id_fabricante);
}

export const fabricanteService = {
  findAll,
  findOneById,
  create,
  update,
  eliminar
}