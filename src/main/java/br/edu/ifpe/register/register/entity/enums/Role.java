package br.edu.ifpe.register.register.entity.enums;

public enum Role {
    ADMIN,
    PROFESSOR,
    STUDENT,
    SECRETARY,
    TECHNICIAN;

    public String toAuthorize() {
        return "ROLE_"+ this.name();
    }
}
