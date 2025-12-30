package uk.ac.hw.group20.order;

import java.util.*;

import uk.ac.hw.group20.errorhandler.InvalidInputException;
import uk.ac.hw.group20.errorhandler.InvalidListSizeException;
import uk.ac.hw.group20.utils.OrderStatus;

public class Order {
    private String orderId;
    private Date dateCreated;
    private String customerId;
    private List<ShopMenuItem> menuItemList;
    private double subTotal;
    private OrderStatus status;
    
    public Order() {
    	
    }

    public Order(String orderId, Date dateCreated, String customerId, List<ShopMenuItem> menuItemList, double subTotal, OrderStatus status) {
       
    	
    	if(dateCreated == null) {
			throw new InvalidInputException("Order date can not be blank");
		}
    	
    	if(customerId == null || customerId.trim().isEmpty()) {
			throw new InvalidInputException("Customer ID can not be blank");
		}
    	
    	if(menuItemList == null || menuItemList.size() < 1) {
			throw new InvalidListSizeException("Order cannot be created without menu items");
		}
    	
    	if(subTotal < 0) {
			throw new InvalidInputException("Order amount cannot be less than 0");
		}
    	
    	this.orderId = orderId;
        this.dateCreated = dateCreated;
        this.customerId = customerId;
        this.menuItemList = menuItemList;
        this.subTotal = subTotal;
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<ShopMenuItem> getMenuItemList() {
        return menuItemList;
    }

    public void setMenuItemList(List<ShopMenuItem> menuItemList) {
        this.menuItemList = menuItemList;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", dateCreated=" + dateCreated +
                ", customerId='" + customerId + '\'' +
                ", menuItemList=" + menuItemList +
                ", subTotal=" + subTotal +
                ", status='" + status + '\'' +
                '}';
    }
}