package com.sekurity.room_escape.api.room.office.controller;

import com.sekurity.room_escape.api.room.office.dto.OfficeReq;
import com.sekurity.room_escape.api.room.office.dto.OfficeResp;
import com.sekurity.room_escape.api.room.office.facade.OfficeFacade;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/room/office")
@RequiredArgsConstructor
@Tag(name = "[유저] 사무실")
public class OfficeController {

  private final OfficeFacade officeFacade;

  @PostMapping
  public OfficeResp submit(
      @RequestBody OfficeReq req
  ) {
    return officeFacade.check(req);
  }


}
