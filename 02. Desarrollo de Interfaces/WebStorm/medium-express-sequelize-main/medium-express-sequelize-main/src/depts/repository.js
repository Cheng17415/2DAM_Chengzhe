import {Dept} from "./entities/Dept.entity.js";

const findAll = async () => {
    return await Dept.findAll();
}

const findOneById = async (deptno) => {
  return await Dept.findOne({ where: { empno : deptno }});
}

const findUserPosts = async (deptno) => {
  return await Dept.findAll({ where: { deptno: deptno }});
}

const create = async (dept) => {
  return Dept.create(dept);
}

export const deptRepository ={
  findAll,
  findOneById,
  create
}