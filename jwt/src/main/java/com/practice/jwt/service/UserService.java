package com.practice.jwt.service;

import com.practice.jwt.domain.User;
import com.practice.jwt.exception.AppException;
import com.practice.jwt.exception.ErrorCode;
import com.practice.jwt.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void join(String name, String password){

        //username 중복체크
        User target = userRepository.findByUserName(name);

        if(target != null){
            throw new AppException(ErrorCode.USERNAME_DUPLICATED,target.getName() +" << 이미 가입된 이름입니다."); // 익셉션 발생시 해당 내용이 runtimeExceptionHandler 함수의 파라미터로 전달됨
        }

        // 저장 객체 생성
        User user = User.builder()
                .name(name)
                .password(bCryptPasswordEncoder.encode(password)) // 유저가 입력한 암호를 인코딩하여 DB에 저장함
                .build();

        userRepository.save(user);
        return;
    }
}
