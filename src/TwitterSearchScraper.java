import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.Duration;
import java.util.List;

public class TwitterSearchScraper {
    public static void main(String[] args) {
        // Đường dẫn tới geckodriver
        System.setProperty("webdriver.gecko.driver", "D:\\Project OOP\\Gecko\\geckodriver.exe");

        // Cấu hình cho Firefox
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("C:\\Users\\Admin\\AppData\\Local\\Mozilla Firefox\\firefox.exe");

        // Khởi tạo WebDriver
        WebDriver driver = new FirefoxDriver(options);

        try {
            // Truy cập vào Twitter
            driver.get("https://twitter.com");

            // Đợi tối đa 10 giây để trang tải
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            // Giả sử bạn đã đăng nhập, giờ thực hiện tìm kiếm
            // Tìm kiếm từ khóa liên quan đến blockchain
            WebElement searchBox = driver.findElement(By.xpath("//input[@aria-label='Search query']"));
            searchBox.sendKeys("#blockchain OR #crypto OR #NFT");  // Tìm theo hashtag hoặc từ khóa

            // Gửi yêu cầu tìm kiếm
            searchBox.submit();

            // Đợi kết quả hiển thị
            Thread.sleep(5000);

            // Lấy danh sách kết quả tweet (tìm các phần tử tweet có lượng tương tác cao)
            List<WebElement> tweets = driver.findElements(By.xpath("//article[@data-testid='tweet']"));

            // Mở file để ghi kết quả
            BufferedWriter writer = new BufferedWriter(new FileWriter("twitter_results.txt"));

            // Duyệt qua danh sách tweets và lấy thông tin
            for (WebElement tweet : tweets) {
                try {
                    // Lấy tên người dùng
                    WebElement userName = tweet.findElement(By.xpath(".//div[@dir='ltr']/span/span"));
                    String user = userName.getText();

                    // Lấy số lượng followers (nếu có, cần tìm đúng xpath)
                    WebElement followers = tweet.findElement(By.xpath(".//div[contains(@aria-label, 'followers')]"));
                    String followersCount = followers.getText();

                    // Ghi thông tin vào file
                    writer.write("User: " + user);
                    writer.write(" - Followers: " + followersCount);
                    writer.newLine();
                } catch (Exception e) {
                    System.out.println("Có lỗi xảy ra khi lấy thông tin từ một tweet: " + e.getMessage());
                }
            }

            // Đóng file sau khi ghi
            writer.close();
            System.out.println("Danh sách người dùng đã được ghi vào file twitter_results.txt");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đóng trình duyệt
            driver.quit();
        }
    }
}
