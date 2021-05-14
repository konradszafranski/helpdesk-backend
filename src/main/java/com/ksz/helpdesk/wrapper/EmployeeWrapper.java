package com.ksz.helpdesk.wrapper;

import com.ksz.helpdesk.entity.Employee;
import com.ksz.helpdesk.entity.UserPermission;
import com.ksz.helpdesk.repository.UserPermissionRepo;
import com.ksz.helpdesk.request.CreateUserRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeWrapper {

    UserPermissionRepo userPermissionRepo;

    public EmployeeWrapper(UserPermissionRepo userPermissionRepository) {
        this.userPermissionRepo = userPermissionRepository;
    }

    public Employee employeeRequestToEmployee(CreateUserRequest createUserRequest) {
        List<UserPermission> allPermisions = userPermissionRepo.findAll();
        List<UserPermission> allMatchingPermissions = allPermisions.stream()
                .filter(ap -> createUserRequest.getUserPermissions().contains(ap.getAuthority()))
                .collect(Collectors.toList());

        Employee employee = new Employee(createUserRequest.getUsername(),
                createUserRequest.getPassword(),
                allMatchingPermissions,
                createUserRequest.getName(),
                createUserRequest.getSurname(),
                createUserRequest.getPosition(),
                createUserRequest.getPhoneNumber());

        return employee;
    }
}
