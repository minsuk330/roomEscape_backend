package com.sekurity.room_escape.api.common.health.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
  @GetMapping("/")
  public String health() {
    return "OK";
  }
}
