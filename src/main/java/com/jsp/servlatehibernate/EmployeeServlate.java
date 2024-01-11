package com.jsp.servlatehibernate;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class EmployeeServlate extends HttpServlet {


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int id=Integer.parseInt(req.getParameter("empId"));
		String name=req.getParameter("empName");
		String email=req.getParameter("empEmail");
		String address=req.getParameter("empAddress");
		
		Employee emp= new Employee();
		emp.setEmpId(id);
		emp.setEmpName(name);
		emp.setEmpEmail(email);
		emp.setEmpAddress(address);



		SessionFactory cfg=new Configuration().configure().addAnnotatedClass(Employee.class).buildSessionFactory();

		Session session=cfg.openSession();

		Transaction tran=session.beginTransaction();

		session.save(emp);

		tran.commit();
		session.close();
		
		String message ="DATA SUCCESSFULLY INSERTED!!!";
		req.setAttribute("m", message);
		RequestDispatcher requestDispatcher=req.getRequestDispatcher("output.jsp");
		requestDispatcher.forward(req, resp);
	}



}


