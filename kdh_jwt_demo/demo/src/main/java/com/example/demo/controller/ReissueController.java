package com.example.demo.controller;

import com.example.demo.jwt.JWTUtil;
import com.example.demo.model.TokenDTO;
import com.example.demo.service.TokenService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@ResponseBody
public class ReissueController {

    private final JWTUtil jwtUtil;
    private final TokenService tokenService;


    public ReissueController(JWTUtil jwtUtil, TokenService tokenService) {

        this.jwtUtil = jwtUtil;
        this.tokenService = tokenService;
    }

    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response) {

        //get refresh token
        String refresh = null;
        Cookie[] cookies = request.getCookies(); //쿠기를 가져온다
        for (Cookie cookie : cookies) {

            if (cookie.getName().equals("refresh")) { //쿠기가 리프레시면

                refresh = cookie.getValue(); // 리프레시 쿠키를 초기화한다
            }
        }

        if (refresh == null) { // 리프레시 쿠기가 없다면

            //response status code
            return new ResponseEntity<>("refresh token null", HttpStatus.BAD_REQUEST); //이 상태를 리턴
        }

        //expired check
        try { // 리스페리쿠기가 만료됐다면?
            jwtUtil.isExpired(refresh);
        } catch (ExpiredJwtException e) {

            //response status code
            return new ResponseEntity<>("refresh token expired", HttpStatus.BAD_REQUEST); // 이상태를 리턴
        }

        // 토큰이 refresh인지 확인 (발급시 페이로드에 명시)
        String category = jwtUtil.getCategory(refresh); //리프레시 쿠기를 한번더 검증

        if (!category.equals("refresh")) {

            //response status code
            return new ResponseEntity<>("invalid refresh token", HttpStatus.BAD_REQUEST);
        }

        //DB에 저장되어 있는지 확인
        String isExist = tokenService.findrRefresh(refresh);
        if (isExist.equals("")) {

            //response body
            return new ResponseEntity<>("invalid refresh token", HttpStatus.BAD_REQUEST);
        }

        String username = jwtUtil.getUsername(refresh);
        String role = jwtUtil.getRole(refresh);

        //make new JWT
        String newAccess = jwtUtil.createJwt("access", username, role, 600000L);
        String newRefresh = jwtUtil.createJwt("refresh", username, role, 86400000L);

        //Refresh 토큰 저장 DB에 기존의 Refresh 토큰 삭제 후 새 Refresh 토큰 저장
        tokenService.deleteRefresh(refresh);
        addRefreshEntity(username, newRefresh, 86400000L);

        //response
        response.setHeader("access", newAccess);
        response.addCookie(createCookie("refresh", newRefresh));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Cookie createCookie(String key, String value) {

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24*60*60);
        //cookie.setSecure(true);
        //cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }

    private void addRefreshEntity(String username, String refresh, Long expiredMs) {

        Date date = new Date(System.currentTimeMillis() + expiredMs);

        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setUser_name(username);
        tokenDTO.setRefresh(refresh);
        tokenDTO.setExpiration(date.toString());

        tokenService.saveToken(tokenDTO);
    }
}
