package sba.sms.services;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import sba.sms.models.Student;
import sba.sms.utils.CommandLine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
class StudentServiceTest {

    static StudentServiceImpl studentService;

    @BeforeAll
    static void beforeAll() {
        studentService = new StudentServiceImpl();
        CommandLine.addData();
    }


    @Test
    void testGetAllStudents() {

        List<Student> expected = new ArrayList<>(Arrays.asList(
                new Student("annette@gmail.com", "annette allen", "password"),
                new Student("anthony@gmail.com", "anthony gallegos", "password"),
                new Student("ariadna@gmail.com", "ariadna ramirez", "password"),
                new Student("bolaji@gmail.com", "bolaji saibu", "password"),
                new Student("reema@gmail.com", "reema brown", "password")
        ));


        Assertions.assertThat(new ArrayList<>(studentService.getAllStudents()).containsAll(expected));

    }
}