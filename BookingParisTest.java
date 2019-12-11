import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;
public class BookingParisTest {
    @Test
    public void ParisTest() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "D:/PVTAUto/HWork_9/src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://booking.com");
        driver.manage().window().maximize();
        WebElement country = driver.findElement(By.xpath("//*[@id=\"ss\"]"));
        country.sendKeys("Париж");
        WebElement datesDropDown = driver.findElement(By.xpath(".//div[@class='xp__dates xp__group']"));
        datesDropDown.click();
        WebElement dateOfArrival = driver.findElement(By.xpath(".//td[@data-date='2019-12-15']"));
        dateOfArrival.click();
        WebElement dateOfLeft = driver.findElement(By.xpath(".//td[@data-date='2019-12-22']"));
        dateOfLeft.click();
        WebElement searchHotels = driver.findElement(By.xpath(".//button[@type='submit']"));
        searchHotels.submit();

        WebElement cheapHotels = driver.findElement(By.xpath(".//a[@data-id='pri-2']"));
        String cheapestPriceString = cheapHotels.getAttribute("data-value");
        int cheapestPrice = Integer.parseInt(cheapestPriceString);
        cheapHotels.click();

        List<WebElement> hotelList = driver.findElements(By.xpath(".//div[@class='sr-cta-button-row']"));
        Assert.assertFalse(hotelList.isEmpty());

        WebElement theBestHotels = driver.findElement(By.xpath(".//a[@data-id='review_score-90']"));
        theBestHotels.click();
        Thread.sleep(3000);

        WebElement priceOfTheBestHotel = driver.findElement(By.xpath("//*[@id=\"hotellist_inner\"]/div[2]/div[2]/div[3]/div/div/div[1]/div/div[2]/div/div[2]/div[3]"));
        int pricePerDayAtTheBestHotel = (Integer.parseInt(priceOfTheBestHotel.getText().substring(4).replace(" ", ""))) / 7;
        System.out.println(pricePerDayAtTheBestHotel);
        Assert.assertTrue(cheapestPrice >= pricePerDayAtTheBestHotel);
    }
}
