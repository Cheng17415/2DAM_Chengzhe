import { Sequelize } from "sequelize";
import { sequelize }  from "../../db/db.config.js";

export const Emp = sequelize.define('emp',
  {
    empno: {
      type: Sequelize.INTEGER,
      autoIncrement: true,
      primaryKey: true
    },
    ename: {
      type: Sequelize.STRING,
    },
    job: {
      type: Sequelize.STRING,
    },
    mgr:{
        type : Sequelize.INTEGER,
    },
      sal:{
        type: Sequelize.DOUBLE,
      },
      comm:{
          type: Sequelize.DOUBLE,
      },
    deptno: {
      type: Sequelize.INTEGER,
    },
    hiredate: {
        type: 'DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL',
        defaultValue: () => new Date()
    }
  },
  {
    timestamps: false
  }
);