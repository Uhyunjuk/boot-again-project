package com.example.bootagain.controller;

import com.example.bootagain.dto.MemberDto;
import com.example.bootagain.entity.Member;
import com.example.bootagain.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/signup")
    public String signup() {
        return "members/new";
    }

    @PostMapping("/join")
    public String join(MemberDto mdto) { // 1. 전송받은 폼데이터를 매개변수로 가져오기
        log.info(mdto.toString());

        // 2. mdto의 toEntity()를 호출해서 반환값을 member 엔티티에 저장
        Member member = mdto.toEntity();
        log.info(member.toString());

        // 3. 레포지토리를 이용해 엔티티 디비에 저장
        Member saved = memberRepository.save(member); // member 엔티티를 저장해 saved에 반환
        log.info(saved.toString());

        return "";
    }
}
