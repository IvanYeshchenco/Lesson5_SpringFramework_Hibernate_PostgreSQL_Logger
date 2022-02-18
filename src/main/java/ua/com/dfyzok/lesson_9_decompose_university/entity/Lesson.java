package ua.com.dfyzok.lesson_9_decompose_university.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity(name = "lesson")
@Table(name = "lessons")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long lessonId;
    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 2, max = 50, message = "Lesson name must be between 2 and 50 characters")
    @Column(name = "lesson_name", length = 50)
    private String lessonName;
    @Column(name = "lesson_time")
    // @DateTimeFormat(iso = ISO.DATE)
    private String time;
    @Size(min = 2, max = 50, message = "Curse name must be between 2 and 50 characters")
    @Column(name = "lesson_course_name", length = 50)
    private String courseName;
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
    @Version
    private long version;

    public Lesson() {
    }

    public Lesson(long lessonId,
            @NotEmpty(message = "Name cannot be empty") @Size(min = 2, max = 50, message = "Lesson name must be between 2 and 50 characters") String lessonName,
            @FutureOrPresent String time,
            @Size(min = 2, max = 50, message = "Curse name must be between 2 and 50 characters") String courseName,
            Group group, Teacher teacher, long version) {
        this.lessonId = lessonId;
        this.lessonName = lessonName;
        this.time = time;
        this.courseName = courseName;
        this.group = group;
        this.teacher = teacher;
        this.version = version;
    }

    public long getLessonId() {
        return lessonId;
    }

    public void setLessonId(long lessonId) {
        this.lessonId = lessonId;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Lesson [lessonId=" + lessonId + ", lessonName=" + lessonName + ", time=" + time + ", courseName="
                + courseName + ", group=" + group + ", teacher=" + teacher + ", version=" + version + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((courseName == null) ? 0 : courseName.hashCode());
        result = prime * result + ((group == null) ? 0 : group.hashCode());
        result = prime * result + (int) (lessonId ^ (lessonId >>> 32));
        result = prime * result + ((lessonName == null) ? 0 : lessonName.hashCode());
        result = prime * result + ((teacher == null) ? 0 : teacher.hashCode());
        result = prime * result + ((time == null) ? 0 : time.hashCode());
        result = prime * result + (int) (version ^ (version >>> 32));
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
        Lesson other = (Lesson) obj;
        if (courseName == null) {
            if (other.courseName != null)
                return false;
        } else if (!courseName.equals(other.courseName))
            return false;
        if (group == null) {
            if (other.group != null)
                return false;
        } else if (!group.equals(other.group))
            return false;
        if (lessonId != other.lessonId)
            return false;
        if (lessonName == null) {
            if (other.lessonName != null)
                return false;
        } else if (!lessonName.equals(other.lessonName))
            return false;
        if (teacher == null) {
            if (other.teacher != null)
                return false;
        } else if (!teacher.equals(other.teacher))
            return false;
        if (time == null) {
            if (other.time != null)
                return false;
        } else if (!time.equals(other.time))
            return false;
        if (version != other.version)
            return false;
        return true;
    }

}
