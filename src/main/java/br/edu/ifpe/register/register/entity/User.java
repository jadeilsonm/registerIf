package br.edu.ifpe.register.register.entity;

import jakarta.persistence.Entity;

import java.util.UUID;

@Entity
public class User {
    public UUID id;
    public String registration;
    public String name;
    public String email;
}
