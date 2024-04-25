import { useState, useRef } from 'react';
import { postAuth } from '../api/authApi';
import { useNavigate, Link } from 'react-router-dom';
import Input from '../components/form/Input';

export default function CalendarMonth() {
    const navigate = useNavigate();

        const CalendarMonth = ({calendar}) => {
            const renderCalendar = () => {
                return calendar.map(day=> {
                    const vacations = day.halfDays.reduce((total, halfDay)=>{
                        return total +
                        halfDay.employeeTimeOffs.length;
                    },0)
                })
            }
        }
   
        return(
            <div className="Calendar-container">
                <Input
                    label="id:"
                    type="number"
                    name="id"
                
                />
                <Input
                 label="day:"
                 type="number"
                 name="day"
                
              />
              <Input
                label="halfDay:"
                type="number"
                name="halfDays"
                
              />

        </div>

    );
}