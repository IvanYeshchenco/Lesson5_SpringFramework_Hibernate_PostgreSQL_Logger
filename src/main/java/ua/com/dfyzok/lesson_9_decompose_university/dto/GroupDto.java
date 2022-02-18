package ua.com.dfyzok.lesson_9_decompose_university.dto;

import java.util.Collection;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import ua.com.dfyzok.lesson_9_decompose_university.entity.Student;
import ua.com.dfyzok.lesson_9_decompose_university.validator.GroupName;

public class GroupDto {

    private int groupId;
    @GroupName
    @NotEmpty(message = "Name cannot be empty")
    @Column(name = "group_name", length = 10)
    private String name;
    @Min(value = 8, message = "Capacity should not be less than 8")
    @Max(value = 30, message = "Capacity should not be greater than 30")
    private int capacity;
    private Collection<Student> students;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Collection<Student> getStudents() {
        return students;
    }

    public void setStudents(Collection<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Group [groupId=" + groupId + ", name=" + name + ", capacity=" + capacity + ", students=" + students
                + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + capacity;
        result = prime * result + groupId;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((students == null) ? 0 : students.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GroupDto other = (GroupDto) obj;
        if (capacity != other.capacity)
            return false;
        if (groupId != other.groupId)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (students == null) {
            if (other.students != null)
                return false;
        } else if (!students.equals(other.students))
            return false;
        return true;
    }
}
