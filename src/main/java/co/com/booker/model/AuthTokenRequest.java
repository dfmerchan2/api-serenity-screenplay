package co.com.booker.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthTokenRequest {
  private String username;
  private String password;
}
