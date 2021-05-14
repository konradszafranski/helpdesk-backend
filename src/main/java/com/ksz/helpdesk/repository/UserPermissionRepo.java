package com.ksz.helpdesk.repository;

import com.ksz.helpdesk.entity.UserPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPermissionRepo extends JpaRepository<UserPermission, Long> {
    @Query("SELECT p FROM UserPermission p WHERE p.permission LIKE :permission")
    UserPermission findPermissionByName(@Param("permission") String permission);
}
