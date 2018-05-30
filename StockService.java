package com.java.service;

import com.java.pojo.Stock;

public interface StockService {

	public void addStock(Stock stock);

	public void editStock(Stock stock);

	public void deleteStock(int id);

}

