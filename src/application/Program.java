package application;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import model.Dao.DaoFactory;
import model.Dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		
		System.out.println("=== TEST 1: seller findById ===");
		
		Seller seller = sellerDao.findById(3);
		
		System.out.println(seller);
		
		
		System.out.println("\n === TEST 2: seler findByDepartment ==");
		
		Department department = new Department(2, null);
		
		List <Seller> list = sellerDao.findByDepartment(department);
		
		for (Seller i : list) {
		
			System.out.println(i);
		}
		
		
		System.out.println("\n === TEST 3: seler findALl ==");
		
		list = sellerDao.findAll();
		
		for (Seller i : list) {
		
			System.out.println(i);
		}
		

		System.out.println("\n === TEST 4: seler Insert ==");
		
		Seller newSeller = new Seller(null, "Vinicius", "vinicius.gmail.com", new Date(0), 3000.0, department);
		sellerDao.insert(newSeller);
		System.out.println("Inseerted! new Id = " + newSeller.getId());
	
	
		System.out.println("\n === TEST 5: seler Update ==");
		
		seller = sellerDao.findById(1);
		seller.setName("Martha Wayne");
		sellerDao.update(seller);
		
		
		System.out.println("\n === TEST 6: seler delete ==");
		
		System.out.println("Enter id for delete id. ");
		
		int i = sc.nextInt();
		
		sellerDao.deleteById(i);
		System.out.println("Delete complete");
		
		sc.close();
		
	}
}
