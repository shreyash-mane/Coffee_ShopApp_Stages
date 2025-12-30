package uk.ac.hw.group20.main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

import uk.ac.hw.group20.admin.LoginGui;
import uk.ac.hw.group20.admin.User;
import uk.ac.hw.group20.admin.UserLoader;
import uk.ac.hw.group20.order.ShopMenuItem;
import uk.ac.hw.group20.order.FileType;
import uk.ac.hw.group20.order.Order;
import uk.ac.hw.group20.order.OrderLoader;
import uk.ac.hw.group20.order.ShoppingCart;
import uk.ac.hw.group20.order.ShoppingCartManager;
import uk.ac.hw.group20.order.bill.BillManager;
import uk.ac.hw.group20.order.bill.BillResponse;
import uk.ac.hw.group20.report.Report;
import uk.ac.hw.group20.utils.*;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.IllegalFormatFlagsException;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.*;

public class HomeGui extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private JPanel homeContentPane;
	private JButton btnCoffee;
	private JScrollPane scrollPane;
	private JButton btnOther;
	private JButton btnFood;
	private JList lstMenuItem;
	private DefaultListModel model;
	public String loginUserId;
	private JLabel lblUserId;
	private JPanel pnlMainLeft;
	private JPanel pnlMainRight;
	private JMenuBar menuBar;
	private JMenu mnHome;
	private JMenuItem menuItemLogout;
	private JMenuItem menuItemExit;
	private JLabel lblItemSelected;
	private JLabel lblCategory;
	private JLabel lblQuantity;
	private JLabel lblPrice;
	private JTextField txtSelectedItem;
	private JTextField txtCategory;
	private JTextField txtQuantity;
	private JTextField txtPrice;
	private JButton btnAddItem;
	private JTable tblInformation;
	private DefaultTableModel tableModel;
	
	CommonMethod commonMethod = new CommonMethod();
	//ShoppingCartManager cartManager = new ShoppingCartManager();
	List<Object[]> tableRows = new ArrayList<>();
	private JLabel lblItemId;
	private JButton btnCheckout;
	private JButton btnReloadCart;
	private JButton btnRemoveCart;
	private JMenu mnOrder;
	private JMenuItem menuItemProcess;
	private JLabel lblTableTitle;
	private JButton btnProcessOrder;
	private JMenu menuReport;
	private JMenuItem menuItemOrdersReport;
	private JLabel lblSelectCustomer;
	private JComboBox cbCustomerId;

	public HomeGui(String userId) {
		setTitle("Coffee Shop App - Edinburg Group 20");
		initializeComponent(userId);
		menuEventHendler();
		buttonHandler();
		lblUserId.setText(userId);
		GroupLayout gl_homeContentPane = new GroupLayout(homeContentPane);
		gl_homeContentPane.setHorizontalGroup(
			gl_homeContentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_homeContentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(pnlMainLeft, GroupLayout.PREFERRED_SIZE, 379, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pnlMainRight, GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE))
		);
		gl_homeContentPane.setVerticalGroup(
			gl_homeContentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_homeContentPane.createSequentialGroup()
					.addGap(38)
					.addGroup(gl_homeContentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(pnlMainRight, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(pnlMainLeft, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		JScrollPane sclRight = new JScrollPane();
		
		GroupLayout glPnlMainRight = new GroupLayout(pnlMainRight);
		glPnlMainRight.setHorizontalGroup(
			glPnlMainRight.createParallelGroup(Alignment.LEADING)
				.addGroup(glPnlMainRight.createSequentialGroup()
					.addContainerGap()
					.addGroup(glPnlMainRight.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTableTitle, Alignment.TRAILING)
						.addComponent(sclRight, GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, glPnlMainRight.createSequentialGroup()
							.addGroup(glPnlMainRight.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnCheckout)
								.addGroup(glPnlMainRight.createSequentialGroup()
									.addComponent(btnReloadCart, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnRemoveCart, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)))
							.addGap(70)
							.addComponent(btnProcessOrder)))
					.addContainerGap())
		);
		glPnlMainRight.setVerticalGroup(
			glPnlMainRight.createParallelGroup(Alignment.LEADING)
				.addGroup(glPnlMainRight.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTableTitle)
					.addGap(15)
					.addComponent(sclRight, GroupLayout.PREFERRED_SIZE, 325, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(glPnlMainRight.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnReloadCart)
						.addComponent(btnRemoveCart)
						.addComponent(btnProcessOrder))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCheckout)
					.addContainerGap(40, Short.MAX_VALUE))
		);
		
		tblInformation = new JTable();
		sclRight.setViewportView(tblInformation);
		pnlMainRight.setLayout(glPnlMainRight);
		homeContentPane.setLayout(gl_homeContentPane);
	}

	private void initializeComponent(String userId) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 904, 596);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnHome = new JMenu("Home");
		menuBar.add(mnHome);
		
		menuItemLogout = new JMenuItem("Logout");
		
		mnHome.add(menuItemLogout);
		
		menuItemExit = new JMenuItem("Close");
		mnHome.add(menuItemExit);
		
		mnOrder = new JMenu("Orders");
		menuBar.add(mnOrder);
		
		menuItemProcess = new JMenuItem("View Order");
		
		mnOrder.add(menuItemProcess);
		
		menuReport = new JMenu("Report");
		menuBar.add(menuReport);
		
		menuItemOrdersReport = new JMenuItem("Orders");
		menuReport.add(menuItemOrdersReport);
		
		homeContentPane = new JPanel();
		homeContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(homeContentPane);
		
		//Object[] title = new String[] {"Dr.", "Mr.", "Miss.", "Ms."};
		pnlMainLeft = new JPanel();
		pnlMainLeft.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Application Panel", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		pnlMainRight = new JPanel();
		pnlMainRight.setBackground(new Color(238, 238, 238));
		pnlMainRight.setBorder(new TitledBorder(null, "Information Panel", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		btnCoffee = new JButton("Beverage");
		btnFood = new JButton("Food Items");
		btnOther = new JButton("Other Items");
		btnCheckout = new JButton("Complete & Pay");
		btnReloadCart = new JButton("Reload Cart");
		btnRemoveCart = new JButton("Remove All Items");
		btnProcessOrder = new JButton("Process Order");
		
		JLabel lblHeader = new JLabel("Browse by Category");
		
		scrollPane = new JScrollPane();
		scrollPane.setToolTipText("Click to select menu item from the list");
		
		lblUserId = new JLabel("");
		lblItemSelected = new JLabel("Select Item");
		lblCategory = new JLabel("Category");
		lblQuantity = new JLabel("Quantity");
		lblPrice = new JLabel("Price");
		lblTableTitle = new JLabel("");
		
		txtSelectedItem = new JTextField();
		txtSelectedItem.setEditable(false);
		txtSelectedItem.setColumns(10);
		
		txtCategory = new JTextField();
		txtCategory.setEditable(false);
		txtCategory.setColumns(10);
		
		txtQuantity = new JTextField();
		txtQuantity.setColumns(10);
		
		txtPrice = new JTextField();
		txtPrice.setEditable(false);
		txtPrice.setColumns(10);
		
		btnAddItem = new JButton("Add Item");
		
        lblItemId = new JLabel("");
		
		cbCustomerId = new JComboBox();
		lblSelectCustomer = new JLabel("Select Customer");
		
	    User user = UserLoader.getUserById(userId.trim());
	    System.out.println("User ID: " + userId + " and other login in details: " + user);
	    
	    if(user != null && Constant.CUSTOMER_ROLE_ID.equals(user.getRoleId())) {
	    	cbCustomerId.setVisible(false);
	    	lblSelectCustomer.setVisible(false);
	    } else {
	    	List<User> users = UserLoader.getUserByRoleId(Constant.CUSTOMER_ROLE_ID);
	    	//String[] userArray = new String[users.size()];
	    	String[] userArray = new String[users.size()];
	    	for (int i = 0; i < users.size(); i++) {
	    	    userArray[i] = users.get(i).getUserId() + " - " + users.get(i).getUsername();
	    	}
	    	
	    	cbCustomerId = new JComboBox(userArray);
	    	cbCustomerId.setVisible(true);
	    	lblSelectCustomer.setVisible(true);
	    }
		
		GroupLayout glPnlMainLeft = new GroupLayout(pnlMainLeft);
		glPnlMainLeft.setHorizontalGroup(
			glPnlMainLeft.createParallelGroup(Alignment.TRAILING)
				.addGroup(glPnlMainLeft.createSequentialGroup()
					.addContainerGap()
					.addGroup(glPnlMainLeft.createParallelGroup(Alignment.LEADING)
						.addGroup(glPnlMainLeft.createSequentialGroup()
							.addGroup(glPnlMainLeft.createParallelGroup(Alignment.TRAILING)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 361, GroupLayout.PREFERRED_SIZE)
								.addGroup(glPnlMainLeft.createSequentialGroup()
									.addGroup(glPnlMainLeft.createParallelGroup(Alignment.LEADING)
										.addGroup(glPnlMainLeft.createSequentialGroup()
											.addGap(6)
											.addComponent(lblItemSelected, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
											.addComponent(lblCategory, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE))
										.addGroup(glPnlMainLeft.createSequentialGroup()
											.addComponent(txtSelectedItem, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txtCategory, GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(glPnlMainLeft.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblQuantity)
										.addComponent(txtQuantity, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE))
									.addGroup(glPnlMainLeft.createParallelGroup(Alignment.LEADING)
										.addGroup(glPnlMainLeft.createSequentialGroup()
											.addGap(18)
											.addComponent(lblPrice, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
										.addGroup(glPnlMainLeft.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txtPrice, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE))))
								.addGroup(Alignment.LEADING, glPnlMainLeft.createSequentialGroup()
									.addGroup(glPnlMainLeft.createParallelGroup(Alignment.TRAILING)
										.addGroup(glPnlMainLeft.createSequentialGroup()
											.addComponent(lblItemId)
											.addPreferredGap(ComponentPlacement.RELATED, 214, Short.MAX_VALUE))
										.addGroup(glPnlMainLeft.createSequentialGroup()
											.addComponent(lblSelectCustomer)
											.addPreferredGap(ComponentPlacement.RELATED)))
									.addComponent(cbCustomerId, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
								.addGroup(glPnlMainLeft.createSequentialGroup()
									.addGroup(glPnlMainLeft.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblHeader)
										.addGroup(glPnlMainLeft.createSequentialGroup()
											.addComponent(btnCoffee, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
											.addGap(7)
											.addComponent(btnFood)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(glPnlMainLeft.createParallelGroup(Alignment.LEADING)
										.addComponent(btnOther, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblUserId, Alignment.TRAILING))))
							.addContainerGap())
						.addComponent(btnAddItem, Alignment.TRAILING)))
		);
		glPnlMainLeft.setVerticalGroup(
			glPnlMainLeft.createParallelGroup(Alignment.LEADING)
				.addGroup(glPnlMainLeft.createSequentialGroup()
					.addGroup(glPnlMainLeft.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUserId)
						.addComponent(lblHeader))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(glPnlMainLeft.createParallelGroup(Alignment.LEADING)
						.addGroup(glPnlMainLeft.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnOther, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnFood, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnCoffee, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(glPnlMainLeft.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblItemSelected)
						.addComponent(lblCategory)
						.addComponent(lblQuantity)
						.addComponent(lblPrice))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(glPnlMainLeft.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtSelectedItem, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtCategory, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtQuantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(glPnlMainLeft.createParallelGroup(Alignment.LEADING)
						.addComponent(lblItemId)
						.addGroup(glPnlMainLeft.createParallelGroup(Alignment.BASELINE)
							.addComponent(cbCustomerId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblSelectCustomer)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAddItem)
					.addContainerGap(28, Short.MAX_VALUE))
		);
		
		lstMenuItem = new JList();
		lstMenuItem.setBorder(new TitledBorder(null, "Click the menu item to select", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		lstMenuItem.setToolTipText("Click to select menu item from the list");
		scrollPane.setViewportView(lstMenuItem);
		pnlMainLeft.setLayout(glPnlMainLeft);
		
	}

	private void menuEventHendler() {
		menuItemLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				LoginGui loginGui = new LoginGui();
				loginGui.setVisible(true);
			}
		});
		
		menuItemExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Report.showOrderReportDialog();
//				int choice = JOptionPane.showConfirmDialog(
//			            null, 
//			            Report.getOrderReport(), 
//			            "Exit Confirmation", 
//			            JOptionPane.YES_NO_OPTION
//			        );
//
//			        if (choice == JOptionPane.YES_OPTION) {
//			            System.exit(0);
//			        }
			}
		});
		
		lstMenuItem.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				String selectedValue = lstMenuItem.getSelectedValue() == null ? "" : lstMenuItem.getSelectedValue().toString();
				String [] selectedArray = selectedValue.split(",");
				
				if(selectedArray.length == 3) {
					String categorySelected = selectedArray[0].trim().substring(0,3);
					String category = null;
					
					if("OTH".equals(categorySelected)) category = "Others";
					if("BEV".equals(categorySelected)) category = "Beverage";
					if("FOD".equals(categorySelected)) category = "Food";
					
					lblItemId.setText(selectedArray[0].trim());
					lblItemId.setVisible(false);
					txtSelectedItem.setText(selectedArray[1].trim());
					txtCategory.setText(category);
					txtPrice.setText(selectedArray[2].trim());
					Double.parseDouble(selectedArray[2].trim());
					txtQuantity.setText("1");
				}
				
			}
		});
		
		menuItemProcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String customerId = lblUserId.getText().trim();
				//Load customer Shopping items
				LinkedList<Order> orders = OrderLoader.loadOrdersFromCSV(FileType.ORDERS);
				
				if(orders == null || orders.size() < 1) {
					JOptionPane.showMessageDialog(pnlMainRight, "There are no orders to process!", "Order Confirmation", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
		        
		        //Always create the new table from the saved user orders
		        tableRows = new ArrayList<>();
		        for(Order order : orders) {
		        	tableRows.add(new Object[]{
		        			order.getOrderId(),
		        			DATE_FORMAT.format(order.getDateCreated()),
		        			order.getCustomerId(),
		        			order.getMenuItemList(),
		        			order.getSubTotal(),
		        			order.getStatus()});
		        }
				
		        String[] columnNames = {"Order ID", "Date", "Customer", "Menu Items", "Total Price", "Status"};
				tableModel = commonMethod.getTableModel(columnNames, tableRows, null);
				lblTableTitle.setText("Order Management");
		        
		        tblInformation.setModel(tableModel);
		        tblInformation.setVisible(true);
				
			}
		});
		
		menuItemOrdersReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		        


		        String customerId = lblUserId.getText().trim();
				//Load customer Shopping items
				LinkedList<Order> orders = OrderLoader.loadOrdersFromCSV(FileType.ORDER_ARCHIVE);
		        
		        //Always create the new table from the saved user orders
		        tableRows = new ArrayList<>();
		        for (Order order : orders) {
		            for (ShopMenuItem item : order.getMenuItemList()) {
		                tableRows.add(new Object[]{
		                    item.getName(),
		                    item.getCategoryId(),
		                    item.getQuantity(),
		                    item.getCurrentPrice(),
		                    item.getCurrentPrice() * item.getQuantity(),
		                    order.getOrderId(),
		                    DATE_FORMAT.format(order.getDateCreated())
		                });
		            }
		        }
				
		        String[] columnNames = {"Menu Item Name", "Category", "Quantiry", "Unit Price", "Total Price", "Order ID", "Order Date"};
				tableModel = commonMethod.getTableModel(columnNames, tableRows, "Report");
				lblTableTitle.setText("Order Report");
		        
		        tblInformation.setModel(tableModel);
		        tblInformation.setVisible(true);
			}
		});

	}
	
	private void buttonHandler() {
		btnCoffee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CommonMethod commonMethod = new CommonMethod();
				model = commonMethod.getModel("BEVERAGE");
				
//				String categoryId = "BEVERAGE";
//				List<MenuItem> menuItems = MenuItemLoader.loadMenuItemsFromCSV();
//
//			    List<MenuItem> foods = MenuItemLoader.getMenuItemsByCategoryId(menuItems, categoryId);
//				model = new DefaultListModel();
//				if(foods.isEmpty()) {
//					model.addElement("There are no items in Food category");
//				} else {
//					for (MenuItem item : foods) {
//					    model.addElement(item.displayMenuItem());
//					}
//				}
				
				lstMenuItem.setModel(model);
			}
		});
		
		btnFood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CommonMethod commonMethod = new CommonMethod();
				model = commonMethod.getModel("FOOD");
