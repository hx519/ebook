package com.example.demo.repository;
import java.util.List;
import com.example.demo.entity.UserPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userPasswordRepository extends JpaRepository<UserPassword,Integer>{
    UserPassword findUserpasswordByUid(Long uid);
}