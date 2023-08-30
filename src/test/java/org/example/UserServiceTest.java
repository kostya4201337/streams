package org.example;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    private final UserService userService = new UserService();

    private final UserGenerator userGenerator = new UserGenerator();

    private final List<Client> clientList = userGenerator.getClients();

    @Test
    public void should_getSumByAge() {
        //given
        String name = "bob";

        //when
        int sum = userService.getSumAgeByName(clientList, name);

        //then
        int expectedSum = 820;
        assertEquals(expectedSum, sum);
    }

    @Test
    public void should_getNamesByMention() {
        //when
        Set<String> set = userService.getNamesByMention(clientList);

        //then
        Set<String> expectedSet = new LinkedHashSet<>(Set.of("bob", "kek", "a", "b", "c", "tom", "d", "bruh", "greg"));
        assertEquals(set, expectedSet);
    }

    @Test
    public void should_returnTrueIfAgeGreaterExists() {
        //when
        boolean isExist = userService.doesAgeGreaterExist(clientList,0);

        //then
        assertTrue(isExist);
    }

    @Test
    public void should_returnFalseIfAgeGreaterDoesNotExist() {
        //when
        boolean isExist = userService.doesAgeGreaterExist(clientList,99999);

        //then
        assertFalse(isExist);
    }

    @Test
    public void should_convertToIdAndNameMap() {
        //when
        Map<Long, String> map = userService.convertToIdAndNameMap(clientList);

        //then
        Map<Long, String> expectedMap = new LinkedHashMap<>(Map.of(0L, "bob", 1L, "greg", 2L, "kek", 3L, "tom", 4L, "bruh", 5L, "bob", 6L, "a", 7L, "b", 8L, "c", 9L, "d"));
        assertEquals(map, expectedMap);
    }

    @Test
    public void should_convertToAgeAndSameAgeCollectionMap() {
        //given
        final List<Client> clients = List.of(
                new Client(0, "bob", 54, new ArrayList<>(List.of(
                        new PhoneNumber("+123456789", Type.LANDLINE)
                ))),
                new Client(1, "greg", 23, new ArrayList<>(List.of(
                        new PhoneNumber("+4556754743", Type.LANDLINE)
                ))),
                new Client(2, "kek", 54, new ArrayList<>(List.of(
                )))
        );

        //when
        Map<Integer, List<Client>> map = userService.convertToAgeAndSameAgeCollectionMap(clients);

        //then
        Map<Integer, List<Client>> expectedMap = Map.of(
                54, List.of(
                        new Client(0, "bob", 54,
                                List.of(new PhoneNumber("+123456789", Type.LANDLINE))),
                        new Client(2, "kek", 54, List.of())),
                23, List.of(
                        new Client(1, "greg", 23,
                                List.of(new PhoneNumber("+4556754743", Type.LANDLINE))
                        )));
        assertEquals(map, expectedMap);
    }

    @Test
    public void should_getNumbers(){
        //when
        String numbers = userService.getNumbers(clientList);

        //then
        String expectedNumbers = "+123456789, +4355762, +23464765, +4556754743, +5778786, +12435632, +45876221, +3435535, +5768687, +90909009, +2223, +222, +55789, +1111, +11212, +421113423";
        assertEquals(numbers, expectedNumbers);
    }

    @Test
    public void should_getOldestLandlineUser() {
        //when
        Client oldest = userService.getOldestLandlineUser(clientList);

        //then
        Client expectedOldest = new Client (8, "c", 5455,
                List.of(new PhoneNumber("+1111", Type.LANDLINE),
                        new PhoneNumber("+11212", Type.MOBILE)
                )
        );
        assertEquals(oldest, expectedOldest);
    }
}