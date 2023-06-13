package ua.lab1.web;

import com.auth0.jwk.JwkException;
import ua.lab1.web.dao.DAOFactory;
import ua.lab1.web.database.TransactionFactory;
import ua.lab1.web.enitities.Teacher;
import ua.lab1.web.exceptions.TransactionException;
import ua.lab1.web.security.JwtValidator;

import java.net.MalformedURLException;
import java.security.InvalidParameterException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        JwtValidator jwtValidator = new JwtValidator();
        try {
            jwtValidator.validate("" +
            "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJMbVR6M2pYS0tSV2hHbW1KS0NoeU8xLTUyM2dkTUFwLTloVkUxenhrV1pFIn0.eyJleHAiOjE2ODY2MTMwNDAsImlhdCI6MTY4NjYxMjc0MCwiYXV0aF90aW1lIjoxNjg2NTkxNDMyLCJqdGkiOiI3ODMyMWM4Ny00ODFjLTRlMTMtYjU2Ni1lMGE1OGQ2YzAzNTkiLCJpc3MiOiJodHRwOi8vMTI3LjAuMC4xOjgwODAvcmVhbG1zL0NvdXJzZXNSZWFsbSIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiI2ZmQ0NWQ1My00Mjk2LTRmNTMtYjM0ZS1iNjIzNTZjNmRjOGEiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJjb3Vyc2VzLWFwcC1jbGllbnQiLCJub25jZSI6ImE3Yzg2MzY2LWU0MjMtNDliNy1hZmQxLTY1YmQ0ODJiZDBkYSIsInNlc3Npb25fc3RhdGUiOiIwNWMxZWE3OC0xNTQxLTRmMTAtYWQ0MS05OWMxNDI4NDg4NzUiLCJhY3IiOiIwIiwiYWxsb3dlZC1vcmlnaW5zIjpbImh0dHA6Ly9sb2NhbGhvc3Q6ODA4MSJdLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsiZGVmYXVsdC1yb2xlcy1jb3Vyc2VzcmVhbG0iLCJvZmZsaW5lX2FjY2VzcyIsInVtYV9hdXRob3JpemF0aW9uIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiJvcGVuaWQgcHJvZmlsZSBlbWFpbCIsInNpZCI6IjA1YzFlYTc4LTE1NDEtNGYxMC1hZDQxLTk5YzE0Mjg0ODg3NSIsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwibmFtZSI6Im5hbWUgc3VybmFtZSIsInByZWZlcnJlZF91c2VybmFtZSI6InRlYWNoZXJfdGVzdCIsImdpdmVuX25hbWUiOiJuYW1lIiwiZmFtaWx5X25hbWUiOiJzdXJuYW1lIiwiZW1haWwiOiJ0ZWFjaGVyX3Rlc3RAZ21haWwuY29tIn0.loZVPaqlJKXphRVjEDihG6jMDJYcpuOiRJF3S_k9-CZV0uRXxBmJnvh70-EOwi55uRXufH61sJdODRvlfap6R9TV7PVMaQLiKXW83mMAkL1Sdcp32wxocmbouJzcd600uDfvLh81t5kCGqCsA2AmBhuIsQiK31QVqZeoLNdaoHAiPDJbtuK30SegqPlQ4cQK6BpsXrXCccB8V9cAqxYAmFtSZum_qBj2umifbNHJsfU0gTmKj_Eqn85QxY1w3TzDrAtEkfmf8L5XCzneGX7OpLRLe85Fg83GBoM1Zj0s9T8draP5iXvVSj9nKi0gIH4lun5FTe3MhJwiNrcmDKWNDg"
                    );
                    System.out.println("valid");
        }
        catch (InvalidParameterException | JwkException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}