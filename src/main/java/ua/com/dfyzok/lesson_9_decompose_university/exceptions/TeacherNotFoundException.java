package ua.com.dfyzok.lesson_9_decompose_university.exceptions;

public class TeacherNotFoundException extends Exception {

    public TeacherNotFoundException(Long id) {
        super("Could not find teacher " + id);
    }

}
