package ua.com.dfyzok.lesson_9_decompose_university.exceptions;

public class GroupNotFoundException extends Exception {

    public GroupNotFoundException(Long id) {
        super("Could not find group " + id);
    }

}
