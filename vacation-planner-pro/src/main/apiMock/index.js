const path = require("path");
const jsonServer = require("json-server");
const server = jsonServer.create();
const router = jsonServer.router(path.join(__dirname, "db.json"));
const middlewares = jsonServer.defaults();
const cors = require("cors");

server.use(
  cors({
    origin: true,
    credentials: true,
    preflightContinue: true,
    methods: "GET,HEAD,PUT,PATCH,POST,DELETE",
  })
);
server.options("*", cors());

server.use(middlewares);
server.use(jsonServer.bodyParser);
server.use(router);

server.listen(3000, () => {
  console.log(
    "\u001b[35mJSON Server is running on \u001b[34mhttp://localhost:3000/\u001b[0m"
  );
});
