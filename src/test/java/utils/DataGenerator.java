package utils;

import static java.lang.System.currentTimeMillis;
import static org.apache.commons.lang3.RandomUtils.nextInt;

import model.User;

public abstract class DataGenerator {

  public static User generateUser() {
    var name = "at_" + generate36RadixId();
    var email = name + "@ya.ru";
    var password = "at_pwd";
    return new User(name, email, password);
  }

  public static String generate36RadixId() {
    return to36Radix(currentTimeMillis()) + to36Radix(nextInt(0, 36 * 36 * 36 * 36));
  }

  private static String to36Radix(long n) {
    return Long.toString(n, 36);
  }

}
