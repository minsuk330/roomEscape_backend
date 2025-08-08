package com.sekurity.room_escape.domain.goal.entity.dto.req;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
public class GoalStartReq {

  private String teamName;
  @Setter
  private LocalDateTime startTime;
}
