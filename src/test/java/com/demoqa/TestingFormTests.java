package com.demoqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.files.DownloadActions.click;

public class TestingFormTests {

    @BeforeAll
    static void setUp() {
//   Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void fillFormTest () {
        open("/automation-practice-form");
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");
        //Все простые поля для заполнения
        $("#firstName").setValue("OurFirstName");
        $("#lastName").setValue("OurLastName");
        $("#userEmail").setValue("OurEmail@email.com");
        $("#userNumber").setValue("1234567890");
        $("#currentAddress").setValue("OurCurrentAddress");

        //Поле с радиобаттоном - выбор пола
        $x("//*[@id='genterWrapper']/div[2]/div[2]").click();

        //Поле предметов - выпадающее меню
        $("#subjectsInput").setValue("Hindi").pressEnter();
        $("#subjectsInput").setValue("English").pressEnter();

        //Выбор хобби
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#hobbiesWrapper").$(byText("Reading")).click();
        $("#hobbiesWrapper").$(byText("Music")).click();

        //Выбор города
        $("#stateCity-wrapper").click();
        $(byText("Haryana")).click();
        $("#city").click();
        $(byText("Karnal")).click();

        //Зaгрузка файла
        $("#uploadPicture").uploadFromClasspath("1.png");

        //Дата рождения
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").$(byText("2000")).click();
        $(".react-datepicker__month-select").$(byText("January")).click();
        $("[aria-label$='" + "January" + " " + 9 + "th, " + 2000 + "']").click();

        //Кнопка Submit
        $("#submit").click();

        //Проверка введенных данных
                $(".modal-body").shouldHave
                (text("OurFirstName"),
                text("OurLastName"),
                text("OurEmail@email.com"),
                text("1234567890"),
                text("Female"),
                text("Hindi"),
                text("English"),
                text("Sports"),
                text("Reading"),
                text("Music"),
                text("Haryana"),
                text("Karnal"),
                text("1.png"),
                text("09 January,2000"),
                text("OurCurrentAddress"));

    }
}
