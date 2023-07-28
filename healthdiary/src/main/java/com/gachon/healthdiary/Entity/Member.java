package com.gachon.healthdiary.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@AllArgsConstructor
@ToString
@Entity(name="member")
@NoArgsConstructor
@Data
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    /**기존 Entity 값 갱신*/
    public void patch(Member member) {
        // email 변경사항이 있다면 갱신
        if(member.email != null){
            this.email = member.email;
        }
        // password 변경사항이 있다면 갱신
        if(member.password != null){
            this.password = member.password;
        }
    }
}
