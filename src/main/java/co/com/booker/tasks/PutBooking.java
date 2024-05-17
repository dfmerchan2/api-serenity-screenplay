package co.com.booker.tasks;

import co.com.booker.model.DataBooking;
import io.restassured.http.ContentType;
import lombok.RequiredArgsConstructor;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Put;
import net.thucydides.core.annotations.Step;

import static co.com.booker.utilities.enums.EndPoints.BOOKING_ID;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.apache.http.HttpStatus.SC_OK;

@RequiredArgsConstructor
public class PutBooking implements Task {

  private int statusCode = SC_OK;
  private final String idBooking;
  private final DataBooking dataBookingRequest;

  @Step("{0} Update the reservation with the id: #idBooking")
  @Override
  public <T extends Actor> void performAs(T actor) {
    actor.attemptsTo(
        Put.to(BOOKING_ID.getValue())
            .with(
                request ->
                    request
                        .contentType(ContentType.JSON)
                        .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                        .pathParam("id", idBooking)
                        .body(dataBookingRequest)
                        .log()
                        .all()));
    SerenityRest.lastResponse().print();
    actor.should(seeThatResponse(response -> response.statusCode(statusCode)));
  }

  public static PutBooking with(String idBooking, DataBooking dataBookingRequest) {
    return instrumented(PutBooking.class, idBooking, dataBookingRequest);
  }

  public PutBooking withStatusCode(int statusCode) {
    this.statusCode = statusCode;
    return this;
  }

  public static DataBooking getResponseBody() {
    return SerenityRest.lastResponse().as(DataBooking.class);
  }

  public static String messageErrorResponse() {
    return SerenityRest.lastResponse().body().asString();
  }
}
