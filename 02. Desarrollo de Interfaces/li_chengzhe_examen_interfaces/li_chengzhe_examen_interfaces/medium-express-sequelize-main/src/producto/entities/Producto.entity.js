import { DECIMAL, Sequelize} from "sequelize";
import { sequelize }  from "../../db/db.config.js";

export const Producto = sequelize.define('producto',
  {
    id_producto: {
      type: Sequelize.INTEGER,
      autoIncrement: true,
      primaryKey: true
    },
    nombre: {
      type: Sequelize.STRING,
      allowNull: false
    },
    precio_venta: {
      type: DECIMAL(10,2),
      allowNull: false
    },
    stock: {
      type: Sequelize.INTEGER,
      defaultValue: 0
    },
    categoria: {
      type: Sequelize.STRING
    },
    id_fabricante: {
      type: Sequelize.INTEGER,
    },
  },
  {
    timestamps: false
  }
);