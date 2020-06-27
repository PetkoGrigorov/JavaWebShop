package framework.config;

import java.util.HashMap;

public class ConfigDB {

    private static final String envKey = "development";
    private static final HashMap<String, HashMap<String, String>> envConfig = new HashMap<String, HashMap<String, String>>() {{
        put("development", new HashMap<String, String>() {{
            put("Url", "jdbc:mysql://localhost:3306/java_web_shop");
            put("model.User", "root");
            put("Pass", "");
        }});
        put("stage", new HashMap<String, String>() {{
            put("Url", "jdbc:mysql://154.162.0.2:3306/hr");
            put("model.User", "stage_admin");
            put("Pass", "sg0025");
        }});
        put("production", new HashMap<String, String>() {{
            put("Url", "jdbc:mysql://192.168.29.3:3306/hr");
            put("model.User", "production_admin");
            put("Pass", "pr0256");
        }});
    }};


    private static String getEnvValue(String key) {
        return envConfig.get(envKey).get(key);
    }

    public static String getUrl() {
        return getEnvValue("Url");
    }

    public static String getUser() {
        return getEnvValue("model.User");
    }

    public static String getPass() {
        return getEnvValue("Pass");
    }

}
