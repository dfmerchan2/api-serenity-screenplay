package co.com.booker.tasks;

import co.com.booker.model.AuthTokenRequest;
import co.com.booker.model.AuthTokenResponse;
import io.restassured.http.ContentType;
import lombok.RequiredArgsConstructor;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.thucydides.core.annotations.Step;

import static co.com.booker.utilities.enums.EndPoints.AUTH_CREATE_TOKEN;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.apache.http.HttpStatus.SC_OK;

@RequiredArgsConstructor
public class PostAuthCreateToken implements Task {

  private int statusCode = SC_OK;
  private final AuthTokenRequest authTokenRequest;

  @Step("{0} generate a token for authentication")
  @Override
  public <T extends Actor> void performAs(T actor) {
    actor.attemptsTo(
        Post.to(AUTH_CREATE_TOKEN.getValue())
            .with(
                request ->
                    request.contentType(ContentType.JSON).body(authTokenRequest).log().all()));
    SerenityRest.lastResponse().print();
    actor.should(seeThatResponse(response -> response.statusCode(statusCode)));
  }

  public static PostAuthCreateToken with(AuthTokenRequest authTokenRequest) {
    return instrumented(PostAuthCreateToken.class, authTokenRequest);
  }

  public PostAuthCreateToken withStatusCode(int statusCode) {
    this.statusCode = statusCode;
    return this;
  }

  public static AuthTokenResponse getResponseBody() {
    return SerenityRest.lastResponse().as(AuthTokenResponse.class);
  }
}
