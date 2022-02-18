package ua.com.dfyzok.lesson_9_decompose_university.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class FacultyDto {

    private int facultyId;
    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 2, max = 50, message = "Faculty name must be between 2 and 50 characters")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    @Override
    public String toString() {
        return "Faculty [name=" + name + ", facultyId=" + facultyId + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + facultyId;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        FacultyDto other = (FacultyDto) obj;
        if (facultyId != other.facultyId)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
}
