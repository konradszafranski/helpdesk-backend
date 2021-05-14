package com.ksz.helpdesk.repository;

import com.ksz.helpdesk.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    @Query("SELECT e FROM Employee e WHERE e.username LIKE :username")
    public UserDetails findUserByName(@Param("username") String username);
}
