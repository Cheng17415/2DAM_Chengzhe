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

const Clasificacion = sequelize.define('clasificacion', {
    equipo: {type: Sequelize.STRING, allowNull: false,},
    ptos: {type: Sequelize.INTEGER, allowNull: false,},
});

async function insertar(){
    await sequelize.sync({force: true});

    const matriz = [
        {equipo: "Sevilla", ptos: 45},
        {equipo: "Betis", ptos: 30},
        {equipo: "Rayo", ptos: 22}
    ];
    for(let i=0; i<matriz.length; i++){
        const nuevoEquipo = await(Clasificacion.create({
            equipo: matriz[i].equipo,
            ptos: matriz[i].ptos
        }))
    }
}

async function seleccion(){
    const equipos = await Clasificacion.findAll();
    console.log('Equipos encontrados:', equipos.map(u => u.toJSON()));
}

async function actualizar(){
    await Clasificacion.update({ptos: 55 }, { where: { equipo: "Betis" } });
}

async function eliminar(){
    await Clasificacion.destroy({ where: { equipo: 'Barcelona' } });
}

(async()=>{
    await insertar();
    await seleccion();
    await actualizar();
})();