package core.config;

import core.interfaces.ConfigProvider;

import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader implements ConfigProvider {

    private static final Properties prop = new Properties();

    static {
        try (InputStream is = ConfigLoader.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {

            if (is == null) {
                throw new RuntimeException("config.properties not found");
            }

            prop.load(is);

        } catch (Exception e) {
            throw new RuntimeException("Config load failed", e);
        }
    }

    // ✅ STATIC (used everywhere)
    public static String get(String key) {
        return prop.getProperty(key);
    }

    // ✅ Interface method
    @Override
    public String getValue(String key) {
        return prop.getProperty(key);
    }
}