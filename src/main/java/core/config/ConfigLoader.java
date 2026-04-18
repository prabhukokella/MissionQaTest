package core.config;

import java.util.Properties;
import java.io.FileInputStream;



public class ConfigLoader {

    private static Properties prop;

    static {
        try {
            String env = System.getProperty("env", "qa");

            prop = new Properties();
            prop.load(new FileInputStream(
                "src/test/resources/config/" + env + ".properties"
            ));

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config file");
        }
    }

    public static String get(String key) {
        return prop.getProperty(key);
    }
}