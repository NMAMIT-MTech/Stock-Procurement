package com.java.pojo;

import java.util.Date;

public class Stock {
	private int id;
	private String itemName;
	private int itemQuantity;
	private Date purchaseDate;
	private String purchasedBy;
	private String purchasedFrom;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getPurchasedBy() {
		return purchasedBy;
	}

	public void setPurchasedBy(String purchasedBy) {
		this.purchasedBy = purchasedBy;
	}

	public String getPurchasedFrom() {
		return purchasedFrom;
	}

	public void setPurchasedFrom(String purchasedFrom) {
		this.purchasedFrom = purchasedFrom;
	}

	@Override
	public String toString() {
		return "Stock [id=" + id + ", itemName=" + itemName + ", itemQuantity=" + itemQuantity + ", purchaseDate="
				+ purchaseDate + ", purchasedBy=" + purchasedBy + ", purchasedFrom=" + purchasedFrom + "]";
	}

}
