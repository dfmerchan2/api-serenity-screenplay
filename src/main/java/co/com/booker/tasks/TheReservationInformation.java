package co.com.booker.tasks;

import co.com.booker.model.DataBooking;
import lombok.AllArgsConstructor;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;

@AllArgsConstructor
public class TheReservationInformation implements Task {

  private final DataBooking request;
  private final DataBooking response;

  @Step("{0} review the reservation information")
  @Override
  public <T extends Actor> void performAs(T actor) {
    actor.attemptsTo(
        Ensure.that(request.getFirstName()).isEqualTo(response.getFirstName()),
        Ensure.that(request.getLastName()).isEqualTo(response.getLastName()),
        Ensure.that(request.getTotalPrice()).isEqualTo(response.getTotalPrice()),
        Ensure.that(request.isDepositPaid()).isEqualTo(response.isDepositPaid()),
        Ensure.that(request.getBookingDates().getCheckIn())
            .isEqualTo(response.getBookingDates().getCheckIn()),
        Ensure.that(request.getBookingDates().getCheckOut())
            .isEqualTo(response.getBookingDates().getCheckOut()),
        Ensure.that(request.getAdditionalNeeds()).isEqualTo(response.getAdditionalNeeds()));
  }

  public static Performable areCorrect(DataBooking request, DataBooking response) {
    return instrumented(TheReservationInformation.class, request, response);
  }
}
