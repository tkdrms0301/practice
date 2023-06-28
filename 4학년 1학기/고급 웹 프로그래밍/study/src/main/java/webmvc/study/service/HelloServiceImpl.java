package webmvc.study.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import webmvc.study.domain.Member;
import webmvc.study.persistance.MemberRepository;

@Service
@AllArgsConstructor
public class HelloServiceImpl implements HelloService {
    private final MemberRepository memberRepository;

    @Override
    public String sayHello(String name) {
        return "hello " + name;
    }

    @Override
    public String saveMember(Member member) {
        memberRepository.save(member);
        return "ok";
    }

    @Override
    public Member findById(Long id) {
        return memberRepository.findById(id);
    }

}
