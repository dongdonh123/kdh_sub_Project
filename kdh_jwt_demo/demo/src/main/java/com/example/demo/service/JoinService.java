package com.example.demo.service;


import com.example.demo.mapper.JoinMapper;
import com.example.demo.model.UserDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {


    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JoinMapper joinMapper;

    public JoinService(JoinMapper joinMapper , BCryptPasswordEncoder bCryptPasswordEncoder) {

        this.joinMapper = joinMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void joinProcess(UserDTO userDTO) {

        //1. ID 중복체크 PASS

        //2. ID,passWD, ROLE 문자열 준비해서 DTO에 넣기
        userDTO.setUser_role("ROLE_ADMIN");
        //2-1 passwd 인코딩(암호화)
        userDTO.setUser_passwd(bCryptPasswordEncoder.encode(userDTO.getUser_passwd()));
        //3. 매퍼에 보내기
        joinMapper.joinProcess(userDTO);


    }
}