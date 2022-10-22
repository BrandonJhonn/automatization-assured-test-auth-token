package cleanTest;

import factoryRequest.CFactoryRequest;
import factoryRequest.CInfoRequest;
import io.restassured.response.Response;
import org.joda.time.DateTime;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.CAPIConfiguration;
import utils.CGetProperties;

import java.util.Date;

import static org.hamcrest.Matchers.equalTo;

public class CProjectAPITest {

    Response response;
    JSONObject body = new JSONObject();
    CInfoRequest infoRequest = new CInfoRequest();

    @BeforeEach
    public void verifyAuthenticated() {
        /// VERIFY TOKEN AUTHENTICATED
        infoRequest.setUrl(CAPIConfiguration.AUTH_VERIFY);
        infoRequest.setBody(body.toString());
        response = CFactoryRequest.make("get").sendAuthToken(infoRequest);

        if (!response.getBody().as(Boolean.class)) {
            /// AUTH WHIT TOKEN
            infoRequest.setUrl(CAPIConfiguration.AUTH_TOKEN);
            infoRequest.setBody(body.toString());
            response = CFactoryRequest.make("get").send(infoRequest);
            response.then().body("UserEmail", equalTo(CGetProperties.getInstance().getvStrUser())).statusCode(200);

            CGetProperties.getInstance().setvStrAccessToken(
                    response.then().extract().path("TokenString")
            );
        }
    }

    @Test
    public void verifyCRUDProjectTest() {
        DateTime currentDate = DateTime.now();
        String vStrProjectName = "" + currentDate.getYear();
        vStrProjectName += currentDate.getMonthOfYear();
        vStrProjectName += currentDate.getDayOfMonth();
        vStrProjectName += "_TEST_API_GRUPO_2";

        /// CREATE PROJECT
        body.put("Content", vStrProjectName);
        infoRequest.setUrl(CAPIConfiguration.CREATE_PROJECT);
        infoRequest.setBody(body.toString());
        response = CFactoryRequest.make("post").sendAuthToken(infoRequest);
        response.then().body("Content", equalTo(body.get("Content"))).statusCode(200);
        int vIdProject = response.then().extract().path("Id");

        /// UPDATE PROJECT
        vStrProjectName += "_UPDATED";
        body.put("Content", vStrProjectName);
        infoRequest.setUrl(String.format(CAPIConfiguration.UPDATE_PROJECT, vIdProject));
        infoRequest.setBody(body.toString());
        response = CFactoryRequest.make("put").sendAuthToken(infoRequest);
        response.then().body("Content", equalTo(body.get("Content"))).statusCode(200);

        /// GET PROJECT
        infoRequest.setUrl(String.format(CAPIConfiguration.GET_PROJECT, vIdProject));
        infoRequest.setBody(body.toString());
        response = CFactoryRequest.make("get").sendAuthToken(infoRequest);
        response.then().body("Content", equalTo(body.get("Content"))).statusCode(200);

        /// DELETE PROJECT
        infoRequest.setUrl(String.format(CAPIConfiguration.DELETE_PROJECT, vIdProject));
        infoRequest.setBody(body.toString());
        response = CFactoryRequest.make("delete").sendAuthToken(infoRequest);
        response.then().body("Content", equalTo(body.get("Content"))).statusCode(200);
    }
}
