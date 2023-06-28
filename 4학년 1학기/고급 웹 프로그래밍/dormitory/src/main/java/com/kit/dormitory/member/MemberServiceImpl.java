package com.kit.dormitory.member;

import com.kit.dormitory.annotation.ElpasedTimeLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
public class MemberServiceImpl implements MemberService{

    private final MemberStorage memberStorage;


    @Autowired
    public MemberServiceImpl(@Qualifier("mainMemberStorage") MemberStorage memberStorage) {
        this.memberStorage = memberStorage;
    }

    @ElpasedTimeLog
    @Override
    public void register(Member member) {
        memberStorage.store(member);
    }

    @ElpasedTimeLog
    @Override
    public Member findMember(Member member) {
        Member findMember = memberStorage.findById(member.getId());
        return findMember;
    }
}
