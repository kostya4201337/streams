package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class UserServiceTest {

    private final UserService userService = new UserService();

    private final List<Client> clientList = UserGenerator.clients;

    @Test
    void should_getSumByAge() {
        //given
        String name = "bob";

        //when
        int sum = userService.getSumAgeByName(clientList, name);

        //then
        int expectedSum = 820;
        assertThat(expectedSum).isEqualTo(sum);
    }

    @Test
    void should_getNamesByMention() {
        //when
        Set<String> set = userService.getNamesByMention(clientList);

        //then
        Set<String> expectedSet = new LinkedHashSet<>(List.of("bob", "kek", "a", "b", "c", "tom", "d", "bruh", "greg"));
        assertThat(set).isEqualTo(expectedSet);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, -3, 15, 0, Integer.MIN_VALUE})
    void should_returnTrueIfAgeGreaterExists(int nums) {
        //when
        boolean isExist = userService.doesAgeGreaterExist(clientList, nums);

        //then
        assertThat(isExist).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {99999, Integer.MAX_VALUE})
    void should_returnFalseIfAgeGreaterDoesNotExist(int nums) {
        //when
        boolean isExist = userService.doesAgeGreaterExist(clientList, nums);

        //then
        assertThat(isExist).isFalse();
    }

    @Test
    void should_convertToIdAndNameMap() {
        //when
        Map<Long, String> map = userService.convertToIdAndNameMap(clientList);

        //then
        Map<Long, String> expectedMap = new LinkedHashMap<>(Map.of(0L, "bob", 1L, "greg", 2L, "kek", 3L, "tom", 4L, "bruh", 5L, "bob", 6L, "a", 7L, "b", 8L, "c", 9L, "d"));
        assertThat(map).isEqualTo(expectedMap);
    }

    @Test
    void should_convertToAgeAndSameAgeCollectionMap() {
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
        assertThat(map).isEqualTo(expectedMap);
    }

    @Test
    void should_getNumbers(){
        //when
        String numbers = userService.getNumbers(clientList);

        //then
        String expectedNumbers = "+123456789, +4355762, +23464765, +4556754743, +5778786, +12435632, +45876221, +3435535, +5768687, +90909009, +2223, +222, +55789, +1111, +11212, +421113423";
        assertThat(numbers).isEqualTo(expectedNumbers);
    }

    @Test
    void should_getOldestLandlineUser() {
        //when
        Client oldest = userService.getOldestLandlineUser(clientList);

        //then
        Client expectedOldest = new Client (8, "c", 5455,
                List.of(new PhoneNumber("+1111", Type.LANDLINE),
                        new PhoneNumber("+11212", Type.MOBILE)
                )
        );
        assertThat(oldest).isEqualTo(expectedOldest);
    }
}