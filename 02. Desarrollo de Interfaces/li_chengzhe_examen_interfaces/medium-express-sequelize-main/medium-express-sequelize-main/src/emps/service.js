import { empRepository } from './repository.js';
import { deptService } from '../depts/service.js';
import {Emp} from "./entities/Emp.entity.js";

const findAll = async () => {
    const emps = await empRepository.findAll();
    return {
      emps: emps
    }
}

const findOneById = async (id) => {
  console.log('emp id service ', id)
  const emp = await empRepository.findOneById(id);

  return {
    empno: emp.empno,
    ename: emp.ename,
    job: emp.job,
    mgr: emp.mgr,
    sal: emp.sal,
    comm: emp.comm,
    deptno: emp.deptno
  }
}

const findEmpDepts = async (deptno) => {
  return await empRepository.findEmpDepts(deptno)
}
const create = async (userObj) => {
  const newEmp = await empRepository.create(userObj);
  return {
    user: newEmp
  }
}

export const empService = {
  findAll,
  findOneById,
  create,
  findEmpDepts
}