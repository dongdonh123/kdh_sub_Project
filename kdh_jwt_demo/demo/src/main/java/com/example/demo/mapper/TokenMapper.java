package com.example.demo.mapper;
import com.example.demo.model.TokenDTO;
import com.example.demo.model.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TokenMapper {

    String findrRefresh(String refresh);

    void deleteRefresh(String refresh);

    void saveToken(TokenDTO tokenDTO);




}
