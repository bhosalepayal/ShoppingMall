package com.app;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.app.dao.impl.CustomerDaoClass;
import com.app.dao.impl.EmployeeDaoClass;
import com.app.exception.BusinessException;
import com.app.model.Product;

public class Shopping {
	private static Logger log = Logger.getLogger(Shopping.class);
	private static String empUname="admin";
	private static String empPass="123";
	public static void main(String[] args) throws BusinessException {
		Scanner scanner = new Scanner(System.in);
		log.info("Welcome to online shopping Mall");
		log.info("===============================");
		log.info("Main Menu");
		
		int ch=0;
		do {
			log.info("1)  Login As Customer");
			log.info("2)  Login As Employee");
			log.info("3)  Register Customer");
			log.info("4)  EXIT");
			log.info("Please enter your choice(1-4)");
			
			try {
				ch = scanner.nextInt();
			}
			catch(NumberFormatException e) {}
			
			switch(ch) {
			case 1:
				log.info("Welcome to Customer Login");
				log.info("Enter UserName");
				String username=scanner.next();
				log.info("Enter Password");
				String pwd=scanner.next();
				if(CustomerDaoClass.login(username, pwd)) {
					log.info("login sucessfully");
					log.info("==================================");
					log.info("Welcome, What you wann do today? ");
					log.info("==================================");
					int value=0;
					do {
						log.info("1)  View Product List");
						log.info("2)  Add Product to Cart");
						log.info("3)  View Cart List");
						log.info("4)  Placed Order");
						log.info("5)  Mark Order Received");
						log.info("6)  Logout");
						log.info("Please enter your choice(1-6)");
						
						try {
							value = scanner.nextInt();
						}
						catch(NumberFormatException e) {}
						
						switch(value) {
						case 1:
							List<Product> productList = CustomerDaoClass.getAllProduct();
							for(Product p:productList) {
								
								log.info("ID-"+p.getP_id()+" "+p.getP_name()+" Price-"+p.getP_price());	
								log.info("");
							}
								
							break;
							
						case 2:
							log.info("Enter Product Id");
							int pid = scanner.nextInt();
							if(CustomerDaoClass.addCart(pid, username))
								log.info("Successfully Added to Cart!!!");
							else
								log.info("Error while adding");
							break;
						
						case 3:
							List<Product> cartList = CustomerDaoClass.getCart(username);
							if(cartList.isEmpty())
								log.info("You have an empty cart!!!\n");
							int total = 0;
							for(Product p:cartList) {
								total = total+p.getP_price();
								log.info("ID-"+p.getP_id()+" "+p.getP_name()+" Price-"+p.getP_price());	
							}
							log.info("Total="+total+"\n");
							
							break;
							
						case 4:
							log.info("Under Construction");
							break;
							
						case 5:
							log.info("Under Constrution");
							break;
						case 6:
							log.info("Logout Sucessfully!!!");
							break;
							
						}
						
					}while(value!=6);
					
				}else {
					log.info("Incorrect Username and Password");
				}
				break;
				
			case 2:
				log.info("Welcome to Employee Login");
				log.info("Enter UserName");
				String empUser=scanner.next();
				if(empUser.equals(empUname)) {
					log.info("Enter Password");
					String pass=scanner.next();
					if(pass.equals(empPass)) {
						log.info("login sucessfully");
						log.info("==================================");
						log.info("WELCOME ");
						log.info("==================================");
						int empCh=0;
						do {
							log.info("1)  Add new product");
							log.info("2)  Search product");
							log.info("3)  Update product status");
							log.info("4)  Logout");
							log.info("Select your choice 1-4");
							try {
								empCh=scanner.nextInt();
							}catch(NumberFormatException e) {}
							
							switch(empCh) {
							case 1:
								log.info("Enter product name: ");
								String name = scanner.next();
								log.info("Enter product price");
								int price = scanner.nextInt();
								if(EmployeeDaoClass.addProduct(name, price)) {
									log.info("Product sucessfully added");
								}else log.info("Error while adding product");
								break;
								
							case 2:
								
								break;
								
							case 3:
								break;
							case 4:
								log.info("Logout Sucessfully!!!");
								break;
							}
							
						}while(empCh!=4);
					}else log.info("INcorrect pasword");
						
				}else log.info("Username is incorrect");
				break;
				
			case 3:
				log.info("Enter the FirstName");
				String f_name = scanner.next();
				log.info("Enter the LastName");
				String l_name = scanner.next();
				log.info("Enter the Email");
				String email1 = scanner.next();
				log.info("Enter the Password");
				String pass1= scanner.next();
				if(CustomerDaoClass.addCustomer(f_name, l_name, email1, pass1)) {
					log.info("Sucessfully Register");
				}else {
					log.info("Error Occured");
				}
				
				
				break;
			case 4:
				log.info("EXIT Sucessfully!!!!");
				break;
			}
		}while(ch!=4);
	}				

}
