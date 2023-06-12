package ua.lab1.web.security;

public interface SecurityService {
    void giveUserTeacherRole(String userId);
    void giveUserStudentRole(String userId);
}
