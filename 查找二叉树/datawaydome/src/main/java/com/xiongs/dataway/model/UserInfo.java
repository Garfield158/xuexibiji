package com.xiongs.dataway.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@ToString
@Table(name = "userInfo")
public class UserInfo {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "username")
    private String username;
    private String password;

}
