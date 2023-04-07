package application;

import java.util.List;
import java.util.Scanner;

import model.Dao.DaoFactory;
import model.Dao.DepartmentDao;
import model.entities.Department;

public class ProgramDepartment {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		
		System.out.println("=== Test 1 FindId Department ===");				
		Department search = departmentDao.findById(6);		
		System.out.println(search);
		
		
		System.out.println("\n=== Test 2 FindAllId Department ===");			
		List <Department> list = departmentDao.findAll();		
		for(Department l : list) {
			System.out.println(l);
		}
		
		
		System.out.println("\n=== Test 3 insert Department ===");		
		Department d1 = new Department(null, "Cosmetic");
		departmentDao.insert(d1);
		System.out.println("Inseerted! new Id = " + d1.getId());	
	
	
		System.out.println("\n=== Test 4 Update Department===");		
		Department up1 = departmentDao.findById(6);
		up1.setName("AGRO");
		departmentDao.update(up1);		
		System.out.println("Update Completed.");
		
		
		System.out.println("\n === Test 5 Delete department ===");
		System.out.println("Enter Id for delete: ");		
		int usuario = sc.nextInt();
		departmentDao.deleteById(usuario);		
		System.out.println("Delete complete.");		
		
		sc.close();
	}

}
