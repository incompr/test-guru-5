import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
public class GitTests {


    @BeforeAll
    static void beforeAll() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://github.com";
        // Configuration.browserSize = "1920x1080";
    }
    @Test
   void GithubJUnitCodeExist(){
            open("/");
    }
}
