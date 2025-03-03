package tests.app_functional_tests.tests;

import constants.Constants;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import tests.app_functional_tests.LandingPageSkipping;

public class TestForSearchFunctional extends LandingPageSkipping {

    @Story("Search Functionality")
    @Test(description = "Positive test for Search Functionality")
    public void testForSearch(){
        homePage
                .clickOnSearchButton()
                .fillSearchField(Constants.SEARCH_TEXT);
        softAssert.assertEquals(searchResultsPage.getTheFirstSearchResultTitle(), Constants.SEARCH_TEXT);
        softAssert.assertAll();
    }

}
