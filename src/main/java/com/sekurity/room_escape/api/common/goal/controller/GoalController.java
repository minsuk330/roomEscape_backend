package com.sekurity.room_escape.api.common.goal.controller;

import com.sekurity.room_escape.domain.goal.entity.dto.req.GoalEndReq;
import com.sekurity.room_escape.api.common.goal.facade.GoalFacade;
import com.sekurity.room_escape.domain.goal.entity.dto.req.GoalStartReq;
import com.sekurity.room_escape.domain.goal.entity.dto.resp.GoalResp;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("common/goal")
@Tag(name = "[유저] 문제/완료")
public class GoalController {

  private final GoalFacade goalFacade;

  @PostMapping("/end")
  public GoalResp complete(
      @RequestBody GoalEndReq req
  ) {
    return goalFacade.complete(req);
  }
  @PostMapping("/start")
  public void start(
      @RequestBody GoalStartReq req
  ) {
    goalFacade.gameStart(req);
  }

  @GetMapping("/list")
  public List<GoalResp> list() {
    return goalFacade.getGoals();
  }

}
