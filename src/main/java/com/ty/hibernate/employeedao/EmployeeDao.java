package com.ty.hibernate.employeedao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.ty.hibernate.employeeEntity.Employee;

public class EmployeeDao {

	public static Employee saveUser(EntityManagerFactory emf, EntityManager em, EntityTransaction et, Employee e) {

		et.begin();
		em.persist(e);
		et.commit();
		System.out.println("Employee Saved Successfully!!!");
		return e;
	}

	public static Employee findEmployeeBasedId(EntityManagerFactory emf, EntityManager em, int id) {
		Employee e = em.find(Employee.class, id);
		if (e != null) {
			System.out.println(e);
			return e;
		}
		System.out.println("Employee does not exist!!");
		return e;
	}

	public static List<Employee> findEmployeeBasedRole(EntityManagerFactory emf, EntityManager em, String role) {
		Query q = em.createQuery("SELECT e FROM Employee e where e.role = ?1");
		q.setParameter(1, role);

		List<Employee> employees = q.getResultList();
		if (employees != null && !(employees.isEmpty())) {
			for (Employee e : employees) {
				System.out.println(e);
			}
		}
		else {
			System.out.println("Employee(s) with this role does not exist!!!");
		}
		return employees;
	}
	
	public static List<Employee> findEmployeeBasedEmail_Password(EntityManagerFactory emf, EntityManager em, String email, String password) {
		Query q = em.createQuery("SELECT e FROM Employee e where e.email = ?1 AND e.password = ?2");
		q.setParameter(1, email);
		q.setParameter(2, password);

		List<Employee> employees = q.getResultList();
		if (employees != null && !(employees.isEmpty())) {
			for (Employee e : employees) {
				System.out.println(e);
			}
		}
		else {
			System.out.println("Employee with this email & password does not exist!!!");
		}
		return employees;
	}
	
	
	public static boolean removeEmployee(EntityManagerFactory emf, EntityManager em, EntityTransaction et, int id) {
		Employee e = em.find(Employee.class, id);
		if (e != null) {
			et.begin();
			em.remove(e);
			et.commit();
			System.out.println("Employee removed sucessfully!!");
			return true;
		}
		System.out.println("Employee does not exist with this id!!");
		return false;
	}
	
	
	public static boolean updateUserRole(EntityManagerFactory emf, EntityManager em, EntityTransaction et, int id, String role) {
		Employee e = em.find(Employee.class, id);
		if (e != null) {
			e.setRole(role);
			et.begin();
			em.merge(e);
			et.commit();
			System.out.println("Employee role updated");
			return true;
		}
		System.out.println("Employee does not exist with this id!!");
		return false;
	}

}
