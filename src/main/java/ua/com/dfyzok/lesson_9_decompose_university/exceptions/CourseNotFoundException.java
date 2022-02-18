package ua.com.dfyzok.lesson_9_decompose_university.exceptions;

public class CourseNotFoundException extends RuntimeException {

    public CourseNotFoundException(Long id) {
        super("Could not find course " + id);
    }

}
