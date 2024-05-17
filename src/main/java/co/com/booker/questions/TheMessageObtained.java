package co.com.booker.questions;

import lombok.AllArgsConstructor;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

@AllArgsConstructor
public class TheMessageObtained implements Question<Boolean> {

  private final String messageResponse;
  private final String messageExpected;

  @Override
  public Boolean answeredBy(Actor actor) {
    return messageResponse.equals(messageExpected);
  }

  public static TheMessageObtained isCorrect(String messageResponse, String messageExpected) {
    return new TheMessageObtained(messageResponse, messageExpected);
  }
}
