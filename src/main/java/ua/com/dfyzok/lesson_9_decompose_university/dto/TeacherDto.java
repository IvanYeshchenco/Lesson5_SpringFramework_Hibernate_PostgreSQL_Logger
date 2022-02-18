package ua.com.dfyzok.lesson_9_decompose_university.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class TeacherDto extends PersonDto {

    private int teacherId;
    @NotEmpty(message = "Position cannot be empty")
    @Size(min = 2, max = 50, message = "Position name must be between 2 and 50 characters")
    private String position;

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int groupId) {
        this.teacherId = groupId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Teacher [courseId=" + teacherId + ", position=" + position + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((position == null) ? 0 : position.hashCode());
        result = prime * result + teacherId;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        TeacherDto other = (TeacherDto) obj;
        if (position == null) {
            if (other.position != null)
                return false;
        } else if (!position.equals(other.position))
            return false;
        if (teacherId != other.teacherId)
            return false;
        return true;
    }
}
