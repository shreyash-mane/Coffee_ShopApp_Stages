package uk.ac.hw.group20.report;

import java.util.*;

import javax.swing.*;
import javax.swing.table.*;

import uk.ac.hw.group20.order.FileType;
import uk.ac.hw.group20.order.Order;
import uk.ac.hw.group20.order.OrderLoader;
import uk.ac.hw.group20.order.ShopMenuItem;

public class Report {
	
	public static StringBuilder getOrderReport() {
	    LinkedList<Order> orders = OrderLoader.loadOrdersFromCSV(FileType.ORDER_ARCHIVE);
	    StringBuilder report = new StringBuilder("Order Report\n");
	    report.append("----------------------------\n");

	    Map<String, int[]> itemSummary = new HashMap<>();

	    for (Order order : orders) {
	        for (ShopMenuItem item : order.getMenuItemList()) {
	            String itemName = item.getName();
	            double itemPrice = item.getCurrentPrice();
	            int quantity = item.getQuantity();

	            itemSummary.putIfAbsent(itemName, new int[]{0, 0});
	            itemSummary.get(itemName)[0] += quantity;
	            itemSummary.get(itemName)[1] += (int) (quantity * itemPrice);
	        }
	    }

	    report.append(String.format("%-20s %10s %12s\n", "Item", "Quantity", "Revenue"));
	    report.append("----------------------------\n");
	    double totalRevenue = 0;

	    for (Map.Entry<String, int[]> entry : itemSummary.entrySet()) {
	        String itemName = entry.getKey();
	        int quantity = entry.getValue()[0];
	        double revenue = entry.getValue()[1];
	        totalRevenue += revenue;

	        report.append(String.format("%-20s %10d %12.2f\n", itemName, quantity, revenue));
	    }

	    report.append("----------------------------\n");
	    report.append(String.format("Total Income: $%.2f\n", totalRevenue));
	    report.append("----------------------------\n");
	    
	    report.append("Thank you for using the Coffee Shop Application!\n");

	    return report;
    
	}
	
	
	public static void showOrderReportDialog() {
		LinkedList<Order> orders = OrderLoader.loadOrdersFromCSV(FileType.ORDER_ARCHIVE);
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new Object[]{"Item Name", "Category", "Quantity", "Revenue ($)"});

        populateOrderReport(tableModel, orders);

        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        int option = JOptionPane.showConfirmDialog(null, scrollPane, 
                "Order Report", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

        
        if (option == JOptionPane.YES_OPTION) {
        	System.exit(0);
        }
    }
	
	public static void populateOrderReport(DefaultTableModel tableModel, List<Order> orders) {
        Map<String, int[]> itemSummary = new HashMap<>();
        
        for (Order order : orders) {
	        for (ShopMenuItem item : order.getMenuItemList()) {
	            String itemName = item.getName()+","+item.getCategoryId();
	            double itemPrice = item.getCurrentPrice();
	            int quantity = item.getQuantity();

	            itemSummary.putIfAbsent(itemName, new int[]{0, 0});
	            itemSummary.get(itemName)[0] += quantity;
	            itemSummary.get(itemName)[1] += (int) (quantity * itemPrice);
	        }
	    }

        double totalRevenue = 0;
        for (Map.Entry<String, int[]> entry : itemSummary.entrySet()) {
            String itemName = entry.getKey();
            String[] itemNameAndCategory = itemName.split(",");
            String name = itemNameAndCategory[0];
            String category = itemNameAndCategory[1];
            int quantity = entry.getValue()[0];
            double revenue = entry.getValue()[1];
            totalRevenue += revenue;

            tableModel.addRow(new Object[]{name, category, quantity, String.format("%.2f", revenue)});
        }

        tableModel.addRow(new Object[]{"", "", "-------------",""});
        tableModel.addRow(new Object[]{"", "", "Total Income: ", String.format("%.2f", totalRevenue)});
        tableModel.addRow(new Object[]{"", "", "-------------",""});
    }

}
