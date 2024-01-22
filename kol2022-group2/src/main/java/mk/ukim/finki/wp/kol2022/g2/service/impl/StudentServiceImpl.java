package mk.ukim.finki.wp.kol2022.g2.service.impl;

import mk.ukim.finki.wp.kol2022.g2.model.Course;
import mk.ukim.finki.wp.kol2022.g2.model.Student;
import mk.ukim.finki.wp.kol2022.g2.model.StudentType;
import mk.ukim.finki.wp.kol2022.g2.model.exceptions.InvalidCourseIdException;
import mk.ukim.finki.wp.kol2022.g2.model.exceptions.InvalidStudentIdException;
import mk.ukim.finki.wp.kol2022.g2.repository.CourseRepository;
import mk.ukim.finki.wp.kol2022.g2.repository.StudentRepository;
import mk.ukim.finki.wp.kol2022.g2.service.StudentService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Student> listAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id).orElseThrow(InvalidStudentIdException::new);
    }

    @Override
    public Student create(String name, String email, String password, StudentType type, List<Long> courseId, LocalDate enrollmentDate) {
        List<Course> courses = courseRepository.findAllById(courseId);
        Student student = new Student(name,email,password,type,courses,enrollmentDate);

        studentRepository.save(student);

        return student;
    }

    @Override
    public Student update(Long id, String name, String email, String password, StudentType type, List<Long> coursesId, LocalDate enrollmentDate) {
        List<Course> courses = courseRepository.findAllById(coursesId);
        Student student = studentRepository.findById(id).orElseThrow(InvalidStudentIdException::new);

        student.setName(name);
        student.setEmail(email);
        student.setPassword(password);
        student.setType(type);
        student.setCourses(courses);
        student.setEnrollmentDate(enrollmentDate);

        studentRepository.save(student);

        return student;
    }

    @Override
    public Student delete(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(InvalidStudentIdException::new);
        studentRepository.delete(student);
        return student;
    }

    @Override
    public List<Student> filter(Long courseId, Integer yearsOfStudying) {
        if(courseId == null && yearsOfStudying == null){
            return studentRepository.findAll();
        }
        else if(courseId != null && yearsOfStudying != null){
            Course course = courseRepository.findById(courseId).orElseThrow(InvalidCourseIdException::new);
            LocalDate date = LocalDate.now().minusYears(yearsOfStudying);
            return studentRepository.findAllByCoursesContainingAndEnrollmentDateBefore(course,date);
        }
        else if(courseId != null){
            Course course = courseRepository.findById(courseId).orElseThrow(InvalidCourseIdException::new);
            return studentRepository.findAllByCoursesContains(course);
        }
        else{
            LocalDate date = LocalDate.now().minusYears(yearsOfStudying);
            return studentRepository.findAllByEnrollmentDateBefore(date);
        }
    }
}
