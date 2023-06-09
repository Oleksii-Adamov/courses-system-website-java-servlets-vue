package ua.lab1.web.security;

public interface SecurityService {
    void addRoleToUserWithId(String userId, String role);
    void removeRoleFromUserWithId(String userId, String role);
}
