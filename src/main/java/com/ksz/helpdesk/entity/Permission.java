package com.ksz.helpdesk.entity;

public enum Permission {
    ADD_EMPLOYEE("ADD_EMPLOYEE"),
    GET_ALL_CASES("GET_EMPLOYEE_LIST");

    private String roleName;

    Permission(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
