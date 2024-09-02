package com.example.bootagain.controller;

import com.example.bootagain.dto.MemberDto;
import com.example.bootagain.entity.Member;
import com.example.bootagain.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.dialect.function.LpadRpadPadEmulation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Slf4j
@Controller
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/members/new")
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

        return "redirect:/members/" + saved.getId();
    }

    // 특정 회원 조회
    @GetMapping("/members/{id}")
    public String show(@PathVariable Long id, Model model) {
        Member member = memberRepository.findById(id).orElse(null);
        model.addAttribute("member", member);
        return "members/show";
    }

    // 전체 회원 조회
    @GetMapping("/members")
    public String index(Model model) {
        List<Member> memberEntityList = memberRepository.findAll();
        model.addAttribute("memberList", memberEntityList);
        return "members/index";
    }

    // 회원 수정 페이지 보여주기
    @GetMapping("/members/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        // 회원 데이터 가져오기
        Member memberEntity = memberRepository.findById(id).orElse(null);
        // 모델에 등록
        model.addAttribute("member", memberEntity);
        return "members/edit";
    }

    // 회원 정보 수정 반영하기
    @PostMapping("/members/update")
    public String update(MemberDto memberDto) {
        // dto를 엔티티로 반환
        Member memberEntity = memberDto.toEntity();
        log.info(memberEntity.toString());

        // 기존 데이터 가져오기
        Member target = memberRepository.findById(memberEntity.getId()).orElse(null);
        // 새로운 값 db에 저장하기
        if (target != null) {
            memberRepository.save(memberEntity);
        }

        // 수정결과페이지(상세페이지)로 리다이렉트 해주기
        return "redirect:/members/" + memberEntity.getId();
    }

    // 회원 삭제하기
    @GetMapping("/members/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {
        // 삭제할것가져오기
        Member target = memberRepository.findById(id).orElse(null);

        // 삭제하고 메세지 띄우기
        if (target != null) {
            memberRepository.delete(target);
            rttr.addFlashAttribute("msg", "회원이 삭제되었습니다.");
        }

        // 리다이렉트하기
        return "redirect:/members";
    }
}
