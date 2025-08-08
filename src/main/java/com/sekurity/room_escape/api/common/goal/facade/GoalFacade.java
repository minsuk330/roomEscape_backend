package com.sekurity.room_escape.api.common.goal.facade;

import com.sekurity.room_escape.api.common.completion.dto.req.GameCreateReq;
import com.sekurity.room_escape.domain.goal.entity.Goal;
import com.sekurity.room_escape.domain.goal.entity.dto.req.GoalCreateReq;
import com.sekurity.room_escape.domain.goal.entity.dto.resp.GoalResp;
import com.sekurity.room_escape.domain.goal.service.GoalService;
import com.sekurity.room_escape.domain.member.entity.Member;
import com.sekurity.room_escape.domain.member.service.MemberService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class GoalFacade {
  private final GoalService goalService;
  private final MemberService memberService;

  @Transactional
  public GoalResp complete(GoalCreateReq req) {
    Member member = memberService.getByName(req.getTeamName());

    Goal goal = goalService.save(req, member);
    updateAllRank();

    return goalService.goalResp(goal);
  }

  public GoalCreateReq

  public List<GoalResp> getGoals() {
    List<Goal> goals = goalService.gets();
    return goals.stream().map(goalService::goalResp).toList();
  }

  @Transactional
  public void updateAllRank() {
    List<Goal> goals = goalService.getsByTime();

    for (int i = 0; i < goals.size(); i++) {
      Goal goal = goals.get(i);
      goal.setRank(i + 1);
    }
  }

}
