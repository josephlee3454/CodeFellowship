package com.example.codefellowshiptwo.Models.user;

import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeUserRepository extends JpaRepository<CodeUser, Long> {
    public CodeUser findByUsername(String username);


}
