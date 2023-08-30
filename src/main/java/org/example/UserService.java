package org.example;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserService {

    /**
     * Calculates the total age for a specific name.
     */
    public int getSumAgeByName(final List<Client> clients, final String name) {
        return clients
                .stream()
                .filter(client -> client.getName().equals(name))
                .mapToInt(Client::getAge)
                .sum();
    }

    /**
     * Gets names by their mention
     */
    public LinkedHashSet<String> getNamesByMention(final List<Client> clients) {
        return clients.stream()
                .map(Client::getName)
                .collect(Collectors.toCollection(LinkedHashSet::new));

    }

    /**
     * Checks if client with age greater exist
     */
    public boolean doesAgeGreaterExist(final List<Client> clients, final int age) {
        return clients
                .stream()
                .anyMatch(client -> client.getAge() > age);
    }

    /**
     * Converts list of clients into hashmap with id key and name value
     */
    public Map<Long, String> convertToIdAndNameMap(final List<Client> clients) {
        return clients
                .stream()
                .collect(Collectors.toMap(
                        Client::getId,
                        Client::getName,
                        (e1, e2) -> e2,
                        LinkedHashMap::new
                ));
    }

    /**
     * Converts list of clients into hashmap with age key and clients with the same age list value
     */
    public Map<Integer, List<Client>> convertToAgeAndSameAgeCollectionMap(final List<Client> clients) {
        return clients
                .stream()
                .collect(Collectors.toMap(
                        Client::getAge,
                        Arrays::asList,
                        (prev, curr) -> Stream.concat(prev.stream(), curr.stream()).toList())
                );
    }

    /**
     * Gets all phone numbers
     */
    public String getNumbers(final List<Client> clients) {
        return clients
                .stream()
                .map(Client::getNumbers)
                .flatMap(Collection::stream)
                .map(PhoneNumber::getValue)
                .collect(Collectors.joining(", "));
    }

    /**
     * Gets oldest landline user
     */
    public Client getOldestLandlineUser(final List<Client> clients) {
        return clients
                .stream()
                .filter(client1 -> client1.getNumbers()
                        .stream()
                        .anyMatch(phoneNumber -> Type.LANDLINE.equals(phoneNumber.getType()))
                )
                .max(Comparator.comparingInt(Client::getAge))
                .orElseThrow();
    }
}
