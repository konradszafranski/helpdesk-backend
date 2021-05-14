package com.ksz.helpdesk.controller;

import com.ksz.helpdesk.request.CreateUserRequest;
import com.ksz.helpdesk.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

    EmployeeService employeeService;

    public AdminController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/getListOfEmployees")
    @ResponseBody
    public ResponseEntity getListOfEmployees() {
        try {
            return new ResponseEntity(employeeService.getListOfUsers().toArray(), HttpStatus.ACCEPTED);
        } catch(Exception e) {
            System.out.println("exception not ok");
            System.out.println(e.toString() + " " + e.getCause() + " " + e.getMessage());
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

    @PostMapping(value = "createNewEmployee", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void testPostMethod(@RequestBody CreateUserRequest createUserRequest) {
        try {
            employeeService.createNewUser(createUserRequest);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
