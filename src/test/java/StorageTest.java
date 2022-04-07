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

        Assert.assertEquals(Storage.storage, expectedMap);
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
    }
}