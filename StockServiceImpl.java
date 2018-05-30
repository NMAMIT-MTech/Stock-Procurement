package com.java.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JComboBox;

import com.java.pojo.Item;
import com.java.pojo.Stock;
import com.java.service.StockService;

public class StockServiceImpl implements StockService {

	// TODO Auto-generated method stub
	String url = "jdbc:mysql://localhost:3306/stocks";
	String user = "root";
	String password = "secret";
	Connection conn;
	PreparedStatement statement;

	@Override
	public void addStock(Stock stock) {

		try {

			String sql = "INSERT INTO stocks (Item_Name,itemQuantity,purchaseDate,purhasedBy,purchasedFrom,Volume) values (?, ?, ?,?,?)";
			conn = DriverManager.getConnection(url, user, password);
			statement = conn.prepareStatement(sql);
			statement.setString(1, stock.getItemName());
			statement.setInt(2, stock.getItemQuantity());
			java.sql.Date sqlDate = new java.sql.Date(stock.getPurchaseDate().getTime());
			statement.setDate(3, sqlDate);
			statement.setString(4, stock.getPurchasedBy());
			statement.setString(5, stock.getPurchasedFrom());
			statement.setString(6, stock.getVolume());

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
			// create our java preparedstatement using a sql update query
			String sql = "UPDATE stocks SET Item_Name=?,itemQuantity=?,purchaseDate=?,purhasedBy=?,purchasedFrom=?,Volume=? WHERE id = ?";
			statement = conn.prepareStatement(sql);

			statement.setString(1, stock.getItemName());
			statement.setInt(2, stock.getItemQuantity());
			java.sql.Date sqlDate = new java.sql.Date(stock.getPurchaseDate().getTime());
			statement.setDate(3, sqlDate);
			statement.setString(4, stock.getPurchasedBy());
			statement.setString(5, stock.getPurchasedFrom());
			statement.setString(6, stock.getVolume());
			statement.setInt(7, stock.getId());

			statement.executeUpdate();
			statement.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void deleteStock(int id) {

		try {
			// create our java prepared statement using a sql update query
			String sql = "Delete from stocks WHERE id = ?";
			statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// TODO Auto-generated method stub

	}

	public void clearDetails() {
		Stock stock = new Stock();
		stock.setItemName(null);
		stock.setItemQuantity(0);
		stock.setPurchaseDate(null);
		stock.setPurchasedBy(null);
		stock.setVolume(null);
	}

	public void display(Stock stock) {
		System.out.println("\n Item name: \n" + stock.getItemName());
		System.out.println("Purchased by: \n" + stock.getPurchasedBy());
		System.out.println("purchased from: \n" + stock.getPurchasedFrom());
		System.out.println("volume: \n" + stock.getVolume());
		System.out.println("\n Date: \n" + stock.getPurchaseDate());

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
		System.out.println("\n Enter the item name: \n");

		String itemName = sc.nextLine();
		stock.setItemName(itemName);

		System.out.println("Enter purchased by: \n");
		String purchasedBy = sc.nextLine();
		stock.setPurchasedBy(purchasedBy);

		System.out.println("Enter purchased from: \n");
		String purchasedFrom = sc.nextLine();
		stock.setPurchasedFrom(purchasedFrom);

		System.out.println("Enter volume: \n");
		String Volume = sc.nextLine();
		stock.setVolume(Volume);

		System.out.println("Enter item quantity: \n");
		int itemQuantity = sc.nextInt();
		stock.setItemQuantity(itemQuantity);

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
	}

	public static void main(String[] args) {
		StockServiceImpl stockImpl = new StockServiceImpl();
		Stock stock = new Stock();
		stockImpl.input(stock);
		stockImpl.addStock(stock);
		stockImpl.display(stock);
		stockImpl.clearDetails();

	}

}

