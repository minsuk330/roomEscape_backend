package com.sekurity.room_escape.api.room.elevator.controller;

import com.sekurity.room_escape.api.room.elevator.dto.ElevatorReq;
import com.sekurity.room_escape.api.room.elevator.dto.ElevatorResp;
import com.sekurity.room_escape.api.room.elevator.facade.ElevatorFacade;
import com.sekurity.room_escape.domain.goal.entity.dto.resp.GoalResp;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("room/elevator")
@Tag(name = "[유저] 엘리베이터")
public class ElevatorController {

  private final ElevatorFacade elevatorFacade;


  @PostMapping
  public ElevatorResp submit(
      @RequestBody ElevatorReq req
  ) {
    return elevatorFacade.check(req);
  }
}
