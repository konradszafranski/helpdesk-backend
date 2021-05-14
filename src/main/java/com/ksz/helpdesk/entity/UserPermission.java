package com.ksz.helpdesk.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "permission")
public class UserPermission implements GrantedAuthority {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "permission")
    private String permission;
    /*@ManyToMany @Transient @JoinColumn(name = "employee_id")
    private Employee employee;*/

    public UserPermission() {
    }

    public UserPermission(String permission) {
        this.permission = Permission.valueOf(permission).getRoleName();
    }

    public void setAuthority(String permission) {
        this.permission = permission;
    }

    @Override
    public String getAuthority() {
        return permission;
    }
}
