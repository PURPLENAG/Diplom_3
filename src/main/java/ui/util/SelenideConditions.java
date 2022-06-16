package ui.util;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Condition.*;

public class SelenideConditions {

  public static final Condition clickable = and("can be clicked", visible, enabled);

}
