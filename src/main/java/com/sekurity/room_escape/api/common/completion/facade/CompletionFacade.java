package com.sekurity.room_escape.api.common.completion.facade;

import com.sekurity.room_escape.api.common.completion.dto.req.GameCreateReq;
import com.sekurity.room_escape.domain.goal.service.GoalService;
import com.sekurity.room_escape.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CompletionFacade {

  private final GoalService goalService;
  private final MemberService memberService;

  //여기서 시작시간과 팀 이름을 저장해놓고
  //
  public void startGame(GameCreateReq req) {

  }

}
