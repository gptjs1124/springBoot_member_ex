package com.codingrecipe.member.repository;

import com.codingrecipe.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    /* JpaRepository<MemberEntity, Long>란?
       DB 붙을 때, JpaRepository<엔티티클래스 명 작성, 엔티티클래스의 pk의 타입이 뭔지?>
       DB에 붙을 때는 반드시 엔티티타입으로 넘겨야 한다.*/

    //이메일로 회원 정보 조회 (select * from member_table where member_email=?)
    Optional<MemberEntity> findByMemberEmail(String memberEmail);
    /*
    * Optional : null 방지
    * MemberEntity : DB에 주고 받는 데이터는 엔티티객체로 넘겨야한다.
    * findByMemberEmail : select * from member_table where member_email=?
    * memberEmail : 메게변수
    * */
}
