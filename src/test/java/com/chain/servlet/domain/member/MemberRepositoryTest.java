package com.chain.servlet.domain.member;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemberRepositoryTest {

    MemberRepository repository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        repository.clearStore();
    }

    @DisplayName("멤버를 저장할 수 있다.")
    @Test
    void save() {
        // given
        Member member = new Member("사람", 20);

        // when
        Member savedMember = repository.save(member);

        // then
        Member findMember = repository.findById(savedMember.getId());
        assertThat(findMember).isEqualTo(savedMember);
    }

    @DisplayName("가입된 모든 회원을 조회할 수 있다.")
    @Test
    void findAll() {
        // given
        Member member1 = new Member("철수", 20);
        Member member2 = new Member("영희", 20);
        repository.save(member1);
        repository.save(member2);

        // when
        List<Member> members = repository.findAll();

        // then
        assertThat(members.size()).isEqualTo(2);
        assertThat(members).contains(member1, member2);
    }
}