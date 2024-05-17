package co.com.booker.utilities;

import com.github.javafaker.Faker;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.Locale;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {

  public static final Faker faker = new Faker(new Locale("es-MX"));
  public static final String FORMAT_DATE = "yyyy-MM-dd";
  public static final String FORMAT_DATE_WITH_SLASH = "dd/MM/yyyy";
  public static final String FORMAT_DATE_WITH_SPACES = "dd MMMM yyyy";
}
