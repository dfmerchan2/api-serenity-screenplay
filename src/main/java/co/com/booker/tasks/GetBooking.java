package co.com.booker.tasks;

import co.com.booker.model.ConsultBookingIdsResponse;
import co.com.booker.model.DataBooking;
import io.restassured.http.ContentType;
import lombok.RequiredArgsConstructor;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.thucydides.core.annotations.Step;

import java.util.List;

import static co.com.booker.utilities.enums.EndPoints.BOOKING_ID;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.apache.http.HttpStatus.SC_OK;

@RequiredArgsConstructor
public class GetBooking implements Task {

  private int statusCode = SC_OK;
  private final String idBooking;

  @Step("{0} consult the reservation with the id: #idBooking")
  @Override
  public <T extends Actor> void performAs(T actor) {
    actor.attemptsTo(
        Get.resource(BOOKING_ID.getValue())
            .with(
                request ->
                    request.contentType(ContentType.JSON).pathParam("id", idBooking).log().all()));

    SerenityRest.lastResponse().print();
    actor.should(seeThatResponse(response -> response.statusCode(statusCode)));
  }

  public static GetBooking inTheApplication(String idBooking) {
    return instrumented(GetBooking.class, idBooking);
  }

  public GetBooking withStatusCode(int statusCode) {
    this.statusCode = statusCode;
    return this;
  }

  public static DataBooking getResponseBody() {
    return SerenityRest.lastResponse().as(DataBooking.class);
  }

  public static List<ConsultBookingIdsResponse> getResponseBodyList() {
    return SerenityRest.lastResponse().jsonPath().getList("", ConsultBookingIdsResponse.class);
  }

  public static String messageResponse() {
    return SerenityRest.lastResponse().body().asString();
  }
}
