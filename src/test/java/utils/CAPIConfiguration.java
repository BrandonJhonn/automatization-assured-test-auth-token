package utils;

import factoryRequest.CFactoryRequest;

public class CAPIConfiguration {

    /** API AUTHENTICATION TOKEN*/
    public static final String AUTH_VERIFY = CGetProperties.getInstance().getvStrHost() + "/api/authentication/isauthenticated.json";
    public static final String AUTH_TOKEN = CGetProperties.getInstance().getvStrHost() + "/api/authentication/token.json";

    /** API PROJECTS*/
    public static final String CREATE_PROJECT = CGetProperties.getInstance().getvStrHost() + "/api/projects.json";
    public static final String GET_PROJECT = CGetProperties.getInstance().getvStrHost() + "/api/projects/%s.json";
    public static final String UPDATE_PROJECT = CGetProperties.getInstance().getvStrHost() + "/api/projects/%s.json";
    public static final String DELETE_PROJECT = CGetProperties.getInstance().getvStrHost() + "/api/projects/%s.json";
}
