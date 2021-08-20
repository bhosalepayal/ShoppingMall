package com.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.app.dbutil.MySqlDbConnection;
import com.app.exception.BusinessException;

public class EmployeeDaoClass {

	public static boolean addProduct(String name, int price) throws BusinessException {
		try(Connection connection=MySqlDbConnection.getConnection()){
			String sql = "insert into product (p_name,p_price) values(?,?)";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, name);
			pst.setInt(2, price);
			int resultSet = pst.executeUpdate();
			if(resultSet>0) {
				 return true;
			 }
		}catch(ClassNotFoundException | SQLException e){
			System.out.println(e);
			throw new BusinessException("Internal error occured,please contact support");
		}
		return false;
	}
	
}
