package com.example.demo.service;


import com.example.demo.mapper.LoginMapper;
import com.example.demo.model.LoginDTO;
import com.example.demo.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements UserDetailsService {

    private final LoginMapper loginMapper;

    @Autowired
    public LoginService(LoginMapper loginMapper){
        this.loginMapper = loginMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //1. username이 DB에 있는지 확인하기
        UserDTO userDTO = loginMapper.findUserName(username);
        //2. 있으면 LoginDTO에 넣기
        if(username.equals(userDTO.getUser_id())){

            return new LoginDTO(userDTO);
        }
        return null;
    }
}
