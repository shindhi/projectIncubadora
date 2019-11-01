package com.invillia.projectSpring.service;

import com.invillia.projectSpring.domain.Member;
import com.invillia.projectSpring.exceptions.ActionNotPermitedException;
import com.invillia.projectSpring.exceptions.NotFoundException;
import com.invillia.projectSpring.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


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

    @Transactional
    public void update(Member member){
        Member persisted = memberRepository.findById(member.getId())
                .orElseThrow(IllegalArgumentException::new);
        persisted.setName(member.getName());
        memberRepository.save(persisted);
    }

    @Transactional
    public void deleteById(Long id){
        memberRepository.findById(id).orElseThrow(()-> new NotFoundException(String.valueOf(id)));
        memberRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Member> findAll(){
        return memberRepository.findAll();
    }


    @Transactional(readOnly = true)
    public Member findById(Long id){
        return memberRepository.findById(id).orElseThrow(() -> new ActionNotPermitedException(String.valueOf(id)));
    }

    @Transactional(readOnly = true)
    public List<Member> findAllMembersByIdTeam(Long id) {
        return memberRepository.findAllMembersByIdTeam(id);
    }
}
