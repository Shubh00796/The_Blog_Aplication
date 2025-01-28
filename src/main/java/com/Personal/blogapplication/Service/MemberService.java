package com.Personal.blogapplication.Service;

import com.Personal.blogapplication.Dtos.MemberDTO;
import com.Personal.blogapplication.Entity.Member;
import com.Personal.blogapplication.Repo.MemberRepository;
import com.Personal.blogapplication.Utils.DTOMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private DTOMapper dtoMapper;

    public MemberDTO addMember(MemberDTO memberDTO){
        log.info("getting the details of the memeber", memberDTO);
        Member memberEntity = dtoMapper.toMemberEntity(memberDTO);
        Member savedMember = memberRepository.save(memberEntity);

        return dtoMapper.toMemberDTO(savedMember);

    }

    public List<MemberDTO> getAllMembers() {
        return memberRepository.findAll().stream()
                .map(dtoMapper::toMemberDTO)
                .collect(Collectors.toList());
    }

    public MemberDTO getMemberById(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
        return dtoMapper.toMemberDTO(member);
    }
    public MemberDTO updateMember(Long id, MemberDTO memberDTO) {
        Member existingMember = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
        existingMember.setName(memberDTO.getName());
        existingMember.setEmail(memberDTO.getEmail());
        existingMember.setMembershipDate(memberDTO.getMembershipDate());
        return dtoMapper.toMemberDTO(memberRepository.save(existingMember));
    }

    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

}
