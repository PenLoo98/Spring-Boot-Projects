package com.gachon.healthdiary.Entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name="member")
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    public Member(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }
}
