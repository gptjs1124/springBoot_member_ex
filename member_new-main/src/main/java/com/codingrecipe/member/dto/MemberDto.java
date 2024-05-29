package com.codingrecipe.member.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDTO {
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberName;


    /*
    *   @Getter
        @Setter
        @NoArgsConstructor //기본생성자를 기본으로 만들어준다.
        @AllArgsConstructor //필드값를 모두다 매게변수로하는 생성자를 만들어 준다.
        @ToString //DTO 값을 출력할때 ToString형태로 내보낸다.
    * */
}
