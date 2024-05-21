import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Person {
    private String name;
    private String id;

    public Person(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

class Student extends Person {
    private Map<Course, String> enrolledCourses;

    public Student(String name, String id) {
        super(name, id);
        this.enrolledCourses = new HashMap<>();
    }

    public void enrollInCourse(Course course) {
        enrolledCourses.put(course, null);
    }

    public void assignGrade(Course course, String grade) {
        if (enrolledCourses.containsKey(course)) {
            enrolledCourses.put(course, grade);
        }
    }

    public Map<Course, String> getEnrolledCourses() {
        return enrolledCourses;
    }
}

class Teacher extends Person {
    private List<Course> coursesTeaching;

    public Teacher(String name, String id) {
        super(name, id);
        this.coursesTeaching = new ArrayList<>();
    }

    public void addCourse(Course course) {
        coursesTeaching.add(course);
    }

    public List<Course> getCoursesTeaching() {
        return coursesTeaching;
    }
}

class Course {
    private String courseName;
    private String courseCode;
    private Teacher teacher;

    public Course(String courseName, String courseCode) {
        this.courseName = courseName;
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}

class SchoolManagementSystem {
    private List<Student> students;
    private List<Teacher> teachers;
    private List<Course> courses;

    public SchoolManagementSystem() {
        students = new ArrayList<>();
        teachers = new ArrayList<>();
        courses = new ArrayList<>();
    }

    // Student management
    public void addStudent(Student student) {
        students.add(student);
    }

    public void deleteStudent(String studentId) {
        students.removeIf(student -> student.getId().equals(studentId));
    }

    public void updateStudent(String studentId, String newName) {
        for (Student student : students) {
            if (student.getId().equals(studentId)) {
                student.setName(newName);
                break;
            }
        }
    }

    // Teacher management
    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    public void deleteTeacher(String teacherId) {
        teachers.removeIf(teacher -> teacher.getId().equals(teacherId));
    }

    public void updateTeacher(String teacherId, String newName) {
        for (Teacher teacher : teachers) {
            if (teacher.getId().equals(teacherId)) {
                teacher.setName(newName);
                break;
            }
        }
    }

    // Course management
    public void addCourse(Course course) {
        courses.add(course);
    }

    public void deleteCourse(String courseCode) {
        courses.removeIf(course -> course.getCourseCode().equals(courseCode));
    }

    public void assignTeacherToCourse(String teacherId, String courseCode) {
        Teacher teacher = findTeacherById(teacherId);
        Course course = findCourseByCode(courseCode);
        if (teacher != null && course != null) {
            course.setTeacher(teacher);
            teacher.addCourse(course);
        }
    }

    // Enrollment management
    public void enrollStudentInCourse(String studentId, String courseCode) {
        Student student = findStudentById(studentId);
        Course course = findCourseByCode(courseCode);
        if (student != null && course != null) {
            student.enrollInCourse(course);
        }
    }

    public void assignGradeToStudent(String studentId, String courseCode, String grade) {
        Student student = findStudentById(studentId);
        Course course = findCourseByCode(courseCode);
        if (student != null && course != null) {
            student.assignGrade(course, grade);
        }
    }

    // Helper methods
    private Student findStudentById(String studentId) {
        for (Student student : students) {
            if (student.getId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    private Teacher findTeacherById(String teacherId) {
        for (Teacher teacher : teachers) {
            if (teacher.getId().equals(teacherId)) {
                return teacher;
            }
        }
        return null;
    }

    private Course findCourseByCode(String courseCode) {
        for (Course course : courses) {
            if (course.getCourseCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }

    // Display functions for testing purposes
    public void displayStudents() {
        for (Student student : students) {
            System.out.println("Student ID: " + student.getId() + ", Name: " + student.getName());
        }
    }

    public void displayTeachers() {
        for (Teacher teacher : teachers) {
            System.out.println("Teacher ID: " + teacher.getId() + ", Name: " + teacher.getName());
        }
    }

    public void displayCourses() {
        for (Course course : courses) {
            System.out.println("Course Code: " + course.getCourseCode() + ", Name: " + course.getCourseName() + ", Teacher: " + (course.getTeacher() != null ? course.getTeacher().getName() : "None"));
        }
    }
}

public class Main {
    public static void main(String[] args) {
        SchoolManagementSystem sms = new SchoolManagementSystem();

        // Create students
        Student s1 = new Student("John Doe", "S001");
        Student s2 = new Student("Jane Smith", "S002");

        // Add students to the system
        sms.addStudent(s1);
        sms.addStudent(s2);

        // Create teachers
        Teacher t1 = new Teacher("Mr. Brown", "T001");
        Teacher t2 = new Teacher("Ms. Green", "T002");

        // Add teachers to the system
        sms.addTeacher(t1);
        sms.addTeacher(t2);

        // Create courses
        Course c1 = new Course("Math", "C001");
