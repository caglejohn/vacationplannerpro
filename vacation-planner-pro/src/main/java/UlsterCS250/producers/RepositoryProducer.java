package UlsterCS250.producers;

import UlsterCS250.repository.EmployeeRepository;
//import UlsterCS250.repository.EmployeeTimeOffRepository;
import UlsterCS250.repository.HalfDayRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class RepositoryProducer {

    @Produces
    @ApplicationScoped
    public EmployeeRepository produceEmployeeRepository() {
        return new EmployeeRepository();
    }

    @Produces
    @ApplicationScoped
    public HalfDayRepository produceHalfDayRepository() {
        return new HalfDayRepository();
    }
}