import express from 'express';
import { initDatabase } from './utils/db/init.js';
import restResponse from './middlewares/restResponse.js';
import notFound from './middlewares/notFound.js';
import errorHandler from './middlewares/errorHandler.js';
import bodyParser from 'body-parser';
import cors from 'cors';
import recordRouter from './routes/record.route.js';

const app = express();
initDatabase();

app.use(bodyParser.json());
app.use(cors());
app.use(restResponse);

const apiRoute = express.Router();
// should put this in user route when possible (record usually belong to particular user)
apiRoute.use('/users/:userId/records', recordRouter);

app.use('/api', apiRoute);

app.use(notFound);
app.use(errorHandler);

export default app;
