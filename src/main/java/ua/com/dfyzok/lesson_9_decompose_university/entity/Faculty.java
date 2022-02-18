package ua.com.dfyzok.lesson_9_decompose_university.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "faculties")
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long facultyId;
    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 2, max = 50, message = "Faculty name must be between 2 and 50 characters")
    @Column(name = "faculty_name", length = 50)
    private String facultyName;
    @Version
    private long version;

    public Faculty() {
    }

    public Faculty(long facultyId,
            @NotEmpty(message = "Name cannot be empty") @Size(min = 2, max = 50, message = "Faculty name must be between 2 and 50 characters") String facultyName,
            long version) {
        this.facultyId = facultyId;
        this.facultyName = facultyName;
        this.version = version;
    }

    public long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(long facultyId) {
        this.facultyId = facultyId;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Faculty [facultyId=" + facultyId + ", facultyName=" + facultyName + ", version=" + version + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (facultyId ^ (facultyId >>> 32));
        result = prime * result + ((facultyName == null) ? 0 : facultyName.hashCode());
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
        Faculty other = (Faculty) obj;
        if (facultyId != other.facultyId)
            return false;
        if (facultyName == null) {
            if (other.facultyName != null)
                return false;
        } else if (!facultyName.equals(other.facultyName))
            return false;
        if (version != other.version)
            return false;
        return true;
    }

}
