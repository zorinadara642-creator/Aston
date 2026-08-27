import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<Student> students = new HashSet<>();

        students.add(new Student("Иван Иванов", "Группа-101", 1, Arrays.asList(4, 5, 3)));
        students.add(new Student("Петр Петров", "Группа-101", 1, Arrays.asList(2, 2, 3)));
        students.add(new Student("Анна Анновна", "Группа-202", 2, Arrays.asList(5, 5, 4)));
        students.add(new Student("Мария Мариевна", "Группа-202", 2, Arrays.asList(3, 3, 3)));

        StudentsService service = new StudentsService();

        System.out.println("Все студенты 1 курса до удаления и перевода:");
        service.printStudents(students, 1);

        service.removeStudentsWithLowAverage(students);

        for (Student student : students) {
            service.promoteStudent(student);
        }

        System.out.println();
        System.out.println("Студенты на 2 курсе после удаления и перевода:");
        service.printStudents(students, 2);

        PhoneBook phoneBook = new PhoneBook();

        phoneBook.add("Иванов", "8 900 111 32 22");
        phoneBook.add("Петров", "8 960 358 77 45");
        phoneBook.add("Иванов", "8 950 325 88 77");

        System.out.println("Телефоны Иванова: " + phoneBook.get("Иванов"));
        System.out.println("Телефоны Петрова: " + phoneBook.get("Петров"));
        System.out.println("Телефоны Сидорова: " + phoneBook.get("Сидоров"));
    }
}
