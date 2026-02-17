import {Emp} from "./entities/Emp.entity.js";

const findAll = async () => {
    return await Emp.findAll();
}

const findOneById = async (empno) => {
  return await Emp.findOne({ where: { empno: empno }});
}
const findEmpDepts = async (deptno) => {
    return await Emp.findAll({where: {deptno: deptno}});
}
const create = async(emp) => {
    return Emp.create(emp);
}

export const empRepository ={
  findAll,
  findOneById,
  create,
  findEmpDepts
}