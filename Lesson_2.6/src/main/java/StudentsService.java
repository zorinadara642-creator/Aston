import java.util.Iterator;
import java.util.Set;

public class StudentsService {

    public void removeStudentsWithLowAverage(Set<Student> students) {
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getAverageGrade() < 3) {
                iterator.remove();
            }
        }
    }

    public void promoteStudent(Student student) {
        if (student.getAverageGrade() >= 3) {
            student.setCourse(student.getCourse() + 1);
        }
    }

    public void printStudents(Set<Student> students, int course) {
        for (Student student : students) {
            if (student.getCourse() == course) {
                System.out.println(student.getName());
            }
        }
    }
}
