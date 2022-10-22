package factoryRequest;

public class CFactoryRequest {

    public static IRequest make(String vStrType) {
        IRequest request;

        switch (vStrType) {
            case "post":
                request = new CPostRequest();
                break;
            case "put":
                request = new CPutRequest();
                break;
            case "delete":
                request = new CDeleteRequest();
                break;
            default:
                request = new CGetRequest();
                break;
        }

        return request;
    }
}
