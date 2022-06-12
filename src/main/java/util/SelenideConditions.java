package util;

import static com.codeborne.selenide.Condition.and;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;

import com.codeborne.selenide.Condition;

public class SelenideConditions {

  public static final Condition clickable = and("can be clicked", visible, enabled);

}
