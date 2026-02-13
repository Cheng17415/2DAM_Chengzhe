import { Sequelize }  from "sequelize";

export const sequelize = new Sequelize('sequelize_practica', 'root', 'root', {
  dialect: 'mysql',
  host: 'localhost'
});
