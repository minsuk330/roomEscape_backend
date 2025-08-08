package com.sekurity.room_escape.domain.member.service;

import com.sekurity.room_escape.domain.member.entity.Member;
import com.sekurity.room_escape.domain.member.repository.MemberRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;

  public Member getByName(String name) {
    return memberRepository.findByTeamName(name);
  }

  public Member getById(Long id) {
    return memberRepository.findById(id).orElse(null);
  }

  public List<Member> gets() {
    return memberRepository.findAll();
  }

}
