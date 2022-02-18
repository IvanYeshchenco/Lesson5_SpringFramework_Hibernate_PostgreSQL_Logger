package ua.com.dfyzok.lesson_9_decompose_university.exceptions;

public class LessonNotFoundException extends RuntimeException {

    public LessonNotFoundException(Long id) {
        super("Could not find lesson " + id);
    }

}
