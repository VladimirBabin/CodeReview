import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class StorageTest {

    @Test
    public void get() {
        // создаем объект storage и кладем в него некоторые данные с помощью метода set
        Storage storage = new Storage();
        storage.set("server:name", "fido");

        List<Integer> listOfIntegers = new ArrayList<>();
        listOfIntegers.add(1);
        listOfIntegers.add(2);
        listOfIntegers.add(3);

        List<String> listOfStrings = new ArrayList<>();
        listOfStrings.add("one");
        listOfStrings.add("two");
        listOfStrings.add("three");

        storage.set(listOfIntegers, listOfStrings);

        storage.set(123, "one-two-three");

        // проверяем что полученные с помощью метода get данные соответсвуют искомым

        Assert.assertEquals(storage.get(123), "one-two-three");
        Assert.assertEquals(storage.get(listOfIntegers), listOfStrings);
        Assert.assertEquals(storage.get("server:name"), "fido");

        // если ключ равен null, пустой или не существует

        Assert.assertEquals("Key should not be null", storage.get(null));
        Assert.assertEquals("Key should not be empty/blank", storage.get(" "));
        Assert.assertEquals("Key should not be empty/blank", storage.get(""));
        Assert.assertEquals("nil", storage.get("foo"));

        storage.deleteAll();
    }

    @Test
    public void set() {
        // создаем объект storage и кладем в него некоторые данные с помощью метода set
        Storage storage = new Storage();
        storage.set("server:name", "fido");

        List<Integer> listOfIntegers = new ArrayList<>();
        listOfIntegers.add(1);
        listOfIntegers.add(2);
        listOfIntegers.add(3);

        List<String> listOfStrings = new ArrayList<>();
        listOfStrings.add("one");
        listOfStrings.add("two");
        listOfStrings.add("three");

        storage.set(listOfIntegers, listOfStrings);

        storage.set(123, "one-two-three");

        // создаем словарь с такими же данными

        Map<Object, Object> expectedMap = new HashMap<>();
        expectedMap.put("server:name", "fido");
        expectedMap.put(listOfIntegers, listOfStrings);
        expectedMap.put(123, "one-two-three");

        // проверяем что они равны

        Assert.assertEquals(expectedMap, Storage.storage);

        // проверяем правильную работу метода если ключ равен null, пустой, уже существует, не существует

        Assert.assertEquals("Key should not be null", storage.set(null, null));
        Assert.assertEquals("Key should not be empty/blank", storage.set("", ""));
        Assert.assertEquals("Key should not be empty/blank", storage.set(" ", " "));

        Assert.assertEquals("Key overwritten", storage.set(123, "one and two and three"));
        Assert.assertTrue(storage.storage.containsKey(123));
        Assert.assertEquals("OK", storage.set(345, "three-four-five"));
        Assert.assertTrue(storage.storage.containsKey(345));

        storage.deleteAll();
    }

    @Test
    public void delete() {
        // создаем объект storage и кладем в него некоторые данные с помощью метода set
        Storage storage = new Storage();
        storage.set("server:name", "fido");

        List<Integer> listOfIntegers = new ArrayList<>();
        listOfIntegers.add(1);
        listOfIntegers.add(2);
        listOfIntegers.add(3);

        List<String> listOfStrings = new ArrayList<>();
        listOfStrings.add("one");
        listOfStrings.add("two");
        listOfStrings.add("three");

        storage.set(listOfIntegers, listOfStrings);

        storage.set(123, "one-two-three");

        // проверяем что метод delete удаляет данные из storage и возвращает последнее значение по ключу

        Assert.assertEquals(storage.get("server:name"), storage.delete("server:name"));
        Assert.assertFalse(storage.storage.containsValue("fido"));
        Assert.assertEquals(storage.get(123), storage.delete(123));
        Assert.assertFalse(storage.storage.containsValue("one-two-three"));
        Assert.assertEquals(storage.get(listOfIntegers), storage.delete(listOfIntegers));
        Assert.assertFalse(storage.storage.containsValue(listOfStrings));
        Assert.assertTrue(storage.storage.isEmpty());

        // если ключ равен null, пустой или не существует

        Assert.assertEquals("Key should not be null", storage.get(null));
        Assert.assertEquals("Key should not be empty/blank", storage.get(" "));
        Assert.assertEquals("Key should not be empty/blank", storage.get(""));
        Assert.assertEquals("nil", storage.get("foo"));

        storage.deleteAll();
    }

    @Test
    public void keys() {
        // создаем объект storage и кладем в него некоторые данные с помощью метода set
        Storage storage = new Storage();
        storage.set("server:name", "fido");

        List<Integer> listOfIntegers = new ArrayList<>();
        listOfIntegers.add(1);
        listOfIntegers.add(2);
        listOfIntegers.add(3);

        List<String> listOfStrings = new ArrayList<>();
        listOfStrings.add("one");
        listOfStrings.add("two");
        listOfStrings.add("three");

        storage.set(listOfIntegers, listOfStrings);

        storage.set(123, "one-two-three");

        // создаем словарь с такими же данными

        Map<Object, Object> expectedMap = new HashMap<>();
        expectedMap.put("server:name", "fido");
        expectedMap.put(listOfIntegers, listOfStrings);
        expectedMap.put(123, "one-two-three");

        // проверяем что метод keys не возвращает null в случае если storage заполнен данными

        Assert.assertFalse(storage.keys().isEmpty());

        // проверяем что метод keys возвращает искомый сет значений по ключу

        Assert.assertEquals(expectedMap.keySet(), storage.keys());

        storage.deleteAll();
    }

    @Test
    public void deleteAll() {
        Storage storage = new Storage();
        storage.set("server:name", "fido");

        List<Integer> listOfIntegers = new ArrayList<>();
        listOfIntegers.add(1);
        listOfIntegers.add(2);
        listOfIntegers.add(3);

        List<String> listOfStrings = new ArrayList<>();
        listOfStrings.add("one");
        listOfStrings.add("two");
        listOfStrings.add("three");

        storage.set(listOfIntegers, listOfStrings);

        storage.set(123, "one-two-three");

        // проверяем что storage не пустой

        Assert.assertFalse(storage.storage.isEmpty());

        // проверяем что после удаления storage пустой

        storage.deleteAll();
        Assert.assertTrue(storage.storage.isEmpty());
    }
}