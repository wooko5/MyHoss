package com.example.mycanister.model;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User implements Serializable {
    private String user_id;
    private String user_pw;
    private String user_name;
    private String user_birth;
    private String user_Phone;
    private String user_address;
    private String user_email;
}
