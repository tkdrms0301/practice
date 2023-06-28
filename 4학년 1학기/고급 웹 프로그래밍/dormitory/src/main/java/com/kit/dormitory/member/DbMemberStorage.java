package com.kit.dormitory.member;

import com.kit.dormitory.annotation.ElpasedTimeLog;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Qualifier("mainMemberStorage")
public class DbMemberStorage implements MemberStorage{
    private static Map<Long,Member> members = new HashMap<>();

    @ElpasedTimeLog
    @Override
    public void store(Member member) {
        System.out.println("=============to db Storage==============");
        members.put(member.getId(), member);
    }

    @ElpasedTimeLog
    @Override
    public Member findById(Long memberId) {
        System.out.println("=============from db Storage==============");
        return members.get(memberId);
    }
}
