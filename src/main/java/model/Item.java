package model;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Item {
	private String name;
	private String category;
	private String price;
	
	public Item(ResultSet rs) throws SQLException{
		name = rs.getString("name");
		category = rs.getString("category");
		price = rs.getString("price");	
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	
}
