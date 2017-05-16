package softuni.server;


import java.util.HashMap;
import java.util.Map;

public class Model {
    Map<String, Object> objects;

    public Model() {
        this.objects = new HashMap<>();
    }

    public void add(String key, String value){
        this.objects.put(key, value);
    }

    public Object get(String key){
        return this.objects.get(key);
    }
}
