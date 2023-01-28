package sba.sms.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
@FieldDefaults(level =  AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
//@Data
@Table(name = "student")
@Entity
@NamedQueries({
        @NamedQuery(name = "getAll", query = "from Student"),
        @NamedQuery(name = "getByEmail", query = "from Student where email = :email")
})
public class Student {

    @Id
    @Column(name = "email", nullable = false , unique = true , columnDefinition = "VARCHAR(50)")
    String email;
    @Column(name = "name", nullable = false , columnDefinition = "VARCHAR(50)")
    String name ;
    @Column(name = "password", nullable = false , columnDefinition = "VARCHAR(50)")
    String password;
    @ToString.Exclude
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH},
            fetch = FetchType.EAGER)


    @JoinTable(name="student_course",
            joinColumns = @JoinColumn(name="email"),
            inverseJoinColumns = @JoinColumn(name="id"))
    List<Course> courseList = new LinkedList<Course>();

    public Student(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public void addCourse(Course course) {
        courseList.add(course);
        course.getStudents().add(this);

    }

}
