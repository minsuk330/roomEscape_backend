package com.sekurity.room_escape.api.common.completion.dto.req;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
public class GameCreateReq {

  private String teamName;
  @Setter
  private LocalDateTime startTime;

}
