package uk.ac.hw.group20.test.order;

import java.util.*;
import java.util.List;

import org.junit.jupiter.api.*;

import uk.ac.hw.group20.errorhandler.*;
import uk.ac.hw.group20.utils.OrderStatus;
import uk.ac.hw.group20.order.Order;
import uk.ac.hw.group20.order.ShopMenuItem;

public class TestOrderCustomExceptions {
	
	String orderId;
	Date dateCreated; 
	String customerId;
	List<ShopMenuItem> menuItemList;
	double subTotal;
	OrderStatus status;
	
	@BeforeEach
	void setupVariables() {
		this.menuItemList = new ArrayList<>();
		ShopMenuItem item = new ShopMenuItem("OOO", "TEST MENU", "BEV002", "TEST", 1, 1.0);
		this.orderId = "OOO";
		this.dateCreated = new Date();
		this.customerId = "OOOO";
		menuItemList.add(item);
		this.subTotal = 1.0;
		this.status =OrderStatus.COMPLETED;
	}
	
	
	@Test
    void testInvalidListSizeException() {
		
		menuItemList.clear();
		
		InvalidListSizeException exception = Assertions.assertThrows(InvalidListSizeException.class, () -> {
			new Order(this.orderId, this.dateCreated, this.customerId, this.menuItemList, this.subTotal, this.status);
        });

        Assertions.assertEquals("Order cannot be created without menu items", exception.getMessage());
    }
	
	@Test
    void testInvalidInputException() {
		
		this.customerId = null;
		
		InvalidInputException exception = Assertions.assertThrows(InvalidInputException.class, () -> {
			new Order(this.orderId, this.dateCreated, this.customerId, this.menuItemList, this.subTotal, this.status);
        });

        Assertions.assertEquals("Customer ID can not be blank", exception.getMessage());
    }
	
	@Test
    void testInvalidOrderAmount() {
		
		this.subTotal = -1;
		
		InvalidInputException exception = Assertions.assertThrows(InvalidInputException.class, () -> {
			new Order(this.orderId, this.dateCreated, this.customerId, this.menuItemList, this.subTotal, this.status);
        });

        Assertions.assertEquals("Order amount cannot be less than 0", exception.getMessage());
    }
	
	@Test
    void testOrderDate() {
		
		this.dateCreated = null;
		
		InvalidInputException exception = Assertions.assertThrows(InvalidInputException.class, () -> {
			new Order(this.orderId, this.dateCreated, this.customerId, this.menuItemList, this.subTotal, this.status);
        });

        Assertions.assertEquals("Order date can not be blank", exception.getMessage());
    }

}
