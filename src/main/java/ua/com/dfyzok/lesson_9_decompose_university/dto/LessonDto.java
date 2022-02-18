package ua.com.dfyzok.lesson_9_decompose_university.dto;

import java.text.SimpleDateFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class LessonDto {

    private int lessonId;
    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 2, max = 50, message = "Lesson name must be between 2 and 50 characters")
    private String name;
    // @FutureOrPresent
    // @DateTimeFormat(iso = ISO.DATE)
    private String time;
    @Size(min = 2, max = 50, message = "Curse name must be between 2 and 50 characters")
    private String courseName;
    private int groupId;
    private int teacherId;

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return "Lesson [lessonId=" + lessonId + ", name=" + name + ", time=" + time + ", courseName=" + courseName
                + ", groupId=" + groupId + ", teacherId=" + teacherId + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((courseName == null) ? 0 : courseName.hashCode());
        result = prime * result + groupId;
        result = prime * result + lessonId;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + teacherId;
        result = prime * result + ((time == null) ? 0 : time.hashCode());
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
        LessonDto other = (LessonDto) obj;
        if (courseName == null) {
            if (other.courseName != null)
                return false;
        } else if (!courseName.equals(other.courseName))
            return false;
        if (groupId != other.groupId)
            return false;
        if (lessonId != other.lessonId)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (teacherId != other.teacherId)
            return false;
        if (time == null) {
            if (other.time != null)
                return false;
        } else if (!time.equals(other.time))
            return false;
        return true;
    }
}
