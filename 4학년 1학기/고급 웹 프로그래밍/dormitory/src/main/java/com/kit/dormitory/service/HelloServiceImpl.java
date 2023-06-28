package com.kit.dormitory.service;

import com.kit.dormitory.member.Member;

public class HelloServiceImpl {

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
