package com.example.bootagain.dto;

import com.example.bootagain.entity.Member;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class MemberDto {

    private String email;
    private String password;

    // 받은 dto 객체를 엔티티 객체로 변환하는 함수
    public Member toEntity() {
        return new Member(null, email, password); // 전달받은 Member클래스(엔티티)의 생성자 형식에 맞게 적으면 됨
    }
}
