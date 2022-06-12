package page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.Set;

import static com.codeborne.selenide.CollectionCondition.size;
import static util.SelenideConditions.clickable;

public interface FormPage<P extends FormPage<P, F>, F extends Enum<F>> {

  ElementsCollection getFormElements();

  Set<F> getFormFields();

  default SelenideElement getFormElement(F field) {
    return getFormElements()
        .should(size(getFormFields().size()))
        .get(field.ordinal());
  }

  default P setFormValue(F field, String value) {
    getFormElement(field)
        .should(clickable)
        .setValue(value);
    //noinspection unchecked
    return (P) this;
  }

  default String getFormValue(F field) {
    return getFormElement(field).getValue();
  }

}
