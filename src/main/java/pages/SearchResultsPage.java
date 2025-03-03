package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchResultsPage extends BasePage {

    @FindBy(xpath = "//android.widget.ImageView[@resource-id='org.wikipedia:id/search_empty_image']")
    protected WebElement emptyPage;

    @FindBy(xpath = "//android.widget.AutoCompleteTextView[@resource-id='org.wikipedia:id/search_src_text']")
    protected WebElement searchField;

    @FindBy(xpath = "//androidx.recyclerview.widget.RecyclerView[@resource-id='org.wikipedia:id/search_results_list']/android.view.ViewGroup/android.widget.TextView")
    protected List<WebElement> searchResults;

    public SearchResultsPage(AppiumDriver driver) {
        super(driver);
    }

    public SearchResultsPage fillSearchField(String searchText) {
        waitForElementToBeVisible(emptyPage);
        fillIn(searchField, searchText);
        logger.info("Search the text - " + searchText);
        return this;
    }

    public String getTheFirstSearchResultTitle() {
        waitForListToBeVisible(searchResults);
        logger.info("The first Search result is - " + searchResults.get(0).getText());
        return searchResults.get(0).getText();
    }


}
