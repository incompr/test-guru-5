import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class GitTests {
    @BeforeAll
    static void beforeAll() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://github.com";
        // Configuration.browserSize = "1920x1080";
    }

    @Test
    void githubJUnitCodeExist() {
        open("/selenide/selenide");
        $("[aria-label='Repository']").$("#wiki-tab").click();
        $("#wiki-pages-box").shouldBe(Condition.visible);
        $("#wiki-pages-filter").setValue("SoftAssertions");
        //screenshot("softAssertions.png");
        $("#wiki-pages-box")/*.$(".details-reset")*/
                .$(byText("SoftAssertions"))
                .shouldBe(Condition.visible)
                .shouldHave(Condition.exactTextCaseSensitive("SoftAssertions"))
                .click();

        //screenshot("softAssertions2.png");
        $(".markdown-body").shouldHave(text("JUnit5 extension - com.codeborne.selenide.junit5.SoftAssertsExtension"));
        $(".markdown-body").shouldHave(text("Using JUnit5 extend test class:"));
        $(".markdown-body").shouldHave(text(
                """
                        @ExtendWith({SoftAssertsExtension.class})
                        class Tests {
                          @Test
                          void test() {
                            Configuration.assertionMode = SOFT;
                            open("page.html");

                            $("#first").should(visible).click();
                            $("#second").should(visible).click();
                          }
                        }"""
        ));
        $(".markdown-body").shouldHave(text("Or register extension inside test class:"));
        $(".markdown-body").shouldHave(text(
                """
                        class Tests {
                          @RegisterExtension\s
                          static SoftAssertsExtension softAsserts = new SoftAssertsExtension();

                          @Test
                          void test() {
                            Configuration.assertionMode = SOFT;
                            open("page.html");

                            $("#first").should(visible).click();
                            $("#second").should(visible).click();
                          }
                        }"""
        ));
    }
}
