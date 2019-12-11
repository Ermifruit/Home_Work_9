import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
public class BookingParis2Test {
    @Test
    public void Paris2Test() throws InterruptedException {
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
        WebElement guestDropDown = driver.findElement(By.xpath(".//label[@id='xp__guests__toggle']"));
        guestDropDown.click();
        WebElement adults = driver.findElement(By.xpath("//*[@id=\"xp__guests__inputs-container\"]/div/div/div[1]/div/div[2]/button[2]/span"));
        adults.click();
        adults.click();
        WebElement rooms = driver.findElement(By.xpath("//*[@id=\"xp__guests__inputs-container\"]/div/div/div[3]/div/div[2]/button[2]/span"));
        rooms.click();
        WebElement searchHotels = driver.findElement(By.xpath(".//button[@type='submit']"));
        searchHotels.submit();
        WebElement rangeOfMoneyToPay = driver.findElement(By.xpath("//*[@id=\"filter_price\"]/div[2]/a[4]/div/span"));
        int rangeOfPriceOfHotelsForRich = Integer.parseInt(rangeOfMoneyToPay.getText().substring(4,7));

        WebElement hotelsForRich = driver.findElement(By.xpath(".//a[@data-id='pri-5']"));
        hotelsForRich.click();
        WebElement hotelsForRichFromLowestPrice = driver.findElement(By.xpath(".//a[@data-category='price']"));
        hotelsForRichFromLowestPrice.click();
        Thread.sleep(3000);
        WebElement priceOfTheCheapestHotelElement = driver.findElement(By.xpath("(.//div[@class='bui-price-display__value prco-inline-block-maker-helper '])[1]"));

        int priceOfTheCheapestHotelPerDay = (Integer.parseInt(priceOfTheCheapestHotelElement.getText().substring(4).replace(" ","")))/7;

        Assert.assertTrue(rangeOfPriceOfHotelsForRich<=priceOfTheCheapestHotelPerDay);
    }
}
