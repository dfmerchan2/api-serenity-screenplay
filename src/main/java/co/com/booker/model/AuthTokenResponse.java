package co.com.booker.model;

import lombok.Data;

@Data
public class AuthTokenResponse {
  private String token;
  private String reason;
}
