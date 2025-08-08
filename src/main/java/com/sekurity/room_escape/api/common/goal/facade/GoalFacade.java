package com.sekurity.room_escape.api.common.goal.facade;

import com.sekurity.room_escape.domain.goal.entity.Goal;
import com.sekurity.room_escape.domain.goal.entity.dto.req.GoalEndReq;
import com.sekurity.room_escape.domain.goal.entity.dto.req.GoalStartReq;
import com.sekurity.room_escape.domain.goal.entity.dto.resp.GoalResp;
import com.sekurity.room_escape.domain.goal.service.GoalService;
import com.sekurity.room_escape.domain.member.entity.Member;
import com.sekurity.room_escape.domain.member.service.MemberService;
import java.time.Duration;
import java.time.LocalDateTime;
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
  public GoalResp complete(GoalEndReq req) {
    Member member = memberService.getByName(req.getTeamName());
    Goal goal = goalService.getByMember(member);

    if (goal.getEndTime()!=null) {
      throw new IllegalStateException("이미 완료된 팀입니다.");
    }
    else {
      goal.setEndTime(LocalDateTime.now());
      goal.setActualTime(Duration.between(goal.getStartTime(), goal.getEndTime()));
    }

    updateAllRank();

    return goalService.goalResp(goal);
  }

  @Transactional
  public void gameStart(GoalStartReq req) {

    req.setStartTime(LocalDateTime.now());

    Member member = memberService.getByName(req.getTeamName());

    goalService.save(req,member);
  }

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
