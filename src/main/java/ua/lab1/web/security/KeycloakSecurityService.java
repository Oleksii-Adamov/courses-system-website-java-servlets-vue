package ua.lab1.web.security;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.io.IOException;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class KeycloakSecurityService implements SecurityService{

    private static final Logger logger = LogManager.getLogger(KeycloakSecurityService.class);
    private final HttpClient client = HttpClient.newHttpClient();
    public KeycloakSecurityService() {
        // default constructor
    }

    private String getAdminToken() throws IOException, InterruptedException, RuntimeException {
        Map<String, String> formData = new HashMap<>();
        formData.put("username", "admin");
        formData.put("password", "root");
        formData.put("grant_type", "password");
        formData.put("client_id", "admin-cli");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/realms/master/protocol/openid-connect/token"))
                .POST(HttpRequest.BodyPublishers.ofString(getFormDataAsString(formData)))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        logger.info("keycloak login as admin response code: " + response.statusCode());
        if (response.statusCode() == 200) {
            JSONObject jsonObject = new JSONObject(response.body());
            String accessToken = (String) jsonObject.get("access_token");
            logger.info("admin access token: " + accessToken);
            return accessToken;
        }
        else {
            throw new RuntimeException("cannot login as admin to keycloak");
        }
    }

    @Override
    public boolean giveUserTeacherRole(String userId) {
        try {
            String accessToken = getAdminToken();
            String teacherRoleRepresantation = "[{\"id\":\"db7c7ece-dd60-48d6-849a-790b062cba39\",\"name\":\"Teacher\",\"description\":\"\",\"composite\":false,\"clientRole\":false,\"containerId\":\"1e290e5d-4fcc-44ff-af1b-2629cfa69905\"}]";
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/admin/realms/CoursesRealm/users/" + userId + "/role-mappings/realm"))
                    .POST(HttpRequest.BodyPublishers.ofString(teacherRoleRepresantation))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + accessToken)
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            logger.info("giveUserTeacherRole response code: " + response.statusCode());
            return response.statusCode() == 204;

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error(e.getMessage());
        } catch (IOException | RuntimeException e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean giveUserStudentRole(String userId) {
        try {
            String accessToken = getAdminToken();
            String studentRoleRepresantation = "[{\"id\":\"5b4f5c42-7de6-45b7-a818-085e8adfcd07\",\"name\":\"Student\",\"description\":\"\",\"composite\":false,\"clientRole\":false,\"containerId\":\"1e290e5d-4fcc-44ff-af1b-2629cfa69905\"}]";
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/admin/realms/CoursesRealm/users/" + userId + "/role-mappings/realm"))
                    .POST(HttpRequest.BodyPublishers.ofString(studentRoleRepresantation))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + accessToken)
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            logger.info("giveUserStudentRole response code: " + response.statusCode());
            return response.statusCode() == 204;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    private static String getFormDataAsString(Map<String, String> formData) {
        StringBuilder formBodyBuilder = new StringBuilder();
        for (Map.Entry<String, String> singleEntry : formData.entrySet()) {
            if (formBodyBuilder.length() > 0) {
                formBodyBuilder.append("&");
            }
            formBodyBuilder.append(URLEncoder.encode(singleEntry.getKey(), StandardCharsets.UTF_8));
            formBodyBuilder.append("=");
            formBodyBuilder.append(URLEncoder.encode(singleEntry.getValue(), StandardCharsets.UTF_8));
        }
        return formBodyBuilder.toString();
    }
}
