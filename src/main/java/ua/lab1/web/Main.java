package ua.lab1.web;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;

public class Main {
    //private static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) throws UnirestException {
        System.out.println("Hello world!");
//        BasicConfigurator.configure();
//        logger.info("Hello world");
//        logger.info("we are in logger info mode");
        Unirest.setTimeouts(60000, 30000);
        HttpResponse<String> response = Unirest.post("http://localhost:8080/realms/master/protocol/openid-connect/token")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .field("username", "admin")
                .field("password", "root")
                .field("grant_type", "password")
                .field("client_id", "admin-cli")
                .asString();
        System.out.println("got response or timeout");
        // response.code()
        if (response.getStatus() != 200) throw new RuntimeException("Cannot login to keycloak as admin, response code " + response.getStatus());
        else {
            System.out.println("logged to keycloak as admin");
            //InputStreamReader reader = new InputStreamReader(response.body().byteStream());
//                JSONArray jsonArray = new JSONArray(response.body().string());
//                String jsonObjectString = (String) jsonArray.get(0);
//                logger.info(jsonObjectString);
            //JSONObject jsonObject = new JSONObject(response.body().string());
            JSONObject jsonObject = new JSONObject(response.getBody());
            String accessToken = (String) jsonObject.get("access_token");
            System.out.println("admin access token: " + accessToken);
        }
    }
}