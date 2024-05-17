package co.com.booker.stepdefinitions;

import static co.com.booker.utilities.enums.ActorNotepad.CREATE_BOOKING_RESPONSE;
import static co.com.booker.utilities.enums.ActorNotepad.PATCH_BOOKING_REQUEST;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.apache.http.HttpStatus.SC_METHOD_NOT_ALLOWED;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.hamcrest.Matchers.equalTo;

import co.com.booker.model.DataBooking;
import co.com.booker.model.DataBookingId;
import co.com.booker.model.builder.DataBookingBuilder;
import co.com.booker.questions.TheMessageObtained;
import co.com.booker.tasks.PatchBooking;
import co.com.booker.tasks.TheReservationInformation;
import com.github.javafaker.Faker;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PatchBookingStepDefinition {

  @When("patch the information of a reservation created")
  public void patchTheInformationOfAReservationCreated() {
    DataBookingId createBookingResponse =
        theActorInTheSpotlight().recall(CREATE_BOOKING_RESPONSE.getKey());

    DataBooking patchBookingRequest = DataBookingBuilder.patchBookingRequest(createBookingResponse);

    theActorInTheSpotlight()
        .attemptsTo(PatchBooking.with(createBookingResponse.getBookingId(), patchBookingRequest));

    theActorInTheSpotlight().remember(PATCH_BOOKING_REQUEST.getKey(), patchBookingRequest);
  }

  @When("patch information for a non-existing reservation")
  public void toPatchInformationForANonExistingReservation() {
    DataBookingId createBookingResponse =
        theActorInTheSpotlight().recall(CREATE_BOOKING_RESPONSE.getKey());

    theActorInTheSpotlight()
        .attemptsTo(
            PatchBooking.with(
                    Faker.instance().number().digits(10),
                    DataBookingBuilder.patchBookingRequest(createBookingResponse))
                .withStatusCode(SC_METHOD_NOT_ALLOWED));
  }

  @When("patch reservation information without sending the ID")
  public void patchReservationInformationWithoutSendingTheID() {
    DataBookingId createBookingResponse =
        theActorInTheSpotlight().recall(CREATE_BOOKING_RESPONSE.getKey());

    theActorInTheSpotlight()
        .attemptsTo(
            PatchBooking.with("", DataBookingBuilder.patchBookingRequest(createBookingResponse))
                .withStatusCode(SC_NOT_FOUND));
  }

  @Then("can see the new reservation information")
  public void canSeeTheNewReservationInformation() {
    DataBooking patchBookingRequest =
        theActorInTheSpotlight().recall(PATCH_BOOKING_REQUEST.getKey());

    theActorInTheSpotlight()
        .attemptsTo(
            TheReservationInformation.areCorrect(
                patchBookingRequest, PatchBooking.getResponseBody()));
  }

  @Then("should be able to see the message {string}")
  public void shouldBeAbleToSeeTheMessage(String message) {
    theActorInTheSpotlight()
        .should(
            seeThat(
                TheMessageObtained.isCorrect(PatchBooking.messageErrorResponse(), message),
                equalTo(true)));
  }
}
