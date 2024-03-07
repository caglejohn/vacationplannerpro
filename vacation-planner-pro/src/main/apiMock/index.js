const path = require("path");
const express = require("express");
const fs = require("fs");
const cors = require("cors");
const session = require("express-session");
const cookieParser = require("cookie-parser");
const jwt = require("jsonwebtoken");

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
      maxAge: 1 * 60 * 60,
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
    const authToken = jwt.sign(
      { username: user.username },
      "jwt-auth-token-secret-key",
      { expiresIn: "1m" }
    );
    const refreshToken = jwt.sign(
      { username: user.username },
      "jwt-refresh-token-secret-key",
      { expiresIn: "5m" }
    );
    res.cookie("authToken", authToken, { maxAge: 60000 });
    res.cookie("refreshToken", refreshToken, {
      maxAge: 300000,
      httpOnly: true,
      secure: true,
      sameSite: "strict",
    });
    res.status(200).json({ valid: true, message: "OK" });
  } else {
    res.status(401).json({ valid: false, message: "Unauthorized" });
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
    res
      .status(400)
      .json({ valid: false, message: "Username already exists in system" });
    return;
  }

  const newUser = { username, password, companyId };
  users.push(newUser);
  saveUsers(users);

  res.status(201).json({ message: "User created successfully" });
});

const refresh = (req, res) => {
  let isValid = false;
  const refreshToken = req.cookies.refreshToken;
  if (!refreshToken) {
    return res.status(401).json({ valid: false, message: "Unauthorized" });
  } else {
    jwt.verify(authToken, "jwt-refresh-secret-key", (err, o) => {
      if (err) {
        return res.status(401).json({ valid: false, message: "Unauthorized" });
      } else {
        const authToken = jwt.sign(
          { username: decoded.user.username },
          "jwt-auth-token-secret-key",
          { expiresIn: "1m" }
        );
        res.cookie("authToken", authToken, { maxAge: 60000 });
        isValid = true;
      }
    });
  }
  return isValid;
};

const validateUser = (req, res, next) => {
  const authToken = req.cookies.authToken;
  if (!authToken) {
    if (refresh(req, res)) next();
  } else {
    jwt.verify(authToken, "jwt-auth-secret-key", (err, o) => {
      if (err) {
        return res.status(401).json({ valid: false, message: "Unauthorized" });
      } else {
        req.username = o.username;
        next();
      }
    });
  }
};

app.get("/calendar", validateUser, (req, res) => {
  return res.status(200).json({ valid: true, message: "OK" });
});

app.listen(PORT, () => {
  console.log(
    `\u001b[35mMock Api is running on \u001b[34mhttp://localhost:${PORT}/\u001b[0m`
  );
});
