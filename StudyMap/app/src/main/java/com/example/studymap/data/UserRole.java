package com.example.studymap.data;

public enum UserRole {
    STUDENT,
    PROFESSOR,
    VISITOR;

    public static UserRole parseUserLogin(String login) {
        if (login.contains("aluno")) {
            return STUDENT;
        } else if (login.contains("professor")) {
            return PROFESSOR;
        } else {
            return VISITOR;
        }
    }
}
