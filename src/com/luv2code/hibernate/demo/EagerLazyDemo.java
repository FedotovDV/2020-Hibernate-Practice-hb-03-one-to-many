package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class EagerLazyDemo {

	public static void main(String[] args) {
		try (SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class).buildSessionFactory(); Session session = factory.getCurrentSession()) {	
			
			// start a transaction
			session.beginTransaction();
			
			// get the instructor from db
			int theId = 1;
			Instructor tempInstructor = session.get(Instructor.class, theId);		
			
			System.out.println("luv2code: Instructor: " + tempInstructor);
		
			System.out.println("luv2code: Courses: " + tempInstructor.getCourses());
			
			// commit transaction
			session.getTransaction().commit();
			
			session.close();
			
			System.out.println("\nluv2code: The session is now closed!\n");

			// option 1: call getter method while session is open
			
			// get courses for the instructor
			System.out.println("luv2code: Courses: " + tempInstructor.getCourses());
			
			
			System.out.println("luv2code: Done!");
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

}



