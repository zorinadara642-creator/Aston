import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneBook {

    private Map<String, List<String>> contacts = new HashMap<>();

    public void add(String surname, String phoneNumber) {
        if (contacts.containsKey(surname)) {
            List<String> phones = contacts.get(surname);
            phones.add(phoneNumber);
        } else {
            List<String> phones = new ArrayList<>();
            phones.add(phoneNumber);
            contacts.put(surname, phones);
        }
    }

    public List<String> get(String surname) {
        if (contacts.containsKey(surname)) {
            return contacts.get(surname);
        } else {
            return new ArrayList<>();
        }
    }
}
