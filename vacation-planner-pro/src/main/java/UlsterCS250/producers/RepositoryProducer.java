package UlsterCS250.producers;

import UlsterCS250.repository.EmployeeRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class RepositoryProducer {

    @Produces
    @ApplicationScoped
    public EmployeeRepository produceEmployeeRepository() {
        return new EmployeeRepository();
    }
}