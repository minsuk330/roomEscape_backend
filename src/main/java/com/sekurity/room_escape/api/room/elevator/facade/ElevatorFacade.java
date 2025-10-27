package com.sekurity.room_escape.api.room.elevator.facade;

import com.sekurity.room_escape.api.room.elevator.dto.ElevatorReq;
import com.sekurity.room_escape.api.room.elevator.dto.ElevatorResp;
import com.sekurity.room_escape.domain.goal.entity.dto.resp.GoalResp;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ElevatorFacade {



  public ElevatorResp check(ElevatorReq req) {
    if (req.getNumbers()!=null && req.getNumbers().equals("11111111")) {
      return ElevatorResp.builder()
          .isSuccess(true)
          .build();
    }
    else {
      return ElevatorResp.builder()
          .isSuccess(false)
          .build();
    }
  }
}
