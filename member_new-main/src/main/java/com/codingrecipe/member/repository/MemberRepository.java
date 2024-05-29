package com.codingrecipe.member.repository;

import com.codingrecipe.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    /* JpaRepository<MemberEntity, Long>란?
       DB 붙을 때, JpaRepository<엔티티클래스 명 작성, 엔티티클래스의 pk의 타입이 뭔지?>
       DB에 붙을 때는 반드시 엔티티타입으로 넘겨야 한다.*/
}
