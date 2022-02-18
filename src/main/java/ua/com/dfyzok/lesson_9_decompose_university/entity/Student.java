package ua.com.dfyzok.lesson_9_decompose_university.entity;

import javax.persistence.*;

@Entity
@Table(name = "students")
public class Student extends Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long studentId;
	@ManyToOne
	@JoinColumn(name = "group_id")
	private Group group;
	@Version
	private long version;

	public Student() {
	}

	public Student(Long studentId, Group group, Long version) {
		this.studentId = studentId;
		this.group = group;
		this.version = version;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", group=" + group + ", version=" + version + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result + (int) (studentId ^ (studentId >>> 32));
		result = prime * result + (int) (version ^ (version >>> 32));
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
		Student other = (Student) obj;
		if (group == null) {
			if (other.group != null)
				return false;
		} else if (!group.equals(other.group))
			return false;
		if (studentId != other.studentId)
			return false;
		if (version != other.version)
			return false;
		return true;
	}

}
