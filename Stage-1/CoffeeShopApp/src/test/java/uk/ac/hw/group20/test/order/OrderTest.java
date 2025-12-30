package uk.ac.hw.group20.test.order;

import java.util.*;

import org.junit.jupiter.api.*;

import uk.ac.hw.group20.order.Order;
import uk.ac.hw.group20.order.ShopMenuItem;
import uk.ac.hw.group20.utils.OrderStatus;

public class OrderTest {
	
	Order testOrder;
	Date currentDate;
	List<ShopMenuItem> menuItemList = new ArrayList<>();
	
	@BeforeEach
	public void setupOrderTest() {
		testOrder = new Order();
		currentDate = new Date();
		ShopMenuItem item = new ShopMenuItem("MU001", "Chicken", "FOD001", "Chicken wraps", 2, 2.50);
		menuItemList.add(item);
		testOrder = new Order("TEST0001", currentDate, "CS001", menuItemList, 5.0, OrderStatus.COMPLETED);
	}
	
	@Test
	public void testOrderConstructorParameter() {
		
		double totalAmount = testOrder.getMenuItemList().stream()
			    .mapToDouble(e -> e.getQuantity() * e.getCurrentPrice())
			    .sum();
		
		Assertions.assertTrue(testOrder != null);
		Assertions.assertTrue(menuItemList.size() == 1);
		Assertions.assertFalse(menuItemList.isEmpty());
		
		Assertions.assertEquals("TEST0001", testOrder.getOrderId());
		Assertions.assertEquals(currentDate, testOrder.getDateCreated());
		Assertions.assertEquals("CS001", testOrder.getCustomerId());
		Assertions.assertEquals(1, testOrder.getMenuItemList().size());
		Assertions.assertEquals(testOrder.getSubTotal(), totalAmount, 0.01);
		Assertions.assertEquals(OrderStatus.COMPLETED, testOrder.getStatus());
		
	}
	
	@Test
	public void testOrderToString() {

	    String orderString = testOrder.toString();
	    
	    Assertions.assertTrue(orderString.contains("OD001"));
	    Assertions.assertTrue(orderString.contains("CS001"));
	    Assertions.assertTrue(orderString.contains("Chicken wraps"));
	    Assertions.assertTrue(orderString.contains("COMPLETED"));
	}
	
	@Test
	public void testOrderSetters() {
	    Order order = new Order();
	    
	    order.setOrderId("OD002");
	    order.setDateCreated(currentDate);
	    order.setMenuItemList(menuItemList);
	    order.setCustomerId("CS002");
	    order.setSubTotal(20.0);
	    order.setStatus(OrderStatus.COMPLETED);
	    
	    Assertions.assertEquals("OD002", order.getOrderId());
	    Assertions.assertEquals(currentDate, testOrder.getDateCreated());
	    Assertions.assertEquals("CS002", order.getCustomerId());
	    Assertions.assertEquals(1, testOrder.getMenuItemList().size());
	    Assertions.assertEquals(20.0, order.getSubTotal());
	    Assertions.assertEquals(OrderStatus.COMPLETED, order.getStatus());
	}
	
	@AfterEach
	public void cleanOrder() {
		//Remove all items created by test class
		this.testOrder = null;
		this.currentDate = null;
		this.menuItemList.clear();
	}

}
