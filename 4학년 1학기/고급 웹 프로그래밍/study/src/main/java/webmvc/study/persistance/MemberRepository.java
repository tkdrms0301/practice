package webmvc.study.persistance;

import org.springframework.stereotype.Repository;
import webmvc.study.domain.Member;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MemberRepository {
    private static final List<Member> mebers = new ArrayList<>();
    private static Long seq = 0L;
    public void save(Member member){
        member.setId(++seq);
        mebers.add(member);
    }

    public List<Member> findAll(){
        return mebers;
    }

    public Member findById(Long memberId) {
        Member findMember = mebers.stream().filter(member -> member.getId() == memberId).findAny().orElse(null);
        return findMember;
    }
}
