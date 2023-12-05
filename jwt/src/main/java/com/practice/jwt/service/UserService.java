package com.practice.jwt.service;

import com.practice.jwt.domain.User;
import com.practice.jwt.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public String join(String name, String password){

        //username 중복체크
        User target = userRepository.findByUserName(name);

        if(target != null){
            throw new RuntimeException(target.getName() +" << 이미 가입된 이름입니다.");
        }

        // 저장 객체 생성
        User user = User.builder()
                .name(name)
                .password(password)
                .build();

        userRepository.save(user);
        return "SUCCESS";
    }
}
