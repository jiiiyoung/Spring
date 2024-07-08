package hello.hello_spring.domain;

import jakarta.persistence.*;


// JPA는 인터페이스를 제공, Hibernate를 이용하여 구현하는 것.
// JPA는 객체, ORM(Object Relational_DB Mapping)

// @Entity가 있으면 JPA가 관리해주는 엔티티이다 라는 뜻
@Entity
public class Member {

    // GeneratedValue : pk 자동으로 설정해주는 것
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
