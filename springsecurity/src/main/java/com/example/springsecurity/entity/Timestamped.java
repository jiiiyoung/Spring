package com.example.springsecurity.entity;


import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 상속관계를 DB 테이블로 만들지 않고 각 테이블에 column을 추가하도록 하는 것
@EntityListeners(AuditingEntityListener.class) // Local시간을 사용할 때 필요
public class Timestamped {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column
    private LocalDateTime modifiedAt;

}
