package ua.lab1.web.security;

public interface SecurityService {
    boolean giveUserTeacherRole(String userId);
    boolean giveUserStudentRole(String userId);
}
