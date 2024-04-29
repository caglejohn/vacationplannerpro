package UlsterCS250.util;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class EmailService {
    public void sendEmail() {
        Client client = ClientBuilder.newClient();
        String apiUrl = EmailSecrets.SMTP_URL;
        String apiKey = EmailSecrets.SMTP_KEY;
        String receiver = "Catherine Stafford <catstaffo@gmail.com>";
        String employeeName = "John Doe";
        String timeOff = "April 28";

        String requestPayload = "{\"api_key\": \"" + apiKey + "\"," +
                "\"to\": [\"" + receiver + "\"], " +
                "\"sender\": \"Vacation Planner Pro <vacationplannerpro@catstaffo.com>\", " +
                "\"subject\": \"New Vacation\", " +
                "\"text_body\": \"" + employeeName + " submitted time off for " + timeOff + "\", " +
                "\"html_body\": \"<p>" + employeeName + " submitted time off for " + timeOff + "</p>\"}";

        try {
            WebTarget target = client.target(apiUrl);
            Response response = target.request(MediaType.APPLICATION_JSON)
                    .post(Entity.json(requestPayload));

            if (response.getStatus() == 200) {
                String responseBody = response.readEntity(String.class);
                System.out.println("Response: " + responseBody);
            } else {
                System.out.println("Error: " + response.getStatus());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
    }
}