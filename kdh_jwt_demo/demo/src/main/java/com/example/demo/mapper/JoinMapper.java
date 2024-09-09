package com.example.demo.mapper;
import com.example.demo.model.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JoinMapper {

    void joinProcess(UserDTO userDTO);


}
