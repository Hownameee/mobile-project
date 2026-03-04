import express from "express";
import { initDatabase } from "./utils/db/init.js";
import restResponse from "./middlewares/restResponse.js";
import notFound from "./middlewares/notFound.js";
import errorHandler from "./middlewares/errorHandler.js";

const app = express();
initDatabase();

app.use(bodyParser.json());
app.use(cors());
app.use(restResponse);

// routes here

app.use(notFound);
app.use(errorHandler);

export default app;
