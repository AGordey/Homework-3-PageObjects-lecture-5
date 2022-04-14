package com.demoqa;

import Page.RegistrationFormPage;
import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class TestFormWithPageObjects {
    Faker faker = new Faker();

    String  firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            currentAddress = faker.address().streetAddress(),
            email = faker.internet().emailAddress(),
            number = faker.numerify("##########"),
            yearBD = "2000",
            mounthBD = "January",
            dayBD = "19",
            gender = "Male",
            subjects = "Hindi",
            hobbie = "Reading",
            picture = "1.png",
            stateCity = "Haryana",
            city = "Karnal";

    RegistrationFormPage registrationFormPage = new RegistrationFormPage ();

    @BeforeAll
    static void setUp() {
       // Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }
    @Test
    void fillFormTest () {
        registrationFormPage.openPage()
                .setFirstName(firstName)
                .setFirstName(lastName)
                .setEmail(email)
                .setUserNumber(number)
                .setCurrentAddress(currentAddress)
                .setGender(gender)
                .setSubjects(subjects)
                .setHobbie(hobbie)
                .setStateAndSity(stateCity, city)
                .setUploadPicture(picture);

                //Дата рождения
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption(yearBD);
        $(".react-datepicker__month-select").selectOption(mounthBD);
        $(".react-datepicker__day.react-datepicker__day--019").click();

        registrationFormPage.setSubmit();

        //Проверка введенных данных
        $(".modal-body").shouldHave
                (text(firstName),
                        text(lastName),
                        text(email),
                        text(number),
                        text(gender),
                        text(subjects),
                        text(hobbie),
                        text(stateCity + " " + city),
                        text(picture),
                        text(dayBD + " " + mounthBD + "," + yearBD),
                        text(currentAddress));

    }
}

