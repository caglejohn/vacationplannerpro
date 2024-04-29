/* eslint-disable react/no-unescaped-entities */
import '../styles/AboutUs.css';

const AboutUs = () => {
  return (
    <div className="">
      <div className="row">
        <div className="col-md-6 box pt-5" style={{ paddingLeft: '4rem' }}>
          <div className="AboutusContainer">
            <h1 id="AboutUsBox">About Us</h1>

            <p id="TextBox">
              Vacation Planner Pro's delevopment process started on January
              24th, 2024. The biggest goal for creating the site was to change
              the way companies manage employee vaction time. With just this one
              tool, companies big or small will have the ablity to track, view,
              and manage employee time off. Our team's mission is to relieve the
              stress of double booked vacations or vacation time piling up to
              close to the end of the year.
            </p>
          </div>
          <div className="authors">
            <h1 id="The Team">The Team</h1>
            <p id="TextBoxTwo">
              John Cagle, Oscar Zandonella, Adam Marsh, Catherine Stafford,
              Geovanny Mora, Gabriella Romero, Cory Waldheim, Mariia Voianova,
              Ruth Zaccardo, Cheyenne Rossler-Demskie,
            </p>
          </div>
        </div>
        <div className="col-md-6 image-container"></div>
      </div>
    </div>
  );
};
export default AboutUs;
