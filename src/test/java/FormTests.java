import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class FormTests {

    @Test
    void testRegistrationForm() {
        Configuration.startMaximized = true;

        String firstName = "Alex";
        String lastName = "Smith";
        String email = "test@mail.com";
        String phoneNumber = "1234567890";
        String address = "Derybasovskaya Street, 1";
        String state = "Haryana";
        String city = "Panipat";

        // Given
        open("https://demoqa.com/automation-practice-form");
        $(".main-header").shouldHave(text("Practice Form"));

        // When
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $(byText("Male")).click();
        $("#userNumber").setValue(phoneNumber);

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("October");
        $(".react-datepicker__year-select").selectOption("1987");
        $(".react-datepicker__month").find(byText("26")).click();

        $("#subjectsInput").setValue("Math").pressEnter();
        $(byText("Sports")).click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/Toolsqa.jpg"));
        $("#currentAddress").setValue(address);
        $("#state").click();
        $("#state").find(byText(state)).click();
        $("#city").click();
        $("#city").find(byText(city)).click();
        $("#submit").click();

        // Then
        $(".modal-body").shouldHave(
                text(firstName), text(lastName), text(email), text(phoneNumber), text(address), text(state), text(city)
        );
    }
}
