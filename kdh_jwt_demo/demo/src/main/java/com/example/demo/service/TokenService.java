package com.example.demo.service;

import com.example.demo.mapper.JoinMapper;
import com.example.demo.mapper.TokenMapper;
import com.example.demo.model.TokenDTO;
import com.example.demo.model.UserDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    private final TokenMapper tokenMapper;

    public TokenService(TokenMapper tokenMapper) {

        this.tokenMapper =tokenMapper;

    }

    public String findrRefresh(String refresh) {
        return tokenMapper.findrRefresh(refresh);
    }

    public void deleteRefresh(String refresh) {
        tokenMapper.deleteRefresh(refresh);
    }

    public void saveToken(TokenDTO tokenDTO) {
        tokenMapper.saveToken(tokenDTO);
    }

}
