package com.ms.kafkapoc.model;


import java.util.Objects;
import java.util.Optional;


public record Customer(String customerId, Optional<String> name, String email) {

  public Customer (String customerId, Optional<String> name, String email) {
    this.customerId = customerId;
    this.name = Objects.requireNonNull(name);
    this.email = email;
  }

}
