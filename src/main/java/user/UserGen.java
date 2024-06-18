package user;

import net.datafaker.Faker;

public class UserGen {
  private static final Faker faker = new Faker();

  public static UserPojo genUser() {
    return new UserPojo(
        faker.internet().emailAddress(), faker.internet().password(), faker.name().firstName());
  }

  public static String genName() {
    return faker.name().firstName();
  }

  public static String genPassword() {
    return faker.internet().password(6, 7);
  }

  public static String genWrongPassword() {
    return faker.internet().password(0, 5);
  }

  public static String genEmail() {
    return faker.internet().emailAddress();
  }
}
