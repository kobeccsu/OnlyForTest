package com.leizhou.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "account")
@Table(name = "account")
public class User {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    String password;

    @Column(nullable = false)
    int status;

    @Column(name = "created_date", nullable = false)
    Date created_date;

    public User(String username, String password){
        this.name = username;
        this.password = password;
    }
}
