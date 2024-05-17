package co.com.booker.stepdefinitions;

import io.cucumber.java.Before;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

import static co.com.booker.utilities.enums.EndPoints.END_POINTS_BASE;

public class Hook {

  @Before
  public void setStage() {
    OnStage.setTheStage(OnlineCast.whereEveryoneCan(CallAnApi.at(END_POINTS_BASE.getValue())));
  }
}
