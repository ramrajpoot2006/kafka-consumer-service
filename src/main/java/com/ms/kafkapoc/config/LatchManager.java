package com.ms.kafkapoc.config;


import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;


@Component
public class LatchManager {
  public final CountDownLatch latch = new CountDownLatch(1);
}
