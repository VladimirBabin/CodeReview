import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Storage {
    public static Map<Object, Object> storage = new HashMap<>();

    public Object get(Object key) {
        if (key == null) {
            return "Key should not be null";
        } else if (key.toString().isBlank()) {
            return "Key should not be empty/blank";
        } else if (storage.containsKey(key)) {
            return storage.get(key);
        }
        return "nil";
    }

    public String set(Object key, Object value) {
        if (key == null) {
            return "Key should not be null";
        } else if (key instanceof String) {
            String str = (String) key;
            if (str.isBlank()) {
                return "Key should not be empty/blank";
            }
        } else if (storage.containsKey(key)) {
            storage.put(key, value);
            return "Key overwritten";
        }
        storage.put(key, value);
        return "OK";
    }

    public Object delete(Object key) {
        if (key == null) {
            return "Key should not be null";
        } else if (key.toString().isBlank()) {
            return "Key should not be empty/blank";
        } else if (storage.containsKey(key)) {
            return storage.remove(key);
        }
        return "nil";
    }

    public Set<Object> keys() {
        return storage.keySet();
    }

    public void deleteAll() {
        storage.clear();
    }
}
