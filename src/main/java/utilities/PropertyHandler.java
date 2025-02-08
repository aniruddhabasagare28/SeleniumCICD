package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyHandler {

    protected static Properties prop = new Properties();
    public static final String SELGRID_URL_KEY="selenium.gridurl";

    static {
        try {
            PropertyHandler.loadPropertyFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void loadPropertyFile() throws IOException {
        InputStream input = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/configuration.properties");
        prop.load(input);
        System.out.println("Properties:");
        for (String key : prop.stringPropertyNames()) {
            String value = getProperty(key);
            System.out.println(key + "=" + value);
        }

    }

    public static String getProperty(String key){
        String systemKeyValue=System.getProperty(key);
        if(systemKeyValue!=null && !systemKeyValue.isEmpty()){
            return systemKeyValue;
        }
        return prop.getProperty(key);
    }

}
