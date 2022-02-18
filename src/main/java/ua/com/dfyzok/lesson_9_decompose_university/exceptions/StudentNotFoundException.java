package ua.com.dfyzok.lesson_9_decompose_university.exceptions;

public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException(Long id) {
        super("Could not find student " + id);
    }

}
