package com.app;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.app.dao.DaoClass;
import com.app.exception.BusinessException;
import com.app.model.Product;

public class Shopping {
	private static Logger log = Logger.getLogger(Shopping.class);
	
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
				ch = Integer.parseInt(scanner.nextLine());
			}
			catch(NumberFormatException e) {}
			
			switch(ch) {
			case 1:
				log.info("Welcome to Customer Login");
				log.info("Enter UserName");
				String username=scanner.nextLine();
				log.info("Enter Password");
				String pwd=scanner.nextLine();
				if(DaoClass.login(username, pwd)) {
					
					
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
							value = Integer.parseInt(scanner.nextLine());
						}
						catch(NumberFormatException e) {}
						
						switch(value) {
						case 1:
							List<Product> productList = DaoClass.getAllProduct();
							for(Product p:productList) {
								log.info("ID-"+p.getP_id()+" "+p.getP_name()+" Price-"+p.getP_price());	
							}
								
							break;
							
						case 2:
							log.info("Enter Product Id");
							int pid = scanner.nextInt();
							if(DaoClass.addCart(pid, username))
								log.info("Successfuly Added to Cart!!!");
							else
								log.info("Error while adding");
							break;
						
						case 3:
							List<Product> cartList = DaoClass.getAllProduct();
							int total = 0;
							for(Product p:cartList) {
								total=+p.getP_price();
								log.info("ID-"+p.getP_id()+" "+p.getP_name()+" Price-"+p.getP_price());	
							}
							log.info("Total Amount "+total);
							break;
							
						case 4:
							log.info("Under Construction");
							break;
							
						case 5:
							log.info("Under Constrution");
							break;
							
						}
						
					}while(value!=6);
					
				}else {
					log.info("Incorrect Username and Password");
				}
				break;
			}
		}while(ch!=4);
	}				

}
