package uk.ac.hw.group20.utils;

import java.util.*;

import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

import uk.ac.hw.group20.order.ShopMenuItem;
import uk.ac.hw.group20.order.MenuItemLoader;

public class CommonMethod {
	
	public DefaultListModel getModel(String category) {
		
		List<ShopMenuItem> menuItems = MenuItemLoader.loadMenuItemsFromCSV();

	    List<ShopMenuItem> categoryItems = MenuItemLoader.getMenuItemsByCategoryId(menuItems, category);
		
	    DefaultListModel model = new DefaultListModel();
		
		if(categoryItems.isEmpty()) {
			model.addElement("There are no items in " + category + " category");
		} else {
			for (ShopMenuItem item : categoryItems) {
			    model.addElement(item.displayMenuItem());
			}
		}
		
		return model;
	}
	
	public DefaultTableModel getTableModel(String[] columnNames, List<Object[]> tableRows, String table) {
		DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
		int totalQuantity = 0;
	    double totalPrice = 0.0;
	    
	    String valueIdentifier = "COST";
	    
	    if("Report".equalsIgnoreCase(table)) {
	    	valueIdentifier = "SALES";
	    }
		
		try {
			for(Object[] tableRow : tableRows) {
				tableModel.addRow(tableRow);
				try {
					totalQuantity += (int) tableRow[2];
				} catch (ClassCastException e) {
					totalQuantity += 0;
				}
	            try {
					totalPrice += (double) tableRow[4];
				} catch (ClassCastException e) {
					totalPrice += 0;
				}
			}
			
			Object[] overLine = {"", "", "-----", "", "-----", ""};
			tableModel.addRow(overLine);
			
			Object[] summaryRow = {
			    "TOTAL",
			    totalQuantity > 0 ? "QUANTITY" : "", totalQuantity > 0 ? totalQuantity : "",
			    totalPrice > 0 ? valueIdentifier : "", totalPrice > 0 ? totalPrice : ""
			};
	        tableModel.addRow(summaryRow);
	        
	        Object[] underLine = {"", "", "-----", "", "-----", ""};
			tableModel.addRow(underLine);
			
		} catch (Exception e) {
			System.out.println("Failed to create table model table headers: " + Arrays.toString(columnNames) + " due to: " + e.getMessage());
		}
		
		return tableModel;
	}

}
