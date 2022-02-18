package ua.com.dfyzok.lesson_9_decompose_university.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long courseId;
    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 2, max = 50, message = "Course name must be between 2 and 50 characters")
    @Column(name = "course_name", length = 50)
    private String courseName;
    @Version
    private long version;

    public Course() {
    }

    public Course(long courseId, long version,
            @NotEmpty(message = "Name cannot be empty") @Size(min = 2, max = 50, message = "Course name must be between 2 and 50 characters") String courseName) {
        this.courseId = courseId;
        this.version = version;
        this.courseName = courseName;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String cpurseName) {
        this.courseName = cpurseName;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Course [courseId=" + courseId + ", name=" + courseName + ", version=" + version + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (courseId ^ (courseId >>> 32));
        result = prime * result + ((courseName == null) ? 0 : courseName.hashCode());
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
        Course other = (Course) obj;
        if (courseId != other.courseId)
            return false;
        if (courseName == null) {
            if (other.courseName != null)
                return false;
        } else if (!courseName.equals(other.courseName))
            return false;
        if (version != other.version)
            return false;
        return true;
    }

}