//				String categoryId = "FOOD";
//				List<MenuItem> menuItems = MenuItemLoader.loadMenuItemsFromCSV();
//
//			    List<MenuItem> foods = MenuItemLoader.getMenuItemsByCategoryId(menuItems, categoryId);
//				model = new DefaultListModel();
//				if(foods.isEmpty()) {
//					model.addElement("There are no items in Food category");
//				} else {
//					for (MenuItem item : foods) {
//					    model.addElement(item.displayMenuItem());
//					}
//				}
				
				lstMenuItem.setModel(model);
				lstMenuItem.clearSelection();
			}
		});
		
		btnOther.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				model = commonMethod.getModel("OTHERS");
				
//				String categoryId = "OTHERS";
//				List<MenuItem> menuItems = MenuItemLoader.loadMenuItemsFromCSV();
//
//			    List<MenuItem> others = MenuItemLoader.getMenuItemsByCategoryId(menuItems, categoryId);
//				
//				model = new DefaultListModel();
//				
//				if(others.isEmpty()) {
//					model.addElement("There are no items in others category");
//				} else {
//					for (MenuItem item : others) {
//					    model.addElement(item.displayMenuItem());
//					}
//				}
				
				lstMenuItem.setModel(model);
				lstMenuItem.clearSelection();
			}
		});
		
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        String name = txtSelectedItem.getText().trim();
		        String category = txtCategory.getText().trim();
		        String customerId = lblUserId.getText().trim();
		        String selectedCustomer = (String) cbCustomerId.getSelectedItem();
		        
		        if(selectedCustomer == null && customerId == null) {
		        	JOptionPane.showMessageDialog(null, "Please Select the customer.", "Input Error", JOptionPane.ERROR_MESSAGE);
		        }
		        
		        if(selectedCustomer != null) {
		        	customerId = selectedCustomer.split(" - ")[0].trim();;
		        }
	        
		        int quantity;
		        double price;

		        try {
		            quantity = Integer.parseInt(txtQuantity.getText().trim());
		            price = Double.parseDouble(txtPrice.getText().trim());
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, "Please enter valid numbers for Quantity and Price.", "Input Error", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
		        
		        //Add the new item
		        ShoppingCart newItem = new ShoppingCart(
		        		"SC001",
		        		lblItemId.getText(),
		        		name,
		        		category,
		        		quantity,
		        		price,
		        		new Date(),
		        		customerId);
		        ShoppingCartManager.addItemToCart(newItem);
		        
		      //Load customer Shopping items
		        List<ShoppingCart> cartItems = ShoppingCartManager.getCartItemsByCustomerId(customerId);
		        
		        //Always create the new table from the saved user orders
		        tableRows = new ArrayList<>();
		        for(ShoppingCart cartItem : cartItems) {
		        	tableRows.add(new Object[]{
		        			cartItem.getItemName(),
		        			cartItem.getCategory(),
		        			cartItem.getQuantity(),
		        			cartItem.getUnitPrice(),
		        			cartItem.getQuantity()*cartItem.getUnitPrice(),
		        			"Remove"});
		        }
		        
		        String[] columnNames = {"Name", "Category", "Quantity", "Price", "Total Price", "Action"};
				tableModel = commonMethod.getTableModel(columnNames, tableRows, null);
				lblTableTitle.setText("Shopping Items");
		        
		        tblInformation.setModel(tableModel);
		        tblInformation.setVisible(true);

		        txtSelectedItem.setText("");
		        txtCategory.setText("");
		        txtQuantity.setText("");
		        txtPrice.setText("");
			}
		});
		
		btnReloadCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String customerId = lblUserId.getText().trim();
		        List<ShoppingCart> cartItems = ShoppingCartManager.getCartItemsByCustomerId(customerId);
		        
		        if(cartItems.isEmpty() || cartItems.size()<1) {
		        	JOptionPane.showMessageDialog(null, "There are no items to be loaded.", "Input Error", JOptionPane.ERROR_MESSAGE);
		        	tblInformation.setVisible(false);
		            return;
		        }
		        
		        //Always create the new table from the saved user orders
		        tableRows = new ArrayList<>();
		        for(ShoppingCart cartItem : cartItems) {
		        	tableRows.add(new Object[]{
		        			cartItem.getItemName(),
		        			cartItem.getCategory(),
		        			cartItem.getQuantity(),
		        			cartItem.getUnitPrice(),
		        			cartItem.getQuantity()*cartItem.getUnitPrice(),
		        			"Remove"});
		        }
		        
		        if(tableRows.isEmpty()) {
		        	tableModel = null;
		        } else {
		        	String[] columnNames = {"Name", "Category", "Quantity", "Price", "Total Price", "Action"};
					tableModel = commonMethod.getTableModel(columnNames, tableRows, null);
					lblTableTitle.setText("Shopping Items");
			        
		        }
		     
		        tblInformation.setModel(tableModel);
		        tblInformation.setVisible(true);

		        txtSelectedItem.setText("");
		        txtCategory.setText("");
		        txtQuantity.setText("");
		        txtPrice.setText("");
			}
		});
		
		btnCheckout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String customerId = lblUserId.getText().trim();

				User user = UserLoader.getUserById(customerId);
				
				List<ShoppingCart> cartItems = new ArrayList<>();
				if(Constant.CUSTOMER_ROLE_ID.equals(user.getRoleId())) {
					cartItems = ShoppingCartManager.getCartItemsByCustomerId(customerId);
				} else {
					cartItems = ShoppingCartManager.loadCartItemsFromCSV();
				}
				
		        
		        if(cartItems.isEmpty() || cartItems.size()<1) {
		        	JOptionPane.showMessageDialog(null, "There are no items to checkout.", "Input Error", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
		        
		        List<ShopMenuItem> orderMenuItem = new ArrayList<>();
		        StringBuilder bill = new StringBuilder("Your Bill\n");
		        bill.append("----------------------------\n");
		        for (ShoppingCart cartItem : cartItems) {
		            double totalPrice = cartItem.getQuantity() * cartItem.getUnitPrice();
		            
		            //Menu items for the order creation
		            orderMenuItem.add(new ShopMenuItem(cartItem.getItemId(), cartItem.getItemName(), cartItem.getCategory(), "Description", cartItem.getQuantity(), cartItem.getUnitPrice()));
		            
		            bill.append(cartItem.getItemName() + ", " +
		                    cartItem.getCategory() + ", " +
		                    cartItem.getQuantity() + ", " +
		                    "$" + String.format("%.2f", cartItem.getUnitPrice()) + ", " +
		                    "$" + String.format("%.2f", totalPrice) + "\n");
		        }

		        bill.append("----------------------------\n");
		        
		        
		        //Calculate the discount, tax and total
		        
		        BillManager billManager = new BillManager();
		        BillResponse billResponse = billManager.getCustomerBill(cartItems);
		        

		        bill.append(String.format("Total Bill : $%.2f\n", billResponse.getTotalAmount()));
		        bill.append(String.format("Discount : $%.2f\n", billResponse.getDiscount()));
		        bill.append(String.format("Tax : $%.2f\n", billResponse.getTaxAmount()));
		        bill.append(String.format("Payable Amount: $%.2f\n", billResponse.getPayableAmount()));
		        bill.append("----------------------------\n");
		        bill.append("Thank you for shopping!\n");

            // Show confirmation dialog
            JOptionPane.showMessageDialog(pnlMainRight, bill, "Bill Confirmation", JOptionPane.INFORMATION_MESSAGE);
            
            //Create an order and remove the shopping cart
            String orderId = IdGenerator.generateIdByItem("ORDER");
            Date currentDate = new Date();
            Order newOrder = new Order(orderId, currentDate , customerId, orderMenuItem, billResponse.getPayableAmount(), OrderStatus.PENDING);
            boolean addOrder = OrderLoader.addOrder(newOrder);
            if(addOrder) {
            	ShoppingCartManager.clearCart(customerId);
            	JOptionPane.showMessageDialog(pnlMainRight, "Success Placed an order, Thank you for shopping with us :)", "Order Confirmation", JOptionPane.INFORMATION_MESSAGE);
            	tblInformation.setVisible(false);
            }
           
				
			}
		});
		
		btnRemoveCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String customerId = lblUserId.getText().trim();
				//Remove all customer Shopping itemss
				int response = JOptionPane.showConfirmDialog(pnlMainRight, "Are you sure you want to remove all shopping items", "Bill Cancellation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (response == JOptionPane.YES_OPTION) {
				    ShoppingCartManager.clearCart(customerId);
				    tblInformation.setVisible(false);
				} else {
					return;
				}
			}
		});
		
		btnProcessOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Get User by role
				String customerId = lblUserId.getText().trim();
				User user = UserLoader.getUserById(customerId);
				
				if(Constant.CUSTOMER_ROLE_ID.equals(user.getRoleId())) {
					JOptionPane.showMessageDialog(pnlMainRight, "Customer cannot process the Orders!", "Informatiom Message", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				try {
					LinkedList<Order> orders = OrderLoader.loadOrdersFromCSV(FileType.ORDERS);
					
					//Get the first item without removing from list
					Order order = orders.peekFirst();
					StringBuilder bill = new StringBuilder("");
					if(order != null) {
						bill = new StringBuilder("Your Bill\n");
						bill.append("----------------------------\n");
						bill.append(String.format(
								
								"Order ID: %s \nDate Created: %s\nCustomer ID: %s\nTotal Amount: %s\nOrder Status: %s\n\nMenu Items: \n%s\n", 
								order.getOrderId(),
								DATE_FORMAT.format(order.getDateCreated()),
								order.getCustomerId(),
								order.getSubTotal(),
								order.getStatus(),
								ShopMenuItem.formatDisplayMenuItems(order.getMenuItemList())
								
								));

						bill.append("----------------------------\n");

						bill.append("Process Complete Bill!\n");
					} else {
						JOptionPane.showMessageDialog(pnlMainRight, "There is no pending bill to process!", "Order Confirmation", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					
					
					int response = JOptionPane.showConfirmDialog(pnlMainRight, bill, "Order Processing", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (response == JOptionPane.YES_OPTION) {
						
						//Asuming Single customer is processing the orders
						Order orderRemoved = orders.pollFirst();
						
						if(orderRemoved != null && orderRemoved.getOrderId() != null) {
							//ToDo Remove order from the file
							OrderLoader.completeOrder(orderRemoved.getOrderId());
							JOptionPane.showMessageDialog(pnlMainRight, "Successfully removed order with ID: " + orderRemoved.getOrderId(), "Bill Confirmation", JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(pnlMainRight, "Failed to Process the order", "Order Confirmation", JOptionPane.ERROR_MESSAGE);
						}
					    tblInformation.setVisible(false);
					} else {
						return;
					}
				} catch (IllegalFormatFlagsException ex) {
					System.out.println("Failed to format the string notation due to: " + ex.getMessage());
				}
			}
		});
	}
}