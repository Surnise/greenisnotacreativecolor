package manager;

import java.util.ResourceBundle;

public class ResourceManager {
    private static ResourceManager instance;
    private ResourceBundle bundle = ResourceBundle.getBundle("resources/db");

    public String getValue(String key){
        return bundle.getString(key);
    }

    public static synchronized ResourceManager getInstance(){
        if(instance == null){
            instance = new ResourceManager();
        }
        return instance;
    }
}
