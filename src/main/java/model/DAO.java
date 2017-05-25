package model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;


public class DAO {
	private Connection conn;
	
	public DAO() {
		String URL = "jdbc:hsqldb:hsql://192.168.56.1/workdb";
		try {
			this.conn = DriverManager.getConnection(URL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getItems(String sort){
		PreparedStatement query;
		ResultSet rs;
		ArrayList<Item> items = new ArrayList<Item>();
		try {
			if(!sort.equals("price") && !sort.equals("category")) sort = "name";
			query = this.conn.prepareStatement("SELECT * FROM items ORDER BY " + sort + ";");
			rs = query.executeQuery();
			while(rs.next()){
				items.add(new Item(rs));
			}
			return new Gson().toJson(items);

		} catch (SQLException e) {
			System.out.println("me no can do get query!");
			e.printStackTrace();
		}
		return null;
	}
	
	public String getItemsByCategory(String category){
		PreparedStatement query;
		ResultSet rs;
		ArrayList<Item> items = new ArrayList<Item>();
		try {
			query = this.conn.prepareStatement("SELECT * FROM items WHERE category='" + category + "';");
			rs = query.executeQuery();
			while(rs.next()){
				items.add(new Item(rs));
			}
			return new Gson().toJson(items);
		} catch (SQLException e) {
			System.out.println("me no can do get query!");
			e.printStackTrace();
		}
		return null;
	}
	
	public String getItemsByName(String name){
		PreparedStatement query;
		ResultSet rs;
		ArrayList<Item> items = new ArrayList<Item>();
		try {
			query = this.conn.prepareStatement("SELECT * FROM items WHERE name='" + name + "';");
			rs = query.executeQuery();
			while(rs.next()){
				items.add(new Item(rs));
			}
			return new Gson().toJson(items);
		} catch (SQLException e) {
			System.out.println("me no can do get query!");
			e.printStackTrace();
		}
		return null;
	}
	
	public String getItemsByPrice(String fromprice, String toprice){
		PreparedStatement query;
		ResultSet rs;
		ArrayList<Item> items = new ArrayList<Item>();
		try {
			query = this.conn.prepareStatement("SELECT * FROM items WHERE price>=" + fromprice + "AND price<=" + toprice + ";");
			rs = query.executeQuery();
			while(rs.next()){
				items.add(new Item(rs));
			}
			return new Gson().toJson(items);
		} catch (SQLException e) {
			System.out.println("me no can do get query!");
			e.printStackTrace();
		}
		return null;
	}
	
	public void addItem(HttpServletRequest request){
		Item item = new Gson().fromJson(request.getParameter("json"), Item.class);
		System.out.println(item.getName());
		System.out.println(item.getCategory());
		System.out.println(item.getPrice());
		PreparedStatement query;
		try {
			query = this.conn.prepareStatement("INSERT INTO items (name, price, category) VALUES ('" + item.getName() + "', " + item.getPrice() + ", '" + item.getCategory() + "');");
			System.out.println(query);
			query.execute();
		} catch (SQLException e) {
			System.out.println("me no can do add query!");
			e.printStackTrace();
		}
	}
	
	public String getItem(int id){
		PreparedStatement query;
		ResultSet rs;
		try {
			query = this.conn.prepareStatement("SELECT * FROM items WHERE id=" + id + ";");
			rs = query.executeQuery();
			rs.next();
			Item item = new Item(rs);
			return new Gson().toJson(item);

		} catch (SQLException e) {
			System.out.println("me no can do get query!");
			e.printStackTrace();
		}
		return null;
	}
	
	public void updateCategory(int id, String category){
		PreparedStatement query;
		try {
			query = this.conn.prepareStatement("UPDATE items SET category='" + category + "' WHERE id=" + id + ";");
			System.out.println(query);
			query.executeQuery();
		} catch (SQLException e) {
			System.out.println("me no can do get query!");
			e.printStackTrace();
		}
	}
	
	public void updateName(int id, String name){
		PreparedStatement query;
		try {
			query = this.conn.prepareStatement("UPDATE items SET name='" + name + "' WHERE id=" + id + ";");
			System.out.println(query);
			query.executeQuery();
		} catch (SQLException e) {
			System.out.println("me no can do get query!");
			e.printStackTrace();
		}
	}
	
	public void updatePrice(int id, String price){
		PreparedStatement query;
		try {
			query = this.conn.prepareStatement("UPDATE items SET price='" + price + "' WHERE id=" + id + ";");
			System.out.println(query);
			query.executeQuery();
		} catch (SQLException e) {
			System.out.println("me no can do get query!");
			e.printStackTrace();
		}
	}
}
