package sba.sms.services;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import sba.sms.dao.CourseI;
import sba.sms.models.Course;
import sba.sms.models.Student;
import sba.sms.utils.HibernateUtil;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public class CourseServiceImpl implements CourseI {

    @Override
    public void createCourse(Course course) {
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {

            tx = session.beginTransaction();
            session.merge(course);
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
    public List<Course> getAllCourses() {
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Course> courseList = null;
        try {
            courseList = session.createNativeQuery("Select * from  course", Course.class).getResultList();

        } catch (
                HibernateException ex) {
            ex.printStackTrace();


        } finally {
            session.close();
        }
        return courseList;
    }

    @Override
    public Course getCourseById(int id) {
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        Course course = null;
        try {
            course = session.get(Course.class, id);

        } catch (
                HibernateException ex) {
            ex.printStackTrace();


        } finally {
            session.close();
        }
        return course;
    }


}
