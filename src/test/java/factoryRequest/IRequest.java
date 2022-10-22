package factoryRequest;

import io.restassured.response.Response;

public interface IRequest {
    Response send(CInfoRequest requestInfo);
    Response sendAuthToken(CInfoRequest requestInfo);
}
