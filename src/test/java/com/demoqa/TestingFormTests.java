package com.demoqa;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.files.DownloadActions.click;

public class TestingFormTests {
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
            stateCity = "Haryana",
            city = "Karnal";



    @BeforeAll
    static void setUp() {
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }
    @Test
    void fillFormTest () {
        open("/automation-practice-form");
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");

        //Простые поля для заполнения
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("#userNumber").setValue(number);
        $("#currentAddress").setValue(currentAddress);

        //Поле с радиобаттоном - выбор пола
        $("#genterWrapper").$(byText(gender)).click();

        //Дата рождения
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption(yearBD);
        $(".react-datepicker__month-select").selectOption(mounthBD);
        $(".react-datepicker__day.react-datepicker__day--019").click();

        //Поле предметов - выпадающее меню
        $("#subjectsInput").setValue(subjects).pressEnter();

        //Выбор хобби
        $("#hobbiesWrapper").$(byText(hobbie)).click();

        //Выбор города
        $("#stateCity-wrapper").click();
        $(byText(stateCity)).click();
        $("#city").click();
        $(byText(city)).click();

        //Зaгрузка файла
        $("#uploadPicture").uploadFromClasspath("1.png");

        //Кнопка Submit
        $("#submit").click();

        //Проверка введенных данных
        $(".modal-body").shouldHave
                (text(firstName),
                        text(lastName),
                        text(email),
                        text(number),
                        text(gender),
                        text(subjects),
                        text(hobbie),
                        text(stateCity),
                        text(city),
                        text("1.png"),
                        text(dayBD+" " + mounthBD + "," + yearBD),
                        text(currentAddress));

    }
}