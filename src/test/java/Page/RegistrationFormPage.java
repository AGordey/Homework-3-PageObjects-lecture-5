package Page;

import Page.Components.CalendarComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormPage {


    public RegistrationFormPage openPage() {
        open("/automation-practice-form");
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");
        return this;

    }

    public RegistrationFormPage setFirstName(String value) {
        $("#firstName").setValue(value);
        return this;
    }

    public RegistrationFormPage setLastName(String value) {
        $("#lastName").setValue(value);
        return this;
    }

    public RegistrationFormPage setEmail(String value) {
        $("#userEmail").setValue(value);
        return this;
    }

    public RegistrationFormPage setUserNumber(String value) {
        $("#userNumber").setValue(value);
        return this;
    }

    public RegistrationFormPage setCurrentAddress(String value) {
        $("#currentAddress").setValue(value);
        return this;
    }

    public RegistrationFormPage setGender(String value) {
        $("#genterWrapper").$(byText(value)).click();
        return this;
    }

    public RegistrationFormPage setSubjects(String value) {
        $("#subjectsInput").setValue(value).pressEnter();
        return this;
    }

    public RegistrationFormPage setHobbie(String value) {
        $("#hobbiesWrapper").$(byText(value)).click();
        return this;
    }

    public RegistrationFormPage setStateAndSity(String statecity, String cityy) {
        $("#stateCity-wrapper").click();
        $(byText(statecity)).click();
        $("#city").click();
        $(byText(cityy)).click();
        return this;
    }

    public RegistrationFormPage setUploadPicture(String value) {
        $("#uploadPicture").uploadFromClasspath(value);
        return this;
    }

    public RegistrationFormPage setSubmit() {
        $("#submit").click();
        return this;
    }
    public RegistrationFormPage setCalendarDate (String day, String month, String year)  {
        $("#dateOfBirthInput").click();
        new Page.Components.CalendarComponent().setDate(day,month,year);
        return this;
    }
    public RegistrationFormPage checkResult (String value)  {
        $(".modal-body").shouldHave(text(value));
        return this;
    }
}