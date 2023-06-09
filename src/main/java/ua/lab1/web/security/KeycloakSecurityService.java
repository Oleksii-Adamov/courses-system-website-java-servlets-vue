package ua.lab1.web.security;


import okhttp3.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.RoleRepresentation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class KeycloakSecurityService implements SecurityService{

    protected static final Logger logger = LogManager.getLogger(KeycloakSecurityService.class);
//    Keycloak keycloak;
//    RealmResource realmResource;
//    OkHttpClient okHttpClient;
//    Request getTokenRequest;
    public KeycloakSecurityService() {
//        String serverUrl = "http://localhost:8080/";
//        String Adminrealm = "master";
//        String UserRealm = "CoursesRealm";
//        // idm-client needs to allow "Direct Access Grants: Resource Owner Password Credentials Grant"
//        //String clientId = "admin-cli";
//        //String clientId = "CoursesRealm-realm";
//        String clientId = "courses-app-client";
//        String clientSecret = "929Bk1JvPjD8wCnWGA9V4J53H37Cwaxt";
//        logger.info("Trying to log to keycloak as admin");
//		// Client "idm-client" needs service-account with at least "manage-users, view-clients, view-realm, view-users" roles for "realm-management"
////		this.keycloak = KeycloakBuilder.builder() //
////				.serverUrl(serverUrl) //
////				.realm(UserRealm) //
////				.grantType(OAuth2Constants.CLIENT_CREDENTIALS) //
////				.clientId(clientId) //
////				.clientSecret(clientSecret).build();
//
//        // User "idm-admin" needs at least "manage-users, view-clients, view-realm, view-users" roles for "realm-management"
//
//        this.keycloak = KeycloakBuilder.builder() //
//                .serverUrl(serverUrl) //
//                .realm(Adminrealm) //
//                .grantType(OAuth2Constants.PASSWORD) //
//                .clientId(clientId) //
//                .username("admin") //
//                .password("root") //
//                .build();
//        logger.info("logged to keycloak as admin");
//        //logger.info(keycloak);
//        this.realmResource = keycloak.realm(UserRealm);
//        logger.info("got realm " + UserRealm);
        //URL url = new URL("http://localhost:8080/CoursesRealm/clients/{id}/roles/{role-name}");
//        try {
//            OkHttpClient client = new OkHttpClient.Builder()
//                    .readTimeout(1, TimeUnit.SECONDS)
//                    .build();
//            okHttpClient = new OkHttpClient().newBuilder()
//                    .build();
//            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
//            RequestBody body = RequestBody.create(mediaType, "username=admin&password=root&grant_type=password&client_id=admin-cli");
//            getTokenRequest = new Request.Builder()
//                    .url("http://localhost:8080/realms/master/protocol/openid-connect/token")
//                    .method("POST", body)
//                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
//                    .build();
//        } catch (Exception e) {
//            logger.info(e.getMessage());
//        }
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

    private String getAdminToken() throws IOException, InterruptedException {
//        Response response = okHttpClient.newCall(getTokenRequest).execute();
        //Unirest.setTimeouts(0, 0);
        logger.info("getAdminToken()");
//        Unirest.setTimeouts(60000, 30000);
//        logger.info("setTimeouts done");
//        HttpResponse<String> response = Unirest.post("http://localhost:8080/realms/master/protocol/openid-connect/token")
//                .header("Content-Type", "application/x-www-form-urlencoded")
//                .field("username", "admin")
//                .field("password", "root")
//                .field("grant_type", "password")
//                .field("client_id", "admin-cli")
//                .asString();
        HttpClient client = HttpClient.newHttpClient();
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
        logger.info("got response or timeout");
        logger.info(response.statusCode());
        logger.info(response.body());
        return "";
        // response.code()
//        if (response.getStatus() != 200) throw new RuntimeException("Cannot login to keycloak as admin, response code " + response.getStatus());
//        else {
//            logger.info("logged to keycloak as admin");
//            //InputStreamReader reader = new InputStreamReader(response.body().byteStream());
////                JSONArray jsonArray = new JSONArray(response.body().string());
////                String jsonObjectString = (String) jsonArray.get(0);
////                logger.info(jsonObjectString);
//            //JSONObject jsonObject = new JSONObject(response.body().string());
//            JSONObject jsonObject = new JSONObject(response.getBody());
//            String accessToken = (String) jsonObject.get("access_token");
//            logger.info("admin access token: " + accessToken);
//            return accessToken;
//        }
    }

    @Override
    public void addRoleToUserWithId(String userId, String role) {
        try {
            logger.info("adding role " + role + " to " + userId);
//        UserResource userResource = realmResource.users().get(userId);
//        RoleRepresentation roleRepresentation = realmResource.roles().get(role).toRepresentation();
//        userResource.roles().realmLevel() //
//                .add(Arrays.asList(roleRepresentation));
            String accessToken = getAdminToken();
            logger.info("successfully added role " + role + " to " + userId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            logger.error(e.getCause().getMessage());
        }
    }

    @Override
    public void removeRoleFromUserWithId(String userId, String role) {
//        logger.info("removing role " + role + " from " + userId);
//        UserResource userResource = realmResource.users().get(userId);
//        RoleRepresentation roleRepresentation = realmResource.roles().get(role).toRepresentation();
//        userResource.roles().realmLevel() //
//                .remove(Arrays.asList(roleRepresentation));
//        logger.info("successfully removed role " + role + " from " + userId);
    }
}
