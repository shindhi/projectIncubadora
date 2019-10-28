package com.invillia.projectSpring.service;

import com.invillia.projectSpring.domain.Member;
import com.invillia.projectSpring.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class MemberServices {
    private final MemberRepository memberRepository;

    public MemberServices(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public void insert(Member member) {
        memberRepository.save(member);
    }
}
