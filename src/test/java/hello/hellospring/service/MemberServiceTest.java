package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @Test
    void join() {
        Member member = new Member();
        member.setName("spring");

        Long saveId = memberService.join(member);

        Member m = memberService.findMember(saveId).get();
        assertThat(member.getName()).isEqualTo(m.getName());
    }

    @Test
    void duplicate_join_exception() {
        Member m1 = new Member();
        m1.setName("boot");

        Member m2 = new Member();
        m2.setName("boot");

        Long id1 = memberService.join(m1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(m2));
        assertThat(e.getMessage()).isEqualTo("Already Exist");
    }

    @Test
    void findMembers() {
    }

    @Test
    void findMember() {
    }
}