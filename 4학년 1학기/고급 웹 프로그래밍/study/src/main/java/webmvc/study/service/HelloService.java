package webmvc.study.service;

import webmvc.study.domain.Member;

public interface HelloService {
    String sayHello(String name);
    String saveMember(Member member);
    Member findById(Long id);
}
