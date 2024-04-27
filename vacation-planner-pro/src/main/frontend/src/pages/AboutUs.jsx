/* eslint-disable react/no-unescaped-entities */
import '../styles/AboutUs.css';
import { Link } from 'react-router-dom';

const AboutUs = () => {
  return (
    <div id="AboutUsPage">
      <div className="nav-container">
        <div className="container"></div>
      </div>

      <div className="container-fluid">
        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarNav"
          aria-controls="navbarNav"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarNav">
          <ul className="navbar-nav flex-column">
            <li className="nav-item">
              <Link className="nav-link" to="/calendar">
                Calendar
              </Link>
            </li>
          </ul>
        </div>
      </div>

      <div className="AboutusContainer">
        <h1 id="AboutUsBox">About Us</h1>

        <p id="TextBox">
          Vacation Planner Pro's delevopment process started on January 24th,
          2024. The biggest goal for creating the site was to change the way
          companies manage employee vaction time. With just this one tool,
          companies big or small will have the ablity to track, view, and manage
          employee time off. Our team's mission is to relieve the stress of
          double booked vacations or vacation time piling up to close to the end
          of the year.
        </p>
      </div>
      <div className="authors">
        <h1 id="The Team">The Team</h1>
        <p id="TextBoxTwo">
          John Cagle, Oscar Zandonella, Adam Marsh, Catherine Stafford, Geovanny
          Mora, Gabriella Romero, Cory Waldheim, Mariia Voianova, Ruth Zaccardo,
          Cheyenne Rossler-Demskie,
        </p>
      </div>
    </div>
  );
};
export default AboutUs;
