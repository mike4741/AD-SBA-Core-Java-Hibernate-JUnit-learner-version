package sba.sms.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
@FieldDefaults(level =  AccessLevel.PRIVATE)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString



@Table(name = "course")

public class Course {
//    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "courseId", nullable = false)
    private int courseId;



    @Column(name = "instructor", nullable = false , columnDefinition = "VARCHAR(50)")
    String instructor;
    @Column(name = "name", nullable = false , columnDefinition = "VARCHAR(50)")
    String name;

    @ToString.Exclude
    @ManyToMany( mappedBy = "courseList" , fetch = FetchType.EAGER)

    List<Student> students;

    public Course(String instructor , String name ) {
        this.name = name;
        this.instructor = instructor;

    }
}
