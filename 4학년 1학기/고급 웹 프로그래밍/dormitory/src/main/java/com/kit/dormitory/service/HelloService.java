package com.kit.dormitory.service;

import com.kit.dormitory.member.Member;

public interface HelloService {
    String sayHello(String name);
    String saveMember(Member member);
    Member findById(Long id);
}
