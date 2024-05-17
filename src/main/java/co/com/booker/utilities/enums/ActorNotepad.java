package co.com.booker.utilities.enums;

public enum ActorNotepad {
  AUTH_TOKEN_RESPONSE("auth token response"),
  CREATE_BOOKING_RESPONSE("create booking response"),
  CREATE_BOOKING_REQUEST("create booking request"),
  UPDATE_BOOKING_REQUEST("update booking request"),
  PATCH_BOOKING_REQUEST("patch booking request");

  private final String key;

  ActorNotepad(String key) {
    this.key = key;
  }

  public String getKey() {
    return key;
  }
}
