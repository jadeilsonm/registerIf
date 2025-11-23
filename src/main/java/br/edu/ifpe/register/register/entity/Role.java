package br.edu.ifpe.register.register.entity;

public enum Role {
    ADMIN,
    TEACHER,
    SECRETARY,
    STUDENT;

    public String toAuthorize() {
        return "ROLE_"+ this.name();
    }
}