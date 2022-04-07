import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Storage {
    public static Map<Object, Object> storage = new HashMap<>();

    public Object get(Object key) {
        return storage.get(key);
    }

    public void set(Object key, Object value) {
        storage.put(key, value);
    }

    public Object delete(Object key) {
        return storage.remove(key);
    }

    public Set<Object> keys() {
        return storage.keySet();
    }
}
