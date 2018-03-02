package com.railroad.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_role", schema = "railroad")
public class UserRole {
    private Integer userRoleId;
    private String login;
    private Long roleId;

    public UserRole() {
    }

    public UserRole(String login, Long roleId) {
        this.login = login;
        this.roleId = roleId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_role_id", unique = true, nullable = false)
    public Integer getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    @Basic
    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "role_id")
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRole userRole = (UserRole) o;

        if (userRoleId != null ? !userRoleId.equals(userRole.userRoleId) : userRole.userRoleId != null) return false;
        if (login != null ? !login.equals(userRole.login) : userRole.login != null) return false;
        if (roleId != null ? !roleId.equals(userRole.roleId) : userRole.roleId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userRoleId != null ? userRoleId.hashCode() : 0;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (roleId != null ? roleId.hashCode() : 0);
        return result;
    }
}
