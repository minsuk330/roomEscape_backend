package com.sekurity.room_escape.domain.goal.entity.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GoalResp {

  private String teamName;
  private String goalTime;
  private Integer rank;
  private String message;
  private boolean completed;

}
