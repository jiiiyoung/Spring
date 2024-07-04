package hello.hello_spring.domain;

import jakarta.persistence.*;

// @Entity가 있으면 JPA가 관리해주는 엔티티이다 라는 뜻
@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // DB에 있는 column명이 username이면 다음과 같이 매핑한다.
    // @Column(name = "username")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}