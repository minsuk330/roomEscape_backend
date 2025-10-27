package com.sekurity.room_escape.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

  ENTITY_NOT_FOUND("%s 팀을 찾을 수 없습니다."),
  DUPLICATED("%s 중복된 데이터입니다."),
  BAD_REQUEST("잘못된 입력 값입니다."),
  ACCOUNT_NOT_FOUND("해당 계정을 찾을 수 없습니다."),
  INTERNAL_SERVER_ERROR("서버 에러입니다."),
  SESSION_EXPIRED("세션이 만료되었습니다. 다시 로그인해주세요.");
  private final String message;

  public String getCode() {
    return name();
  }
}

