package facades;

import Base.DriverManager;
import pages.CartPage;
import pages.FilterSideBar;
import pages.Homepage;
import pages.ProductDetailPage;

public class AddToCartFacade {

    public CartPage addToCart(String categoryName, int qty) {
        Homepage homepage = new Homepage(DriverManager.getDriver());
        FilterSideBar filterBar = homepage.getFilterBar();
        filterBar.selectFilterCategory(categoryName);
        ProductDetailPage productDetailPage = homepage.selectProduct(1);
        productDetailPage.increaseProductQty(qty);
        productDetailPage.addToCart();
        return productDetailPage.goToCart();
    }
}
