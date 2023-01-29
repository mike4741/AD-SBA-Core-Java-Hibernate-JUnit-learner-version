package sba.sms.services;

import jakarta.persistence.Entity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import sba.sms.dao.StudentI;
import sba.sms.models.Course;
import sba.sms.models.Student;
import sba.sms.utils.HibernateUtil;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public class StudentServiceImpl implements StudentI {


    @Override
    public void createStudent(Student student) {
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {

            tx = session.beginTransaction();
            session.merge(student);
            tx.commit();

        } catch (
                HibernateException ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Student> getAllStudents() {
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Student> studentList = null;
        try {
            studentList = session.createNativeQuery("Select * from  student", Student.class).getResultList();

        } catch (
                HibernateException ex) {
            ex.printStackTrace();


        } finally {
            session.close();
        }
        return studentList;
    }

    @Override
    public boolean validateStudent(String email, String password) {
        boolean result = false;
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Student student = session.get(Student.class, email);
            if (student.getEmail() != null && student.getPassword().equals(password)) {
                result = true;
            }

        } catch (
                HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public Student getStudentByEmail(String email) {
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        Student student = null;
        try {
            student = session.get(Student.class, email);

        } catch (
                HibernateException ex) {
            ex.printStackTrace();


        } finally {
            session.close();
        }
        return student;
    }

    @Override
    public void registerStudentToCourse(String email, int courseId) {

        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Student student = session.get(Student.class, email);
            Course course = session.get(Course.class, courseId);

            tx = session.beginTransaction();
            List<Course> cl = student.getCourseList();
            if (!cl.contains(course)) {
                student.addCourse(course);
                session.persist(student);
            }


            tx.commit();

        } catch (
                HibernateException ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }

    }

    @Override
    public List<Course> getStudentCourses(String email) {

        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Course> courseList = null;
        try {

            Student student = session.get(Student.class, email);


            courseList = student.getCourseList();

        } catch (
                HibernateException ex) {
            ex.printStackTrace();


        } finally {
            session.close();
        }
        return courseList;
    }


}
