package pages;

import org.openqa.selenium.WebDriver;

public class PageCreator {
    protected final WebDriver webDriver;
    private HomePage homePage;

    public PageCreator(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    public HomePage getHomePage(){
        return (homePage==null)?homePage = new HomePage(webDriver):homePage;
    }
}
