package com.yang.service;

import com.yang.po.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User checkUser(String username,String password);
}
