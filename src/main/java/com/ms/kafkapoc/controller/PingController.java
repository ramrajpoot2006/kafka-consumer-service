package com.ms.kafkapoc.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/ping")
@RequiredArgsConstructor
public class PingController {

  @GetMapping
  public String ping () {

    return "Hello Mr. Ram, how are you?";
  }

}
