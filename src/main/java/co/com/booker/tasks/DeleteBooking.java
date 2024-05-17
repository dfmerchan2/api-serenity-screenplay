package co.com.booker.tasks;

import io.restassured.http.ContentType;
import lombok.RequiredArgsConstructor;
import net.thucydides.core.annotations.Step;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Delete;

import static co.com.booker.utilities.enums.EndPoints.BOOKING_ID;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.apache.http.HttpStatus.SC_CREATED;

@RequiredArgsConstructor
public class DeleteBooking implements Task {

  private int statusCode = SC_CREATED;
  private final String idBooking;

  @Step("{0} delete the reservation with the id: #idBooking")
  @Override
  public <T extends Actor> void performAs(T actor) {
    actor.attemptsTo(
        Delete.from(BOOKING_ID.getValue())
            .with(
                request ->
                    request
                        .contentType(ContentType.JSON)
                        .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                        .pathParam("id", idBooking)
                        .log()
                        .all()));
    SerenityRest.lastResponse().print();
    actor.should(seeThatResponse(response -> response.statusCode(statusCode)));
  }

  public static DeleteBooking with(String idBooking) {
    return instrumented(DeleteBooking.class, idBooking);
  }

  public DeleteBooking withStatusCode(int statusCode) {
    this.statusCode = statusCode;
    return this;
  }

  public static String messageResponse() {
    return SerenityRest.lastResponse().body().asString();
  }
}
