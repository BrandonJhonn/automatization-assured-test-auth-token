package factoryRequest;

import io.restassured.response.Response;
import utils.CGetProperties;

import static io.restassured.RestAssured.given;

public class CDeleteRequest implements IRequest{
    @Override
    public Response send(CInfoRequest requestInfo) {
        Response response = given()
                .auth()
                .preemptive()
                .basic(CGetProperties.getInstance().getvStrUser(), CGetProperties.getInstance().getvStrPwd())
                .body(requestInfo.getBody())
                .log().all()
                .when()
                .delete(requestInfo.getUrl());

        response.then().log().all();

        return response;
    }

    @Override
    public Response sendAuthToken(CInfoRequest requestInfo) {
        Response response = given()
                .header("token", CGetProperties.getInstance().getvStrAccessToken())
                .body(requestInfo.getBody())
                .log().all()
                .when()
                .delete(requestInfo.getUrl());

        response.then().log().all();

        return response;
    }
}
