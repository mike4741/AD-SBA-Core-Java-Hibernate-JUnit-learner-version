package sba.sms.services;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import sba.sms.models.Course;
import sba.sms.models.Student;
import sba.sms.utils.CommandLine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import  static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class CourseServiceImplTest {

    static CourseServiceImpl courseService;

    @BeforeAll
    static void beforeAll() {
        courseService = new CourseServiceImpl();
        CommandLine.addData();
    }

    @Test
    void testCreateCourse() {

//        given
      Course course =  new Course("C#", "mike");
      int count = courseService.getAllCourses().size();

//        when
        courseService.createCourse(course);

//        then
      assertEquals(count+1,courseService.getAllCourses().size());


    }

    @Test
    void testGetAllCourses() {

        String instructorPhillip = "Phillip Witkin";
        List<Course> expected = new ArrayList<>(Arrays.asList(
                                                   new Course("Java", instructorPhillip),
                                                   new Course("Frontend", "Kasper Kain"),
                                                   new Course("JPA", "Jafer Alhaboubi"),
                                                   new Course("Spring Framework", instructorPhillip),
                                                   new Course("SQL", instructorPhillip)
//                                                   new Course("C#", "mike")

        ));
        Assertions.assertThat((long) courseService.getAllCourses().size()).isEqualTo(expected.size());
    }

    @Test
    void testGetCourseById() {
//         given
        int id = 1;
//         when
        Course course = courseService.getCourseById(id);
//         then
        assertThat(course.getName().equals("Java"));
        assertThat(course.getInstructor().equals("Phillip Witkin"));
    }




  }