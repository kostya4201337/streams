package org.example;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserService {

    public int getSumAgeByName(List<Client> clients, final String name) {
        return clients
                .stream()
                .filter(client -> client.getName().equals(name))
                .mapToInt(Client::getAge)
                .sum();
    }

    public LinkedHashSet<String> getNamesByMention(List<Client> clients) {
        Map<String, Long> mention = clients
                .stream()
                .map(Client::getName)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));


        return mention.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .collect(Collectors.toCollection(LinkedHashSet::new));

    }

    public boolean doesAgeGreaterExist(List<Client> clients, final int age) {
        return clients
                .stream()
                .anyMatch(client -> client.getAge() > age);
    }

    public LinkedHashMap<Long, String> convertToIdAndNameMap(List<Client> clients) {
        return clients
                .stream()
                .collect(Collectors.toMap(
                        Client::getId,
                        Client::getName,
                        (e1, e2) -> e2,
                        LinkedHashMap::new
                ));
    }

    public Map<Integer, List<Client>> convertToAgeAndSameAgeCollectionMap(List<Client> clients) {
        return clients
                .stream()
                .collect(Collectors.toMap(
                        Client::getAge,
                        Arrays::asList,
                        (prev, curr) -> Stream.concat(prev.stream(), curr.stream()).toList())
                );
    }

    public String getNumbers(List<Client> clients) {
        return clients
                .stream()
                .map(Client::getNumbers)
                .flatMap(Collection::stream)
                .map(PhoneNumber::getValue)
                .collect(Collectors.joining(", "));
    }

    public Client getOldestLandlineUser(List<Client> clients) {
        return clients
                .stream()
                .filter(client1 -> client1.getNumbers()
                        .stream()
                        .anyMatch(phoneNumber -> phoneNumber.getType() == Type.LANDLINE)
                )
                .max(Comparator.comparingInt(Client::getAge))
                .orElseThrow();
    }

}
