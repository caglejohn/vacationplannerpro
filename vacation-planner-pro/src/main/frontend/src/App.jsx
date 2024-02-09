import logo from "./assets/images/logo.png";
import "./App.css";

function App() {
  return (
    <>
      <div id="box">
        <h1>Log In</h1>
        <div id="info">
          <label for="userName">User Name:</label>
          <input type="text" id="userName"></input>
          <label for="password">Password:</label>
          <input type="text" id="password"></input>
          <label for="token">Company ID:</label>
          <input type="text" id="token"></input>
          <button>Submit</button>
        </div>
        <img id="logo" src={logo}></img>
      </div>
    </>
  );
}

export default App;
