package com.codingrecipe.member.service;

import com.codingrecipe.member.dto.MemberDTO;
import com.codingrecipe.member.entity.MemberEntity;
import com.codingrecipe.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void save(MemberDTO memberDTO) {
        /* repository의 save메서드 호출 (조건. entity 객체를 넘겨줘야한다.) */
        // 1. dto -> entitiy로 변환 (Alt + enter는 좌변을 만들어줌 ㅎㅎ)
        MemberEntity memberEntity = MemberEntity.ToMemberEntity(memberDTO);
        // 2. repository의 save 메서드 호출 (save는 DB에 insert하라는 뜻.)
        memberRepository.save(memberEntity); // memberRepository의 save는 우리가 만드는게 아니라 jpa에서 공유해주는 메서드임.

    }
}