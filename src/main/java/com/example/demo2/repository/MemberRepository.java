package com.example.demo2.repository;

import com.example.demo2.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
public class MemberRepository {

    @Autowired
    DataSource dataSource;

    public MemberDTO selectById(String id) throws Exception {
        String sql = "select * from members where id=?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, id);

            try (ResultSet resultSet = statement.executeQuery();) {

                if (resultSet.next()) {
                    return new MemberDTO(resultSet);
                } else {
                    return null;
                }
            }
        }
    }
}
