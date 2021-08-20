package com.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.dbutil.MySqlDbConnection;
import com.app.exception.BusinessException;
import com.app.model.Product; 
public class CustomerDaoClass {

	public static boolean login(String email, String password) throws BusinessException {
		try(Connection connection=MySqlDbConnection.getConnection()){
			String sql = "select email, password from customer where email = ? and password = ?";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, email);
			pst.setString(2, password);
			ResultSet resultSet = pst.executeQuery();
			if(resultSet.next()) {
				 return true;
			 }
		}catch(ClassNotFoundException | SQLException e){
			System.out.println(e);
			throw new BusinessException("Internal error occured,please contact support");
		}
		
		return false;
	}
	
	public static List<Product> getAllProduct() throws BusinessException{
		List<Product> list = new ArrayList<>();
		try(Connection connection=MySqlDbConnection.getConnection()){
			String sql = "select * from product";
			PreparedStatement pst = connection.prepareStatement(sql);
			ResultSet resultSet = pst.executeQuery();
			while(resultSet.next()) {
				 Product p = new Product();
				 p.setP_id(resultSet.getInt("p_id"));
				 p.setP_name(resultSet.getString("p_name"));
				 p.setP_price(resultSet.getInt("p_price"));
				 list.add(p);
			 }
		}catch(ClassNotFoundException | SQLException e){
			System.out.println(e);
			throw new BusinessException("Internal error occured,please contact support");
		}
		
		return list;
	}
	
	public static boolean addCart(int p_id, String email) throws BusinessException{
		try(Connection connection=MySqlDbConnection.getConnection()){
			String sql = "insert into cart(p_id,email) values (?,?)";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1, p_id);
			pst.setString(2, email);
			int resultSet = pst.executeUpdate();
			if(resultSet > 0) {
				return true;
			 }
		}catch(ClassNotFoundException | SQLException e){
			System.out.println(e);
			throw new BusinessException("Internal error occured,please contact support");
		}
		return false;
	}
	
	public static List<Product> getCart(String email) throws BusinessException{
		List<Product> cart_list = new ArrayList<>();
		try(Connection connection=MySqlDbConnection.getConnection()){
			String sql = "select * from product where p_id in(select p_id from cart where email = ?)";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, email);
			ResultSet resultSet = pst.executeQuery();
			while(resultSet.next()) {
				 Product p_cart = new Product();
				 p_cart.setP_id(resultSet.getInt("p_id"));
				 p_cart.setP_name(resultSet.getString("p_name"));
				 p_cart.setP_price(resultSet.getInt("p_price"));
				 cart_list.add(p_cart);
			 }
		}catch(ClassNotFoundException | SQLException e){
			System.out.println(e);
			throw new BusinessException("Internal error occured,please contact support");
		}
		
		return cart_list;
	}
	
	
	
	
}
