package com.java.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.java.pojo.Stock;
import com.java.service.StockService;

public class StockServiceImpl implements StockService {

	// TODO Auto-generated method stub
	String url = "jdbc:mysql://localhost:3306/contactdb";
	String user = "root";
	String password = "secret";
	Connection conn;
	PreparedStatement statement;

	@Override
	public void addStock(Stock stock) {

		try {

			String sql = "INSERT INTO stock (itemName,itemQuantity,purchaseDate,purhasedBy,purchasedFrom) values (?, ?, ?,?,?)";
			conn = DriverManager.getConnection(url, user, password);
			statement = conn.prepareStatement(sql);
			statement.setString(1, stock.getItemName());
			statement.setInt(2, stock.getItemQuantity());
			java.sql.Date sqlDate = new java.sql.Date(stock.getPurchaseDate().getTime());
			statement.setDate(3, sqlDate);
			statement.setString(4, stock.getPurchasedBy());
			statement.setString(5, stock.getPurchasedFrom());

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
			String sql = "UPDATE stock SET itemName=?,itemQuantity=?,purchaseDate=?,purhasedBy=?,purchasedFrom=? WHERE id = ?";
			statement = conn.prepareStatement(sql);

			statement.setString(1, stock.getItemName());
			statement.setInt(2, stock.getItemQuantity());
			java.sql.Date sqlDate = new java.sql.Date(stock.getPurchaseDate().getTime());
			statement.setDate(3, sqlDate);
			statement.setString(4, stock.getPurchasedBy());
			statement.setString(5, stock.getPurchasedFrom());
			statement.setInt(6, stock.getId());
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
			// create our java preparedstatement using a sql update query
			String sql = "Delete from stock WHERE id = ?";
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

}
