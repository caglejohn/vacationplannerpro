package UlsterCS250.rest;

import UlsterCS250.planner.Calendar;
import UlsterCS250.planner.Employee;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.AbstractList;
import java.util.TimeZone;

@Path("/planner")
public class PlannerResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Calendar getCalendar(){
        return new Calendar(TimeZone.getTimeZone("EST"));
    }
    /*@GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{resolution}")
    public AbstractList<Employee> getlist(@PathParam("resolution") String resolution, @QueryParam("index") int index){

    }*/
}
