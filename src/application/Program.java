package application;

import java.sql.Date;

import model.entities.Departament;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		Departament obj = new Departament(1, "books");
		
		Seller obj1 = new Seller(21, "BOB", "bob@gmail.com", new Date(0), 3000.0, obj);
		
		System.out.println(obj1);

	}

}
