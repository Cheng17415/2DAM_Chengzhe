import { Sequelize } from "sequelize";
import { sequelize }  from "../../db/db.config.js";

export const Fabricante = sequelize.define('fabricante',
  {
    id_fabricante: {
      type: Sequelize.INTEGER,
      autoIncrement: true,
      primaryKey: true
    },
    nombre: {
      type: Sequelize.STRING,
      allowNull: false
    },
    nif_cif: {
      type: Sequelize.STRING,
      unique: true
    },
    pais:{
      type: Sequelize.STRING
    },
    sitio_web:{
      type: Sequelize.STRING
    }
  },
  {
    timestamps: false
  }
);