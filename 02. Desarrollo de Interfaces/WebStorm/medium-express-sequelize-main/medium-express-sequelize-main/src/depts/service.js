import { deptRepository } from './repository.js';
import {empService} from "../emps/service.js";

const findAll = async () => {
    const depts = await deptRepository.findAll();
    return {
      depts: depts
    }
}

const findOneById = async (deptno) => {
  const dept = await deptRepository.findOneById(deptno);
  const emps = await findDeptEmps(deptno);
  return {
    deptno: dept.deptno,
      dname: dept.dname,
      loc: dept.loc,
      emps: emps
  }
}

const findDeptEmps = async (deptno) => {
    return await empService.findEmpDepts(deptno);
}
const create = async (dept) => {
  const newDept = await deptRepository.create(dept);
  return {
    dept: newDept
  }
}

export const deptService = {
  findAll,
  findOneById,
  create,
  findDeptEmps
}