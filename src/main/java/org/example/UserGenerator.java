package org.example;

import java.util.ArrayList;
import java.util.List;

public final class UserGenerator {

     public static final List<Client> clients = List.of(
            new Client(0, "bob", 777, new ArrayList<>(List.of(
                    new PhoneNumber("+123456789", Type.LANDLINE),
                    new PhoneNumber("+4355762", Type.MOBILE),
                    new PhoneNumber("+23464765", Type.MOBILE)
            ))),
            new Client(1, "greg", 23, new ArrayList<>(List.of(
                    new PhoneNumber("+4556754743", Type.LANDLINE)
            ))),
            new Client(2, "kek", 54, new ArrayList<>(List.of(
                    new PhoneNumber("+5778786", Type.LANDLINE),
                    new PhoneNumber("+12435632", Type.MOBILE)
            ))),
            new Client(3, "tom", 90, new ArrayList<>(List.of(
                    new PhoneNumber("+45876221", Type.LANDLINE),
                    new PhoneNumber("+3435535", Type.MOBILE),
                    new PhoneNumber("+5768687", Type.MOBILE),
                    new PhoneNumber("+90909009", Type.MOBILE)
            ))),
            new Client(4, "bruh", 0, new ArrayList<>(List.of(

            ))),
            new Client(5, "bob", 43, new ArrayList<>(List.of(
                    new PhoneNumber("+2223", Type.LANDLINE),
                    new PhoneNumber("+222", Type.MOBILE)
            ))),
            new Client(6, "a", 54, new ArrayList<>(List.of(

            ))),
            new Client(7, "b", 90, new ArrayList<>(List.of(
                    new PhoneNumber("+55789", Type.LANDLINE)
            ))),
            new Client(8, "c", 5455, new ArrayList<>(List.of(
                    new PhoneNumber("+1111", Type.LANDLINE),
                    new PhoneNumber("+11212", Type.MOBILE)
            ))),
            new Client(9, "d", 111, new ArrayList<>(List.of(
                    new PhoneNumber("+421113423", Type.MOBILE)
            )))
    );

    private UserGenerator() {

    }
}
