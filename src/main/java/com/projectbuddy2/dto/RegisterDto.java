package com.projectbuddy2.dto;



import com.projectbuddy2.entities.Budget;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RegisterDto {
    private String name;
    private String email;
    private String password;
    private List<Budget> budgetArrayList = new ArrayList<Budget>();

    public RegisterDto(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
