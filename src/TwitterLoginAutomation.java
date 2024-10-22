import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class TwitterLoginAutomation {
    public static void main(String[] args) {
        // Đường dẫn tới geckodriver
        System.setProperty("webdriver.gecko.driver", "D:\\Project OOP\\Gecko\\geckodriver.exe");

        // Cấu hình cho Firefox
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("C:\\Users\\Admin\\AppData\\Local\\Mozilla Firefox\\firefox.exe");  // Đường dẫn thực tế đến Firefox
        WebDriver driver = new FirefoxDriver(options);

        try {
            // Mở Twitter
            driver.get("https://x.com/login");

            // Đợi tối đa 10 giây để trang tải
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            // Điền tên người dùng hoặc email
            WebElement usernameInput = driver.findElement(By.name("text"));
            usernameInput.sendKeys("nkhuy05@gmail.com");

            // Nhấn nút tiếp tục (Next)
            WebElement nextButton = driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div/div/div/div/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div/div/div/button[2]/div"));
            nextButton.click();

            // Đợi một chút để trang tải
            Thread.sleep(3000);

            // Điền mật khẩu
            WebElement passwordInput = driver.findElement(By.name("password"));
            passwordInput.sendKeys("Nkhuy05***");

            // Nhấn nút đăng nhập (Log in)
            WebElement loginButton = driver.findElement(By.xpath("//span[text()='Log in']/ancestor::div[@role='button']"));
            loginButton.click();

            // Đợi để đảm bảo đăng nhập thành công
            Thread.sleep(5000);

            // Kiểm tra xem có đăng nhập thành công không
            if (driver.getCurrentUrl().contains("home")) {
                System.out.println("Đăng nhập thành công!");
            } else {
                System.out.println("Đăng nhập thất bại.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đóng trình duyệt
            driver.quit();
        }
    }
}
