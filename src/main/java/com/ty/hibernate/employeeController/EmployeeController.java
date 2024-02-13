package com.ty.hibernate.employeeController;

import java.util.InputMismatchException;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.ty.hibernate.employeeEntity.Employee;
import com.ty.hibernate.employeedao.EmployeeDao;

public class EmployeeController {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("vikas");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		Scanner sc = new Scanner(System.in);

		try {
			boolean flag = true;
			System.out.println("Welcome to Employee Database.\nChoose...");
			while (flag) {
				System.out.println(
						"1. Save Employee\n2. Find Employee by Id\n3. Find Employee by Role\n4. Find Employee by email & password\n5. Remove Employee\n6. Update Employee role\n7. Exit");
				int choice = sc.nextInt();
				switch (choice) {
				case 1: {
					Employee e = new Employee();
					System.out.println("Enter Employee id");
					e.setId(sc.nextInt());
					System.out.println("Enter Employee name");
					e.setName(sc.next());
					System.out.println("Enter Employee email");
					e.setEmail(sc.next());
					System.out.println("Enter Employee password");
					e.setPassword(sc.next());
					System.out.println("Enter Employee role");
					e.setRole(sc.next());
					EmployeeDao.saveUser(emf, em, et, e);
				}
					break;
				case 2: {
					System.out.println("Enter id of Employee to be searched");
					EmployeeDao.findEmployeeBasedId(emf, em, sc.nextInt());
				}
					break;
				case 3: {
					System.out.println("Enter role of Employee to be searched");
					EmployeeDao.findEmployeeBasedRole(emf, em, sc.next());
				}
					break;
				case 4: {
					System.out.println("Enter email & password of Employee to be searched");
					EmployeeDao.findEmployeeBasedEmail_Password(emf, em, sc.next(), sc.next());
				}
					break;
				case 5: {
					System.out.println("Enter id of Employee to be removed");
					EmployeeDao.removeEmployee(emf, em, et, sc.nextInt());
				}
					break;
				case 6: {
					System.out.println("Enter id & new role of Employee to update.");
					EmployeeDao.updateUserRole(emf, em, et, sc.nextInt(), sc.next());
				}
					break;
				case 7: {
					flag = false;
					sc.close();
				}
					break;
				default:
					System.out.println("Wrong choice");
				}

			}
		} catch (InputMismatchException e) {
			System.out.println("Wrong Input");
		}
	}

}
