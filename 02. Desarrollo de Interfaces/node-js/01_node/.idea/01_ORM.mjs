import {Sequelize} from "@sequelize/core";
import {MySqlDialect} from '@sequelize/mysql';

// Configuración de la conexión a la base de datos
const sequelize = new Sequelize( {
    host: 'localhost',
    dialect: MySqlDialect,
    port: 3306,
    user: 'root',
    password: 'root',
    database: 'bd_scott'
});

const Usuario = sequelize.define('nuevoUsuario', {
    nombre: {
        type: Sequelize.STRING,
        allowNull: false,
    },
    edad: {
        type: Sequelize.INTEGER,
        allowNull: false,
    },
    correo: {
        type: Sequelize.STRING,
        allowNull: false,
        unique: true,
    },
});

(async () => {
    await sequelize.sync({ force: true }); // Sincronizar modelo con la base de datos

    // Crear un nuevo usuario
    const nuevoUsuario = await Usuario.create({
        nombre: 'Luis',
        edad: 25,
        correo: 'luis@example.com',
    });

    console.log('Usuario creado:', nuevoUsuario.toJSON());

    // Consultar usuarios
    const usuarios = await Usuario.findAll();
    console.log('Usuarios encontrados:', usuarios.map(u => u.toJSON()));

    // Actualizar usuario
    await Usuario.update({ edad: 30 }, { where: { nombre: 'Luis' } });

    // Eliminar usuario
    await Usuario.destroy({ where: { nombre: 'Luis' } });
})();