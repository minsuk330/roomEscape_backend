package com.sekurity.room_escape.api.common.goal.facade;

import com.sekurity.room_escape.domain.goal.entity.Goal;
import com.sekurity.room_escape.domain.goal.entity.dto.req.GoalEndReq;
import com.sekurity.room_escape.domain.goal.entity.dto.req.GoalStartReq;
import com.sekurity.room_escape.domain.goal.entity.dto.resp.GoalResp;
import com.sekurity.room_escape.domain.goal.entity.dto.resp.GoalStartResp;
import com.sekurity.room_escape.domain.goal.service.GoalService;
import com.sekurity.room_escape.domain.member.entity.Member;
import com.sekurity.room_escape.domain.member.service.MemberService;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
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
    try {
      Goal goal = goalService.getByTeamName(req.getTeamName());

      if (goal == null) {
        throw new IllegalStateException("게임을 먼저 시작해주세요.");
      }

      if (goal.isCompleted()) {
        throw new IllegalStateException("이미 다른 팀원이 완료했습니다.");
      }


      goal.setEndTime(LocalDateTime.now());
      goal.setCompleted(true);
      Duration duration = Duration.between(goal.getStartTime(), goal.getEndTime());
      goal.setActualTime(duration);

      updateAllRank();


      return goalService.goalResp(goal);

    } catch (OptimisticLockingFailureException e) {
      throw new IllegalStateException("다른 팀원이 먼저 완료했습니다.");
    }
  }

  @Transactional
  public GoalStartResp gameStart(GoalStartReq req) {

    try {
      Member member = memberService.getByName(req.getTeamName());
      Goal existingGoal = goalService.getByTeamName(member.getTeamName());

      if (existingGoal!=null) {
        //진행중인 게임
        if (existingGoal.getEndTime()==null) {
          return GoalStartResp.builder()
              .startTime(existingGoal.getStartTime())
              .teamName(existingGoal.getMember().getTeamName())
              .message("이미 시작된 게임입니다. 계속 진행하세요!")
              .build();
        }
        //완료된 게임
        else {
          return GoalStartResp.builder()
              .startTime(existingGoal.getStartTime())
              .endTime(existingGoal.getEndTime())
              .teamName(existingGoal.getMember().getTeamName())
              .message("이미 완료된 게임입니다.")
              .build();
        }
      }

      req.setStartTime(LocalDateTime.now());
      Goal newGoal = goalService.start(req, member);
      return GoalStartResp.builder()
          .teamName(newGoal.getMember().getTeamName())
          .startTime(newGoal.getStartTime())
          .message("게임이 시작되었습니다!")
          .build();
    }
    catch (DataIntegrityViolationException e) {
      Goal goal = goalService.getByTeamName(req.getTeamName());
      return GoalStartResp.builder()
          .teamName(goal.getMember().getTeamName())
          .startTime(goal.getStartTime())
          .message("다른 팀원이 게임을 시작했습니다. 잠시후에 다시 시도해주세요")
          .build();
    }

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
