import '../styles/styleCal.css';
import './scriptCal';

export default function Calendar() {
  return (
    <div className="calendar">
      <body>
        <div id="container">
          <div id="header">
            <div id="monthDisplay"></div>
            <div>
              <button id="backButton">Back</button>
              <button id="nextButton">Next</button>
            </div>
          </div>

          <div id="weekdays">
            <div>Sunday</div>
            <div>Monday</div>
            <div>Tuesday</div>
            <div>Wednesday</div>
            <div>Thursday</div>
            <div>Friday</div>
            <div>Saturday</div>
          </div>

          <div id="calendar"></div>
        </div>
      </body>
    </div>
  );
}
