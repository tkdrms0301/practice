package online.shop.service;

import lombok.RequiredArgsConstructor;
import online.shop.domain.Member;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    public Member login(String loginId, String password){
        if (loginId.equals("login") && password.equals("pwd")) {
            Member member = new Member();
            member.setName("kim");
            return member;
        }
        else{
            return null;
        }
    }
}
