package com.kit.dormitory.member;

import org.springframework.stereotype.Component;

@Component
public interface MemberStorage {
    void store(Member member);
    Member findById(Long memberId);
}
