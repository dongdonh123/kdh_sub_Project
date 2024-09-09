package com.example.demo.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LoginDTO implements UserDetails {

    private final UserDTO userDTO;

    public LoginDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    //권한
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return userDTO.getUser_role();
            }
        });
        return collection;
    }

    //DB에서 사용자 passwd 반환
    @Override
    public String getPassword() {
        return userDTO.getUser_passwd();
    }

    //DB에서 사용자 userid 반환
    @Override
    public String getUsername() {
        return userDTO.getUser_id();
    }

    //계정이 만료되지 않았는지를 확인하는 메서드입니다. true를 반환하면 계정이 만료되지 않았음을 의미합니다. (나는 계정 만료 기능은 하지 않을것임)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //계정이 잠겨 있지 않은지를 확인하는 메서드입니다. true를 반환하면 계정이 잠겨있지 않음을 의미합니다.
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //자격 증명(비밀번호)이 만료되지 않았는지를 확인하는 메서드입니다. (나는 비밀번호 만료 기능은 하지 않을것임)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //계정이 활성화되어 있는지를 확인하는 메서드입니다.
    @Override
    public boolean isEnabled() {
        return true;
    }
}
