package jpabook.start;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.matcher.NameMatcher;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "MEMBER")
@Entity
public class Member extends BaseEntity{


    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    private Integer age;
    private String username;
    private RoleType roleType;

    public Member(String username, Integer age, RoleType roleType){
        this.username = username;
        this.age = age;
        this.roleType = roleType;
    }

    @Embedded
    private Period workPeriod;

    @Embedded
    private Address address;




    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID", insertable = false, updatable = false)
    private Team team;

    public void setTeam(Team team){
        if (this.team != null){
            this.team.getMembers().remove(this);
        }
        this.team = team;
        team.getMembers().add(this);
    }

    @Override
    public String toString() {
        return "Member{" +
                "age=" + age +
                ", username='" + username + '\'' +
                ", roleType=" + roleType +
                '}';
    }
}
