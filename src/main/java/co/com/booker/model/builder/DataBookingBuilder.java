package co.com.booker.model.builder;

import co.com.booker.model.BookingDates;
import co.com.booker.model.DataBooking;
import co.com.booker.model.DataBookingId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import static co.com.booker.utilities.Constants.FORMAT_DATE;
import static co.com.booker.utilities.Constants.faker;
import static co.com.booker.utilities.ConversionUtility.getCurrentDateFormatted;
import static co.com.booker.utilities.ConversionUtility.getFutureOrPastDate;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DataBookingBuilder {

  public static DataBooking bookingRequest() {
    return DataBooking.builder()
        .firstName(faker.name().firstName())
        .lastName(faker.name().lastName())
        .totalPrice(Integer.parseInt(faker.number().digits(3)))
        .depositPaid(true)
        .bookingDates(
            BookingDates.builder()
                .checkIn(getCurrentDateFormatted(FORMAT_DATE))
                .checkOut(getFutureOrPastDate(FORMAT_DATE, 5))
                .build())
        .additionalNeeds("Breakfast")
        .build();
  }

  public static DataBooking bookingRequestWithValueNull() {
    return DataBooking.builder()
        .firstName(null)
        .lastName(null)
        .totalPrice(Integer.parseInt(faker.number().digits(3)))
        .depositPaid(true)
        .bookingDates(
            BookingDates.builder()
                .checkIn(getCurrentDateFormatted(FORMAT_DATE))
                .checkOut(getFutureOrPastDate(FORMAT_DATE, 5))
                .build())
        .additionalNeeds("Breakfast")
        .build();
  }

  public static DataBooking patchBookingRequest(DataBookingId dataBookingId) {
    return DataBooking.builder()
        .firstName(faker.name().firstName())
        .lastName(faker.name().lastName())
        .totalPrice(dataBookingId.getBooking().getTotalPrice())
        .depositPaid(dataBookingId.getBooking().isDepositPaid())
        .bookingDates(
            BookingDates.builder()
                .checkIn(dataBookingId.getBooking().getBookingDates().getCheckIn())
                .checkOut(dataBookingId.getBooking().getBookingDates().getCheckOut())
                .build())
        .additionalNeeds(dataBookingId.getBooking().getAdditionalNeeds())
        .build();
  }
}
