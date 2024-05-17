package co.com.booker.tasks;

import co.com.booker.model.DataBooking;
import co.com.booker.model.DataBookingId;
import io.restassured.http.ContentType;
import lombok.RequiredArgsConstructor;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.thucydides.core.annotations.Step;

import static co.com.booker.utilities.enums.EndPoints.BOOKING;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.apache.http.HttpStatus.SC_OK;

@RequiredArgsConstructor
public class PostCreateBooking implements Task {

  private int statusCode = SC_OK;
  private final DataBooking dataBookingRequest;

  @Step("{0} create a new reservation")
  @Override
  public <T extends Actor> void performAs(T actor) {
    actor.attemptsTo(
        Post.to(BOOKING.getValue())
            .with(
                request ->
                    request.contentType(ContentType.JSON).body(dataBookingRequest).log().all()));
    SerenityRest.lastResponse().print();
    actor.should(seeThatResponse(response -> response.statusCode(statusCode)));
  }

  public static PostCreateBooking with(DataBooking dataBookingRequest) {
    return instrumented(PostCreateBooking.class, dataBookingRequest);
  }

  public PostCreateBooking withStatusCode(int statusCode) {
    this.statusCode = statusCode;
    return this;
  }

  public static DataBookingId getResponseBody() {
    return SerenityRest.lastResponse().as(DataBookingId.class);
  }

  public static String messageErrorResponse() {
    return SerenityRest.lastResponse().body().asString();
  }
}
