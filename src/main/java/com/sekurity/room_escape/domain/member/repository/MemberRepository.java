package com.sekurity.room_escape.domain.member.repository;

import com.sekurity.room_escape.domain.member.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

  Optional<Member> findByTeamName(String name);
}
