package uk.ac.hw.group20.test.order;

import java.util.*;
import java.util.List;

import org.junit.jupiter.api.*;

import uk.ac.hw.group20.errorhandler.*;
import uk.ac.hw.group20.utils.OrderStatus;
import uk.ac.hw.group20.order.Order;
import uk.ac.hw.group20.order.ShopMenuItem;

public class TestMenuItemCustomExceptions {
	
	String menuItemId;
	String name;
	String categoryId;
	String description;
	int quantity;
	double currentPrice;
	ShopMenuItem item;
	
	@BeforeEach
	void setupVariables() {
		this.menuItemId = "MENUOOO";
		this.item = new ShopMenuItem("OOO", "TEST NAME", "BEV002", "TEST", 1, 1.0);
		this.name = "TEST NAME";
		this.categoryId = "BEV002";
		this.description = "TEST";
		this.quantity = 1;
		this.currentPrice = 1.0;
	}
	
	@Test
    void testInvalidNameException() {
		
		this.name = null;
		
		InvalidInputException exception = Assertions.assertThrows(InvalidInputException.class, () -> {
			new ShopMenuItem(this.menuItemId, this.name, this.categoryId, this.description, this.quantity, this.currentPrice);
        });

        Assertions.assertEquals("Item name can not be blank", exception.getMessage());
    }
	
	@Test
    void testInvalidCategoryIdException() {
		
		this.categoryId = null;
		
		InvalidInputException exception = Assertions.assertThrows(InvalidInputException.class, () -> {
			new ShopMenuItem(this.menuItemId, this.name, this.categoryId, this.description, this.quantity, this.currentPrice);
        });

        Assertions.assertEquals("Item category can not be blank", exception.getMessage());
    }

	@Test
    void testConvertionExceptionForQuantity() {
		
		this.quantity = 0;
		
		ConvertionException exception = Assertions.assertThrows(ConvertionException.class, () -> {
			new ShopMenuItem(this.menuItemId, this.name, this.categoryId, this.description, this.quantity, this.currentPrice);
        });

        Assertions.assertEquals("Item quantity must be greater than 1", exception.getMessage());
    }
	
	@Test
    void testConvertionExceptionForPrice() {
		
		this.currentPrice = 0;
		
		ConvertionException exception = Assertions.assertThrows(ConvertionException.class, () -> {
			new ShopMenuItem(this.menuItemId, this.name, this.categoryId, this.description, this.quantity, this.currentPrice);
        });

        Assertions.assertEquals("Item price must be greater than 0", exception.getMessage());
    }
}
