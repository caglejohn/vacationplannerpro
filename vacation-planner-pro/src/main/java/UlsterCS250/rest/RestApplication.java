package UlsterCS250.rest;

import java.util.HashSet;
import java.util.Set;

import UlsterCS250.ApplicationConfig;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
public class RestApplication extends Application {
    @Override
    public Set<Object> getSingletons() {
        Set<Object> singletons = new HashSet<>();
        singletons.add(ApplicationConfig.getEmployeeResource());
        singletons.add(ApplicationConfig.getHalfDayResource());
        return singletons;
    }
}