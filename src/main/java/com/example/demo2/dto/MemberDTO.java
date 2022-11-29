package com.example.demo2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    private String id;
    private String pw;
    private String name;
    private String phone;
    private String email;
    private String zipCode;
    private String address1;
    private String address2;
    private Timestamp signup_date;

    public MemberDTO(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getString("id");
        this.pw = resultSet.getString("pw");
        this.name = resultSet.getString("name");
        this.phone = resultSet.getString("phone");
        this.email = resultSet.getString("email");
        this.zipCode = resultSet.getString("zipCode");
        this.address1 = resultSet.getString("address1");
        this.address2 = resultSet.getString("address2");
        this.signup_date = resultSet.getTimestamp("signup_date");
    }

}
