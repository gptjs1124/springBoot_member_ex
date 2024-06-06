package com.codingrecipe.member.service;

import com.codingrecipe.member.dto.MemberDTO;
import com.codingrecipe.member.entity.MemberEntity;
import com.codingrecipe.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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


    public MemberDTO login(MemberDTO memberDTO) {
        /*
           1. 회원이 입력한 이메일로 DB에서 조회를 함
           2. DB에서 조회한 비번과 사용자가 입력한 비번이 일치하는지 판단
        */
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
        if(byMemberEmail.isPresent()) {
            // 조회 결과가 있다 (해당 이메일을 가진 회원 정보가 있다.)
            MemberEntity memberEntity = byMemberEmail.get();
            if(memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())){
                //비밀번호 일치
                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity); // entity -> dto로 변환 후 리턴
                return dto;
            }else{
                //비밀번호 불일치(로그인 실패)
                return null;
            }
        } else {
            // 조회 결과가 없다 (해당 이메일을 가진 회원이 없다.)
            return null;
        }
    }

    public List<MemberDTO> findAll() {
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        List<MemberDTO> memberDTOList = new ArrayList<>();

        for(MemberEntity memberEntity: memberEntityList ){
            memberDTOList.add(MemberDTO.toMemberDTO(memberEntity));
        }

        return memberDTOList;
    }

    public MemberDTO findById(Long id) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
        if(optionalMemberEntity.isPresent()) {//값이 있으면 true or 값이 없으면 false
            return MemberDTO.toMemberDTO(optionalMemberEntity.get());
        }else{
            return null;
        }
    }

    public MemberDTO updateForm(String myEmaile) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmail(myEmaile);
        if(optionalMemberEntity.isPresent()){
            return MemberDTO.toMemberDTO(optionalMemberEntity.get());
        }else{
            return null;
        }
    }

    public void update(MemberDTO memberDTO) {
        memberRepository.save(MemberEntity.toUpdateMemberEntity(memberDTO));
        //id가 없으면 insert를 하고 id가 있으면 update를 해줌.
    }


    public int delete(Long id) {
        //TODO :: 회원 삭제하기 부터 시작
        //memberRepository.deleteById(id);
        return 0;
    }
}
