import express from 'express';
import userRouter from './routes/emps.js';
import deptRouter from './routes/depts.js';
import { sequelize } from './db/db.config.js';
import { Emp } from './emps/entities/Emp.entity.js';
import { Dept } from './depts/entities/Dept.entity.js';

const app = express();

const PORT = 3000;

app.use(express.json());

app.get('/', (req, res) => {
  res.send('Hey world!');
});

// END POINT: emps
app.use('/emps', userRouter);

// END POINT: depts
app.use('/depts', deptRouter);

try{
  Dept.hasMany(Emp, {
    foreignKey: 'deptno'
  });

  Emp.belongsTo(Dept, {
    foreignKey: 'deptno'
  });
  //Si es true, siempre que ejecutemos crea las tablas
  await sequelize.sync({ force: true });
  console.log('Connection with DB stablished');
} catch(error) {
  console.log('DB not connected', error);
}

export default app.listen(PORT | 3000, () => {
  console.log(`Server listening on port ${PORT}`);
}); 