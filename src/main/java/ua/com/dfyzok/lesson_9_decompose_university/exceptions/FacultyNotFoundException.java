package ua.com.dfyzok.lesson_9_decompose_university.exceptions;

public class FacultyNotFoundException extends RuntimeException {

    public FacultyNotFoundException(Long id) {
        super("Could not find faculty " + id);
    }

}
