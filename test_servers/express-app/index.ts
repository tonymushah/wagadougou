import express, { Express, Request, Response } from 'express';
import dotenv from 'dotenv';
import { ResultType } from './ResultType';

dotenv.config()

const app : Express = express()
const port = process.env.PORT;

app.get('/' , (req : Request, res : Response) => {
    res.send(new ResultType<string>("ok", "message", "Hello World"));
})

app.post('/testPost', (req : Request , res : Response) => {
    let req_body : string = req.body;
    res.send(new ResultType<String>("ok", "message", req_body));
})

app.listen(port , () => {
    console.log(`[server] : listening on port ${port}`);
})

