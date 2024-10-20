import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        // Cấu hình đường dẫn tới GeckoDriver cho Firefox (thay thế bằng đường dẫn đúng của bạn)
        System.setProperty("webdriver.gecko.driver", "D:\\Project OOP\\Gecko\\geckodriver.exe");  // Thay đổi theo đường dẫn thực tế

        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("C:\\Users\\Admin\\AppData\\Local\\Mozilla Firefox\\firefox.exe");  // Đường dẫn thực tế đến Firefox

        // Khởi tạo một thể hiện của FirefoxDriver
        WebDriver driver = new FirefoxDriver(options);

        try {
            // Điều hướng đến Google
            driver.get("https://www.google.com");

            // Tìm hộp tìm kiếm và nhập từ khóa "Selenium WebDriver"
            WebElement searchBox = driver.findElement(By.name("q"));
            searchBox.sendKeys("NetTruyen");

            // Gửi form tìm kiếm
            searchBox.submit();

            // Đợi trang kết quả tải
            Thread.sleep(2000);  // Tạm dừng 2 giây để trang kết quả tải xong

            // Lấy danh sách các phần tử kết quả tìm kiếm (10 kết quả đầu tiên)
            List<WebElement> searchResults = driver.findElements(By.cssSelector("h3"));  // Lấy các tiêu đề của kết quả tìm kiếm

            // Mở file để ghi kết quả
            BufferedWriter writer = new BufferedWriter(new FileWriter("search_results_1.txt"));

            // Xuất 10 kết quả tìm kiếm đầu tiên ra file
            for (int i = 0; i < Math.min(10, searchResults.size()); i++) {
                String resultText = searchResults.get(i).getText();
                //System.out.println("Result " + (i + 1) + ": " + resultText);  // In ra console (tùy chọn)
                writer.write("Result " + (i + 1) + ": " + resultText);  // Ghi vào file
                writer.newLine();  // Xuống dòng trong file
            }

            // Đóng file sau khi ghi xong
            writer.close();
            System.out.println("Results written to search_results_1.txt");

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        } finally {
            // Đóng trình duyệt
            driver.quit();
        }
    }
}
