package user;

import com.github.javafaker.Faker;

public class UserGenerator {
    private static Faker faker = new Faker();

    public static User randomUser() {
        return new User(
                faker.internet().emailAddress(),
                faker.internet().password(6, 8),
                faker.internet().password(3, 5),
                faker.name().firstName()
        );
    }

}