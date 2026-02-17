import { Sequelize }  from "sequelize";

export const sequelize = new Sequelize('fabrica', 'root', 'root', {
  dialect: 'mysql',
  host: 'localhost'
});
