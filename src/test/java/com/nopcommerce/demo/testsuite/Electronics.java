package com.nopcommerce.demo.testsuite;

import com.nopcommerce.demo.pages.*;
import com.nopcommerce.demo.testbase.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Electronics extends BaseTest {
    HomePage homePage = new HomePage();
    ComputersPage computersPage = new ComputersPage();
    DesktopsPage desktopsPage = new DesktopsPage();
    BuildPage buildPage = new BuildPage();
    ShoppingCartPage shoppingCartPage = new ShoppingCartPage();
    SignInPage signInPage = new SignInPage();
    CheckoutPage checkoutPage = new CheckoutPage();
    CellPhonesPage cellPhonesPage = new CellPhonesPage();
    NokiaPage nokiaPage = new NokiaPage();
    RegisterPage registerPage = new RegisterPage();

    @Test
    public void verifyUserShouldNavigateToCellPhonesPageSuccessfully() {
        homePage.mouseHoverOnElectronics();
        homePage.mouseHoverOnCellPhones();
        verifyTwoStrings("Cell phones", By.xpath("//h1[contains(text(),'Cell phones')]"));
    }

    @Test
    public void verifyThatTheProductAddedSuccessfullyAndPlaceOrderSuccessfully() throws InterruptedException {
        homePage.mouseHoverOnElectronics();
        homePage.mouseHoverOnCellPhones();
        verifyTwoStrings("Cell phones", By.xpath("//h1[contains(text(),'Cell phones')]"));
        cellPhonesPage.clickOnListView();
        Thread.sleep(3000);
        cellPhonesPage.clickOnNokia();
        verifyTwoStrings("Nokia Lumia 1020",By.xpath("//h1[contains(text(),'Nokia Lumia 1020')]"));
        verifyTwoStrings("$349.00",By.xpath("//span[@id='price-value-20']"));
        nokiaPage.changeQuantity("2");
        nokiaPage.clickOnAddToCart();
        verifyTwoStrings("The product has been added to your shopping cart",By.xpath("//p[@class = 'content']"));
        nokiaPage.clickOnClose();
        buildPage.mouseHoverToShoppingCart();
        Thread.sleep(3000);
        buildPage.mouseHoverToGoToCartAndClick();
        verifyTwoStrings("Shopping cart", By.xpath("//h1[contains(text(),'Shopping cart')]"));
        String QtyValue = getElement(By.xpath("//input[@class = 'qty-input']")).getAttribute("value");
        Assert.assertEquals(QtyValue,"2");
        verifyTwoStrings("$698.00",By.xpath("//span[text()='$698.00'][@class = 'product-subtotal']"));
        shoppingCartPage.clickOnTerms();
        shoppingCartPage.clickOnCheckout();
        verifyTwoStrings("Welcome, Please Sign In!", By.xpath("//h1[contains(text(),'Welcome, Please Sign In!')]"));
        signInPage.clickOnRegister();
        verifyTwoStrings("Register",By.xpath("//h1[contains(text(),'Register')]"));
        registerPage.clickOnGender();
        registerPage.enterFirstName("YK");
        registerPage.enterLastName("Modi");
        registerPage.enterEmail("prime28@gmail.com");
        registerPage.enterPassword("Prime@123");
        registerPage.enterConfirmPassword("Prime@123");
        registerPage.clickOnRegister();
        verifyTwoStrings("Your registration completed",By.xpath("//div[contains(text(),'Your registration completed')]"));
        registerPage.clickOnContinueRegister();
        shoppingCartPage.clickOnLogIn();
        signInPage.enterEmail("prime28@gmail.com");
        signInPage.enterPassword("Prime@123");
        signInPage.clickOnLogIn();
        verifyTwoStrings("Shopping cart", By.xpath("//h1[contains(text(),'Shopping cart')]"));
        shoppingCartPage.clickOnTerms();
        shoppingCartPage.clickOnCheckout();
        checkoutPage.enterFirstName("YK");
        checkoutPage.enterLastName("Modi");
        //checkoutPage.enterEmail("prime28@gmail.com");
        checkoutPage.selectCountry("United Kingdom");
        checkoutPage.enterCity("London");
        checkoutPage.enterAddress("abc");
        checkoutPage.enterPostCode("HA31HL");
        checkoutPage.enterPhoneNumber("09898245856");
        checkoutPage.clickOnSave();
        checkoutPage.clickOnShippingMethod2();
        checkoutPage.clickOnContinueShipping();
        checkoutPage.clickOnPaymentMethod();
        checkoutPage.clickOnContinuePayment();
        checkoutPage.selectCreditCard("visa");
        checkoutPage.enterCardHolderName("YK");
        checkoutPage.enterCardNumber("1111222233334444");
        checkoutPage.selectMonth("5");
        checkoutPage.selectYear("2030");
        checkoutPage.enterCardCode("123");
        checkoutPage.clickOnContinueCard();
        verifyTwoStrings("Payment Method: Credit Card", By.xpath("//li[@class='payment-method']"));
        verifyTwoStrings("Shipping Method: 2nd Day Air",By.xpath("//li[@class='shipping-method']"));
        verifyTwoStrings("$698.00",By.xpath("//span[text()='$698.00'][@class = 'product-subtotal']"));
        checkoutPage.clickOnConfirm();
        verifyTwoStrings("Thank you", By.xpath("//h1[contains(text(),'Thank you')]"));
        verifyTwoStrings("Your order has been successfully processed!", By.xpath("//strong[contains(text(),'Your order has been successfully processed!')]"));
        checkoutPage.clickOnConfirmOrder();
        verifyTwoStrings("Welcome to our store",By.xpath("//h2[contains(text(),'Welcome to our store')]"));
        homePage.clickOnLogOut();
        Assert.assertEquals(driver.getCurrentUrl(),"https://demo.nopcommerce.com/");

    }
}
