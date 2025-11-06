package br.edu.ifpe.register.register.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserCsvDTO {
    @CsvBindByName(column = "name")
    private String name;
    @CsvBindByName(column = "registration")
    private String registration;
    @CsvBindByName(column = "email")
    private String email;
    @CsvBindByName(column = "phone")
    private String phone;
}
