const path = require("path");
const express = require("express");
const fs = require("fs");
const cors = require("cors");
const session = require("express-session");
const cookieParser = require("cookie-parser");

const app = express();
const PORT = 3000;

app.use(
  cors({
    origin: "http://localhost:5173",
    credentials: true,
  })
);
app.use(express.json());
app.use(cookieParser());
app.use(
  session({
    secret: "secret",
    resave: false,
    saveUninitialized: false,
    cookie: {
      secure: false,
      maxAge: 1000 * 60 * 60 * 1,
    },
  })
);

const dbPath = path.join(__dirname, "db.json");

const loadUsers = () => {
  const data = fs.readFileSync(dbPath);
  return JSON.parse(data).users;
};

const saveUsers = (users) => {
  const data = JSON.stringify({ users });
  fs.writeFileSync(dbPath, data);
};

app.post("/login", (req, res) => {
  const { username, password, companyId } = req.body;
  const users = loadUsers();

  const user = users.find(
    (user) =>
      user.username === username &&
      user.password === password &&
      user.companyId === companyId
  );

  if (user) {
    const authToken = "auth-token";
    res.cookie("authToken", authToken, {
      httpOnly: false,
      sameSite: "none",
      secure: true,
    });
    res.status(200).json({ message: "OK" });
  } else {
    res.status(401).json({ message: "Unauthorized" });
  }
});

app.post("/signup", (req, res) => {
  const { username, password, companyId } = req.body;
  console.log(req.body);
  const users = loadUsers();

  const existingUser = users.find(
    (user) => user.username === username && user.companyId === companyId
  );
  if (existingUser) {
    res.status(400).json({ message: "Username already exists in system" });
    return;
  }

  const newUser = { username, password, companyId };
  users.push(newUser);
  saveUsers(users);

  res.status(201).json({ message: "User created successfully" });
});

app.get("/calendar", (req, res) => {
  const { username, password, companyId } = req.body;
  const users = loadUsers();

  const user = users.find(
    (user) =>
      user.username === username &&
      user.password === password &&
      user.companyId === companyId
  );

  if (user) {
    const authToken = "auth-token";
    res.cookie("authToken", authToken, {
      httpOnly: false,
      sameSite: "none",
      secure: true,
    });
    res.status(200).json({ message: "OK" });
  } else {
    res.status(401).json({ message: "Unauthorized" });
  }
});

app.listen(PORT, () => {
  console.log(
    `\u001b[35mMock Api is running on \u001b[34mhttp://localhost:${PORT}/\u001b[0m`
  );
});
