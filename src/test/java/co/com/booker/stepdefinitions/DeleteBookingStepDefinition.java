package co.com.booker.stepdefinitions;

import static co.com.booker.utilities.enums.ActorNotepad.CREATE_BOOKING_RESPONSE;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.apache.http.HttpStatus.SC_METHOD_NOT_ALLOWED;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;

import co.com.booker.model.DataBookingId;
import co.com.booker.questions.TheMessageObtained;
import co.com.booker.tasks.DeleteBooking;
import com.github.javafaker.Faker;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.Matchers;

public class DeleteBookingStepDefinition {

  @When("deletes the created reservation")
  public void deletesTheCreatedReservation() {
    DataBookingId createBookingResponse =
        theActorInTheSpotlight().recall(CREATE_BOOKING_RESPONSE.getKey());

    theActorInTheSpotlight().attemptsTo(DeleteBooking.with(createBookingResponse.getBookingId()));
  }

  @When("delete a reservation that does not exist")
  public void deleteAReservationThatDoesNotExist() {
    theActorInTheSpotlight()
        .attemptsTo(
            DeleteBooking.with(Faker.instance().number().digits(10))
                .withStatusCode(SC_METHOD_NOT_ALLOWED));
  }

  @When("sent the request to delete a reservation without sending the ID")
  public void sentTheRequestToDeleteAReservationWithoutSendingTheID() {
    theActorInTheSpotlight().attemptsTo(DeleteBooking.with("").withStatusCode(SC_NOT_FOUND));
  }

  @Then("should see the response {string} when deleting the reservation")
  public void shouldSeeTheResponseWhenDeletingTheReservation(String message) {
    theActorInTheSpotlight()
        .should(
            seeThat(
                TheMessageObtained.isCorrect(DeleteBooking.messageResponse(), message),
                Matchers.equalTo(true)));
  }
}
