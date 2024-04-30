package UlsterCS250.util;

import java.util.ArrayList;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class EmailService {

    public void sendEmail(ArrayList<String> receivers, String username, String day) {
        Client client = ClientBuilder.newClient();
        String apiUrl = EmailSecrets.SMTP_URL;
        String apiKey = EmailSecrets.SMTP_KEY;

        StringBuilder toField = new StringBuilder("[");
        for (String receiver : receivers) {
            toField.append("\"").append(receiver).append("\", ");
        }
        toField.delete(toField.length() - 2, toField.length());
        toField.append("]");

        String requestPayload = "{\"api_key\": \"" + apiKey + "\"," +
                "\"to\": " + toField.toString() + ", " +
                "\"sender\": \"Vacation Planner Pro <vacationplannerpro@catstaffo.com>\", " +
                "\"subject\": \"New Vacation\", " +
                "\"text_body\": \"" + username.toUpperCase() + " submitted time off for " + day + "\", " +
                "\"html_body\": \"<p>" + username.toUpperCase() + " submitted time off for " + day + "</p>\"}";

        try {
            WebTarget target = client.target(apiUrl);
            Response response = target.request(MediaType.APPLICATION_JSON)
                    .post(Entity.json(requestPayload));

            if (response.getStatus() == 200) {
                String responseBody = response.readEntity(String.class);
                System.out.println("Response: " + responseBody);
            } else {
                System.out.println("Error: " + response.getStatus());
                System.out.println(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
    }
}
