package com.ksz.helpdesk.service;

import com.ksz.helpdesk.entity.Employee;
import com.ksz.helpdesk.entity.UserPermission;
import com.ksz.helpdesk.repository.EmployeeRepo;
import com.ksz.helpdesk.repository.UserPermissionRepo;
import com.ksz.helpdesk.request.CreateUserRequest;
import com.ksz.helpdesk.wrapper.EmployeeWrapper;
import org.hibernate.HibernateException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeService implements UserDetailsService {

    EmployeeRepo employeeRepository;
    UserPermissionRepo userPermissionRepository;
    EmployeeWrapper employeeWrapper;
    PasswordEncoder passwordEncoder;

    public EmployeeService(EmployeeRepo employeeRepo, UserPermissionRepo userPermissionRepo, EmployeeWrapper employeeWrapper, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepo;
        this.userPermissionRepository = userPermissionRepo;
        this.employeeWrapper = employeeWrapper;
        this.passwordEncoder = passwordEncoder;
    }

    public void createNewUser(CreateUserRequest createUserRequest) {
        Employee employee = employeeWrapper.employeeRequestToEmployee(createUserRequest);
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        try {
            employeeRepository.saveAndFlush(employee);
        } catch (Exception e) {
            System.out.println("exception " + e.getMessage() + " " + e.getCause());
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return employeeRepository.findUserByName(username);
        } catch (Exception e) {
            System.out.println(e.toString());
            throw e;
        }
    }

    public List<Employee> getListOfUsers() throws HibernateException {
        List<Employee> employeeList = employeeRepository.findAll();
        return  employeeList;
    }

    @PostConstruct
    public void createSampleUser() {
        try {
            UserPermission userRole = new UserPermission("GET_ALL_CASES");
            userPermissionRepository.save(userRole);
            UserPermission userPermission2 = new UserPermission("ADD_EMPLOYEE");
            userPermissionRepository.save(userPermission2);

            Set<String> userPermissions = new HashSet<>();
            userPermissions.add("ADD_EMPLOYEE");
            CreateUserRequest createUserRequest = new CreateUserRequest("k_sz",
                    "cat",
                    userPermissions,
                    "Konrad",
                    "Szafrański",
                    "POPO",
                    "544190270");
            createNewUser(createUserRequest);
        } catch (Exception e) {
            System.out.println("employeeService");
            System.out.println(e);
        }
    }
}
