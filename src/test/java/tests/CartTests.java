package tests;

import Base.BaseTest;
import Base.DriverManager;
import com.beust.ah.A;
import facades.AddToCartFacade;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.FilterSideBar;
import pages.Homepage;
import pages.ProductDetailPage;

public class CartTests extends BaseTest {
    @Test
    public void testCartHammers(){
        Homepage homepage = new Homepage(DriverManager.getDriver());
        FilterSideBar filterBar = homepage.getFilterBar();
        filterBar.selectFilterCategory("Hammer");
        ProductDetailPage productDetailPage = homepage.selectProduct(1);
        productDetailPage.increaseProductQty(2);
        productDetailPage.addToCart();
        CartPage cartPage = productDetailPage.goToCart();
        //Assertions
    }

    @Test
    public void testCartPliers(){
        Homepage homepage = new Homepage(DriverManager.getDriver());
        FilterSideBar filterBar = homepage.getFilterBar();
        filterBar.selectFilterCategory("Pliers");
        ProductDetailPage productDetailPage = homepage.selectProduct(1);
        productDetailPage.increaseProductQty(1);
        productDetailPage.addToCart();
        CartPage cartPage = productDetailPage.goToCart();
        //Assertions
    }

    @Test
    public void testCartHammersFacade(){
        AddToCartFacade addToCartFacade= new AddToCartFacade();
        CartPage cartPage = addToCartFacade.addToCart("Hammer", 2);

        //Assertions
        Assert.assertTrue(cartPage.isProceedPresent());
        Assert.assertTrue(cartPage.getProductName().contains("Hammer"));
    }
}
