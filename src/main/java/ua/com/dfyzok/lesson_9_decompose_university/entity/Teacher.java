package ua.com.dfyzok.lesson_9_decompose_university.entity;

import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "teachers")
public class Teacher extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long teacherId;
    @NotEmpty(message = "Position cannot be empty")
    @Size(min = 2, max = 50, message = "Position name must be between 2 and 50 characters")
    @Column(name = "position")
    private String position;
    @OneToMany(mappedBy = "teacher")
    private Set<Lesson> lessons;
    @Version
    private long version;

    public Teacher() {
    }

    public Teacher(long teacherId,
            @NotEmpty(message = "Position cannot be empty") @Size(min = 2, max = 50, message = "Position name must be between 2 and 50 characters") String position,
            Set<Lesson> lessons, long version) {
        this.teacherId = teacherId;
        this.position = position;
        this.lessons = lessons;
        this.version = version;
    }

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Set<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(Set<Lesson> lessons) {
        this.lessons = lessons;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((lessons == null) ? 0 : lessons.hashCode());
        result = prime * result + ((position == null) ? 0 : position.hashCode());
        result = prime * result + (int) (teacherId ^ (teacherId >>> 32));
        result = prime * result + (int) (version ^ (version >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Teacher [teacherId=" + teacherId + ", position=" + position + ", lessons=" + lessons + ", version="
                + version + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Teacher other = (Teacher) obj;
        if (lessons == null) {
            if (other.lessons != null)
                return false;
        } else if (!lessons.equals(other.lessons))
            return false;
        if (position == null) {
            if (other.position != null)
                return false;
        } else if (!position.equals(other.position))
            return false;
        if (teacherId != other.teacherId)
            return false;
        if (version != other.version)
            return false;
        return true;
    }

}
