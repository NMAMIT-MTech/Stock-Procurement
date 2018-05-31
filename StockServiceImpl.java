String user = "root";
	String password = "";
	Connection conn;
	PreparedStatement statement;
	PreparedStatement rs;

	@Override
	public void addStock(Stock stock) {
		// adding the items to the database
		try {

			String sql = "INSERT INTO stocks (Item_Name,Item_Quantity,Purchase_by,Purchase_Date,purchase_from,Volume,Item_ID) values (?, ?, ?,?,?,?,?)";
			conn = DriverManager.getConnection(url, user, password);
			statement = conn.prepareStatement(sql);
			statement.setString(1, stock.getItemName());
			statement.setInt(2, stock.getItemQuantity());
			statement.setString(3, stock.getPurchasedBy());
			java.sql.Date sqlDate = new java.sql.Date(stock.getPurchaseDate().getTime());
			statement.setDate(4, sqlDate);
			statement.setString(5, stock.getPurchasedFrom());
			statement.setString(6, stock.getVolume());
			statement.setInt(7, stock.getItemId());

			int row = statement.executeUpdate();
			if (row > 0) {
				System.out.println("Stock added Successfully");
			}
			conn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public void editStock(Stock stock) {
		try {
			StockServiceImpl stockImpl = new StockServiceImpl();
			stockImpl.display(stock);
			Scanner sc = new Scanner(System.in);
			System.out.println("\n Enter your item to be deleted: \n");
			int id1 = 0;
			id1 = sc.nextInt();
			conn = DriverManager.getConnection(url, user, password);

			String sql = "UPDATE stocks SET Item_Name=?,itemQuantity=?,purchaseDate=?,purhasedBy=?,purchasedFrom=?,Volume=?,Item_ID=? WHERE stockId = ?";
			statement = conn.prepareStatement(sql);

			statement.setString(1, stock.getItemName());
			statement.setInt(2, stock.getItemQuantity());
			java.sql.Date sqlDate = new java.sql.Date(stock.getPurchaseDate().getTime());
			statement.setDate(3, sqlDate);
			statement.setString(4, stock.getPurchasedBy());
			statement.setString(5, stock.getPurchasedFrom());
			statement.setString(6, stock.getVolume());
			statement.setInt(7, stock.getItemId());
			stock.setId(id1);
			statement.setInt(8, stock.getId());

			statement.executeUpdate();
			statement.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void deleteStock() {
		// deleting the items from the database
		StockServiceImpl stockImpl = new StockServiceImpl();
		Stock stock = new Stock();
		Scanner sc = new Scanner(System.in);

		stockImpl.clearDetails();
		stockImpl.display(stock);

		System.out.println("\n Enter your item to be deleted: \n");

		int id1 = 0;
		id1 = sc.nextInt();

		try {
			// create our java prepared statement using a sql update query
			String sql = "Delete from stocks WHERE stockid = ?";

			conn = DriverManager.getConnection(url, user, password);

			statement = conn.prepareStatement(sql);
			statement.setInt(1, id1);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void clearDetails() {
		// clearing the stcok details
		Stock stock = new Stock();
		stock.setItemName(null);
		stock.setItemQuantity(0);
		stock.setPurchaseDate(null);
		stock.setPurchasedBy(null);
		stock.setVolume(null);
	}

	public void display(Stock stock) {
		// display the items
		Stock stock1 = new Stock();
		String query = "SELECT * FROM stocks";

		Statement st;
		try {
			conn = DriverManager.getConnection(url, user, password);
			st = conn.createStatement();

			ResultSet rs = st.executeQuery(query);
			System.out.format(
					"StockId       ItemName           ItemQuantity          ItemPurchasedBy       volume\n");

			while (rs.next()) {

				int StockId = rs.getInt("StockId");
				stock1.setId(StockId);

				String ItemName = rs.getString("Item_Name");
				stock1.setItemName(ItemName);

				int ItemQuantity = rs.getInt("Item_Quantity");
				stock1.setItemQuantity(ItemQuantity);
				String ItemPurchasedBy = rs.getString("Purchase_by");
				stock1.setPurchasedBy(ItemPurchasedBy);

				String volume = rs.getString("Volume");
				stock1.setVolume(volume);

				System.out.format("%d%20s%20d %20s%20s\n", StockId, ItemName, ItemQuantity, ItemPurchasedBy, volume);
			}
			st.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		//System.exit(0);
	}

	protected JComboBox<String> referenceData() throws Exception {

		JComboBox<String> bookList = new JComboBox<String>();
		// add more books
		bookList.addItem("Book");
		bookList.addItem("Milk");
		bookList.addItem("Sugar");
		bookList.addItem("Coffe Powder");
		return bookList;
	}

	public void input(Stock stock) {
		Scanner sc = new Scanner(System.in);
		stock.setItemId(0);
		System.out.println("1. Milk");
		System.out.println("2. Sugar");
		System.out.println("3. Coffee powder");
		System.out.println("4. Tea powder");
		System.out.println("5. Water");
		System.out.println("6. Tea bags");
		System.out.println("7. Sugar cubes");
		System.out.println("8. Coffe cups");
		System.out.println("9. Cookies");
		System.out.println("10. Toffees");
		System.out.println("\n Enter the Item Id :");
		int itemId = sc.nextInt();
		stock.setItemId(itemId);

		if (itemId == 1) {
			stock.setItemName("Milk");
		}
		if (itemId == 2) {
			stock.setItemName("Sugar");
		}
		if (itemId == 3) {
			stock.setItemName("Coffee powder");
		}
		if (itemId == 4) {
			stock.setItemName("Tea powder");
		}
		if (itemId == 5) {
			stock.setItemName("Water");
		}
		if (itemId == 6) {
			stock.setItemName("Tea bags");
		}
		if (itemId == 7) {
			stock.setItemName("Sugar cubes");
		}
		if (itemId == 8) {
			stock.setItemName("Tea bags");
		}
		if (itemId == 9) {
			stock.setItemName("Cookies");
		}
		if (itemId == 10) {
			stock.setItemName("Toffees");
		}

		System.out.println("Enter date: \n");
		String datestring = sc.next();
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date purchaseDate;
		try {
			purchaseDate = formatter.parse(datestring);
			stock.setPurchaseDate(purchaseDate);
		} catch (ParseException e) {
			System.out.println("Errro importing date: \n");
			e.printStackTrace();
		}

		System.out.println("Enter purchased by: \n");
		String purchasedBy = sc.next();
		stock.setPurchasedBy(purchasedBy);

		System.out.println("Enter volume: \n");
		String Volume = sc.next();
		stock.setVolume(Volume);

		System.out.println("Enter purchased from: \n");
		String purchasedFrom = sc.next();
		stock.setPurchasedFrom(purchasedFrom);

		System.out.println("Enter item quantity: \n");
		int itemQuantity = sc.nextInt();
		stock.setItemQuantity(itemQuantity);

	}

	public static void main(String[] args) {

		int choice;
		int loop1 = 1;

		Scanner sc = new Scanner(System.in);
		do {
		System.out.println("Presss 1 to enter Item Detail");
		System.out.println("Presss 2 to delete the Item Detail");
		System.out.println("Presss 3 to diaply the Item Detail");
		System.out.println("Presss 4 to exit: \n");

		System.out.println("Enter your choice: \n");
		choice = sc.nextInt();

		StockServiceImpl stockImpl = new StockServiceImpl();
		Stock stock = new Stock();
		
		

			switch (choice) {
			case 1:
				stockImpl.input(stock);
				stockImpl.addStock(stock);
				stockImpl.display(stock);
				break;
			case 2:
				stockImpl.deleteStock();
				break;
			case 3:
				stockImpl.display(stock);
				System.out.println("\n Do you wish to continue or exit?: \n Press 0.Yes\n 4.Exit \n");
				int yes=0;
				yes = sc.nextInt();
				if(yes==4)
				{
					loop1=0;
				}	
				else
					loop1=1;
				
				break;
			case 4:
				System.out.println("Have a nice day :)");
				System.exit(0);

			default:
				System.out.println("Please enter valid choice: \n");
				break;
			}

		} while (loop1 != 0);
	}
}

