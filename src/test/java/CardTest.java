import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.remote.tracing.EventAttribute.setValue;


public class CardTest {



    private String addDate(int howManyDays, String format) {
        return LocalDate.now().plusDays(howManyDays).format(DateTimeFormatter.ofPattern(format));

    }
    String bookDate = addDate(5, "dd.MM.yyyy");

    @Test
    void shouldBook() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(bookDate);
        $("[data-test-id='name'] input").setValue("Тигр Евфратыч");
        $("[data-test-id='phone'] input").setValue("+79128800000");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        //$("[data-test-id='notification']").should(appear);
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(25))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + bookDate));
    }
}

//    @Test
//    void shouldRegisterByAccountNumber() throws InterruptedException {
//        open("http://localhost:9999");
//        $$(".tab-item").find(exactText("По номеру счёта")).click();
//        $("[name='number']").setValue("4055 0100 0123 4613 8564");
//        $("[name='phone']").setValue("+792000000000");
//        $$("button").find(exactText("Продолжить")).click();
//        $(withText("Успешная авторизация")).shouldBe(visible);
//        $(byText("Личный кабинет")).shouldBe(visible);
//
//    }
//
//    @Test
//    void shouldRegisterByAccountNumber() throws InterruptedException {
//        open("http://localhost:9999");
//        $$(".tab-item").find(exactText("По номеру счёта")).click();
//        $("[name='number']").setValue("4055 0100 0123 4613 8564");
//        $("[name='phone']").setValue("+792000000000");
//        $$("button").find(exactText("Продолжить")).click();
//        $(withText("Успешная авторизация"))
//                .shouldBe(visible, Duration.ofSeconds(5));
//        $(byText("Личный кабинет"))
//                .shouldBe(visible, Duration.ofSeconds(5));
//
