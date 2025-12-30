package uk.ac.hw.group20.order;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import uk.ac.hw.group20.utils.OrderStatus;

public class OrderLoader {
    private static final String FILE_NAME = "data/orders.csv";
    private static final String ARCHIVE_FILE = "data/orders_archive.csv";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //private static LinkedList<Order> orders = new LinkedList<>();
    //MenuItem menuItem = new MenuItem();

    /**
     * Loads orders from the CSV file into the LinkedList.
     */
    public static LinkedList<Order> loadOrdersFromCSV(FileType type) {
    	
    	LinkedList<Order> orders = new LinkedList<>();
    	File file;
    	
    	if(FileType.ORDER_ARCHIVE == type) {
    		file = new File(ARCHIVE_FILE);
    	} else {
    		file = new File(FILE_NAME);
    	}
        

        if (!file.exists()) {
            System.out.println("Orders file not found. Creating a new order list.");
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                
                String[] data = line.split(",");
                if (data.length == 6) {
                	List<ShopMenuItem> menuItems = ShopMenuItem.getFormatedMenuItems(data[3].trim());
                	OrderStatus status;
                	try {
                	    status = OrderStatus.valueOf(data[5].trim());
                	} catch (IllegalArgumentException e) {
                		System.out.println("Unknown order status: " + data[5].trim());
                	    status = OrderStatus.UNKNOWN;
                	}
                	
                    orders.add(new Order(
                        data[0].trim(),
                        DATE_FORMAT.parse(data[1].trim()),
                        data[2].trim(),
                        menuItems,
                        Double.parseDouble(data[4].trim()),
                        status
                    ));
                } else {
                    System.out.println("Invalid order details: " + line);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load orders.", e);
        }
        
        return orders;
    }

    /**
     * Adds a new order to the LinkedList and saves it to CSV.
     */
    public static boolean addOrder(Order newOrder) {
    	boolean response = false;
        try {
        	LinkedList<Order> orders = loadOrdersFromCSV(FileType.ORDERS);
			orders.add(newOrder);
			saveOrdersToCSV(orders);
			response = true;
		} catch (Exception e) {
			System.out.println("Failed to add an order: " + e.getMessage());
			 throw new RuntimeException("Failed to add an order: " + e.getMessage());
		}
        
        return response;
    }

    /**
     * Updates an existing order in the LinkedList.
     */
    public static void updateOrder(String orderId, OrderStatus newStatus) {
    	LinkedList<Order> orders = loadOrdersFromCSV(FileType.ORDERS);
    	if(orders.isEmpty() || orders.size() < 1) {
    		System.out.println("No orders available to update.");
            return;
    	}
    	
    	boolean updated = false;
        for (Order order : orders) {
            if (order.getOrderId().equals(orderId)) {
                order.setStatus(newStatus);
                updated = true;
                break;
            }
        }
        
        if (updated) {
            saveOrdersToCSV(orders);
            System.out.println("Order " + orderId + " has been updated to " + newStatus);
        } else {
            System.out.println("Order ID " + orderId + " was not found.");
        }
    }

    /**
     * Moves an order to the archive and removes it from the active list.
     */
    public static void completeOrder(String orderId) {
        Order completedOrder = null;
        LinkedList<Order> orders = loadOrdersFromCSV(FileType.ORDERS);

        Iterator<Order> iterator = orders.iterator();
        while (iterator.hasNext()) {
            Order order = iterator.next();
            if (order.getOrderId().equals(orderId)) {
                completedOrder = order;
                completedOrder.setStatus(OrderStatus.COMPLETED);
                iterator.remove();
                break;
            }
        }

        if (completedOrder != null) {
            saveOrdersToCSV(orders);
            archiveOrder(completedOrder);
        }
    }

    /**
     * Push an order to the last of the list.
     */
    public static void pushOrder(Order newOrder) {
    	LinkedList<Order> orders = loadOrdersFromCSV(FileType.ORDERS);
        orders.addLast(newOrder);
        saveOrdersToCSV(orders);
    }

    /**
     * Pop (remove) the last order from the list.
     */
    public static Order popOrder() {
    	LinkedList<Order> orders = loadOrdersFromCSV(FileType.ORDERS);
        if (orders.isEmpty()) {
        	System.out.println("The is no order to process");
        	return null;
        }

        Order lastOrder = orders.removeFirst();
        saveOrdersToCSV(orders);
        return lastOrder;
    }

    /**
     * Saves orders to the CSV file.
     */
    private static void saveOrdersToCSV(LinkedList<Order> orders) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            bw.write("OrderID,DateCreated,CustomerID,MenuItems,SubTotal,Status\n");
            for (Order order : orders) {
                bw.write(String.format("%s,%s,%s,%s,%.2f,%s\n",
                        order.getOrderId(),
                        DATE_FORMAT.format(order.getDateCreated()),
                        order.getCustomerId(),
                        ShopMenuItem.formatMenuItems(order.getMenuItemList()),
                        order.getSubTotal(),
                        order.getStatus()));
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to save orders.", e);
        }
    }

    /**
     * Saves completed orders to the archive file.
     */
    private static void archiveOrder(Order order) {
    	LinkedList<Order> orders = loadOrdersFromCSV(FileType.ORDERS);
    	//ToDo load saved archived orders, add new order and save
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVE_FILE, true))) {
            bw.write(String.format("%s,%s,%s,%s,%.2f,%s\n",
                    order.getOrderId(),
                    DATE_FORMAT.format(order.getDateCreated()),
                    order.getCustomerId(),
                    ShopMenuItem.formatMenuItems(order.getMenuItemList()),
                    order.getSubTotal(),
                    order.getStatus()));
        } catch (IOException e) {
            throw new RuntimeException("Failed to archive order.", e);
        }
    }

}