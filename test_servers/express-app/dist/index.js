"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const express_1 = __importDefault(require("express"));
const dotenv_1 = __importDefault(require("dotenv"));
const ResultType_1 = require("./ResultType");
dotenv_1.default.config();
const app = (0, express_1.default)();
const port = process.env.PORT;
app.get('/', (req, res) => {
    res.send(new ResultType_1.ResultType("ok", "message", "Hello World"));
});
app.post('/testPost', (req, res) => {
    let req_body = req.body;
    res.send(new ResultType_1.ResultType("ok", "message", req_body));
});
app.listen(port, () => {
    console.log(`[server] : listening on port ${port}`);
});
