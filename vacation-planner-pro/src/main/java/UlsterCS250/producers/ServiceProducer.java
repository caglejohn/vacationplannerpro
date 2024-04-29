package UlsterCS250.producers;

import UlsterCS250.util.EmailService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class ServiceProducer {
    @Produces
    @ApplicationScoped
    public EmailService produceEmailService() {
        return new EmailService();
    }
}
