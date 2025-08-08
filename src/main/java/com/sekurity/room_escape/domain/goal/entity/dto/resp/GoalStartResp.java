package com.sekurity.room_escape.domain.goal.entity.dto.resp;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class GoalStartResp {

  private String message;
  private String teamName;
  private LocalDateTime startTime;
  private LocalDateTime endTime;


}
