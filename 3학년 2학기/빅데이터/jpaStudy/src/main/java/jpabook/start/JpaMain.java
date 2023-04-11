package jpabook.start;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import net.bytebuddy.asm.MemberRemoval;

import javax.persistence.*;
import javax.sound.midi.MetaMessage;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Member member1 = new Member("kim",20, RoleType.ADMIN);
            Member member2 = new Member("kim",30, RoleType.USER);
            Member member3 = new Member("lee",25,RoleType.USER);
            Member member4 = new Member("lee",15,RoleType.USER);

            Team team1 = new Team();
            Team team2 = new Team();
            team1.setName("teamA");
            team2.setName("teamB");

            member1.setTeam(team1);
            member2.setTeam(team1);
            member3.setTeam(team2);
            member4.setTeam(team2);

            em.persist(member1);
            em.persist(member2);
            em.persist(member3);
            em.persist(member4);
            em.persist(team1);
            em.persist(team2);

            em.flush();
            em.clear();

            JPAQueryFactory query = new JPAQueryFactory(em);
            QMember qMember = QMember.member;
            QTeam qTeam = new QTeam("t");

            MemberDTO param = new MemberDTO("kim", 20);
            BooleanBuilder builder = new BooleanBuilder();
            if(param.getUsername() != null && !param.getUsername().equals("")){
                builder.and(qMember.username.contains(param.getUsername()));
            }
            if(param.getAge() != null){
                builder.and(qMember.age.gt(20));
            }
            List<Member> members = query.selectFrom(qMember).where(memberNameContain(param), memberAgeGt(param)).fetch();
            members.stream().forEach(m-> System.out.println("m.getName() = " + m));


            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    private static BooleanExpression memberNameContain(MemberDTO param) {
        if(param.getUsername() == null || param.getUsername().equals("")){
            return null;
        }
        return QMember.member.username.contains(param.getUsername());
    }

    private static BooleanExpression memberAgeGt(MemberDTO param){
        if(param.getAge() == null){
            return null;
        }
        return QMember.member.age.gt(param.getAge());
    }

    private static void logic(EntityManager em) {
        Member member = new Member();
        em.persist(member);
        System.out.println
                ("member.id = " + member.getId());
    }

    static Member createMember(Long id, String username) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Member member = new Member();

        try {
            tx.begin();

            //member.setId(id);
            member.setUsername(username);
            em.persist(member);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        return member;
    }

    static void mergeMember(Member member) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            Member mergeMember = em.merge(member);

            System.out.printf("merge= " + member.getUsername());
            System.out.println("merge= " + mergeMember.getUsername());
            System.out.println("em.contains(member) = " + em.contains(member));
            System.out.println("em.contains(mergeMember) = " + em.contains(mergeMember));
            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
    }
}
