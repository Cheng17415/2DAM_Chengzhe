import { Sequelize } from "sequelize";
import { sequelize }  from "../../db/db.config.js";

export const Dept = sequelize.define('dept',
  {
    deptno: {
      type: Sequelize.INTEGER,
      autoIncrement: true,
      primaryKey: true
    },
    dname: {
      type: Sequelize.STRING,
      allowNull: false
    },
    loc: {
      type: Sequelize.STRING,
    }
  },
  {
    timestamps: false
  }
);