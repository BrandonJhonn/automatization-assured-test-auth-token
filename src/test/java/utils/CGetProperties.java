package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CGetProperties {

    private static CGetProperties instance = null;

    private String vStrHost;
    private String vStrUser;
    private String vStrPwd;
    private String vStrAccessToken;

    public String getvStrHost() {
        return vStrHost;
    }

    public void setvStrHost(String vStrHost) {
        this.vStrHost = vStrHost;
    }

    public String getvStrUser() {
        return vStrUser;
    }

    public void setvStrUser(String vStrUser) {
        this.vStrUser = vStrUser;
    }

    public String getvStrPwd() {
        return vStrPwd;
    }

    public void setvStrPwd(String vStrPwd) {
        this.vStrPwd = vStrPwd;
    }

    public String getvStrAccessToken() {
        return vStrAccessToken;
    }

    public void setvStrAccessToken(String vStrAccessToken) {
        this.vStrAccessToken = vStrAccessToken;
    }

    private CGetProperties() {

        Properties properties = new Properties();
        String vStrNameFile = "conection.properties";

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(vStrNameFile);

        if (inputStream != null) {
            try {
                properties.load(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        vStrHost = properties.getProperty("host");
        vStrUser = properties.getProperty("user");
        vStrPwd = properties.getProperty("pwd");
        vStrAccessToken = "";
    }

    public static CGetProperties getInstance() {
        if (instance == null)
            instance = new CGetProperties();
        return instance;
    }
}
