package com.sekurity.room_escape.api.room.office.facade;

import com.sekurity.room_escape.api.room.office.dto.OfficeReq;
import com.sekurity.room_escape.api.room.office.dto.OfficeResp;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class OfficeFacade {

  public OfficeResp check(OfficeReq req) {
    if(!req.getId().isEmpty()&&!req.getPassword().isEmpty()){
      if (req.getId().equals("admin") && req.getPassword().equals("secure")) {
        return OfficeResp.builder()
            .isSuccess(true)
            .build();
      }
      else  {
        return OfficeResp.builder()
            .isSuccess(false)
            .build();
      }
    }
    else {
      return OfficeResp.builder()
          .isSuccess(false)
          .build();
    }
  }

}
