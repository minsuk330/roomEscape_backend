package com.sekurity.room_escape.domain.goal.entity.dto.req;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
public class GoalEndReq {

  @Setter
  private LocalDateTime endTime;
  private String teamName;
}
