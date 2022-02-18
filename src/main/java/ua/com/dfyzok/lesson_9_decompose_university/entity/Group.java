package ua.com.dfyzok.lesson_9_decompose_university.entity;

import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import ua.com.dfyzok.lesson_9_decompose_university.validator.GroupName;

@Entity
@Table(name = "groups")
public class Group {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long groupId;
	@GroupName
	@NotEmpty(message = "Name cannot be empty")
	@Column(name = "group_name", length = 10)
	private String groupName;
	@Min(value = 8, message = "Capacity should not be less than 8")
	@Max(value = 30, message = "Capacity should not be greater than 30")
	@Column(name = "capacity")
	private int capacity;
	@OneToMany(mappedBy = "group")
	private Set<Lesson> lessons;
	@OneToMany(mappedBy = "group")
	private Set<Student> students;
	@Version
	private long version;

	public Group() {
	}

	public Group(long groupId, @NotEmpty(message = "Name cannot be empty") String groupName,
			@Min(value = 8, message = "Capacity should not be less than 8") @Max(value = 30, message = "Capacity should not be greater than 30") int capacity,
			Set<Lesson> lessons, Set<Student> students, long version) {
		this.groupId = groupId;
		this.groupName = groupName;
		this.capacity = capacity;
		this.lessons = lessons;
		this.students = students;
		this.version = version;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String name) {
		this.groupName = name;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public Set<Lesson> getLessons() {
		return lessons;
	}

	public void setLessons(Set<Lesson> lessons) {
		this.lessons = lessons;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "Group [groupId=" + groupId + ", name=" + groupName + ", capacity=" + capacity + ", lessons=" + lessons
				+ ", students=" + students + ", version=" + version + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + capacity;
		result = prime * result + (int) (groupId ^ (groupId >>> 32));
		result = prime * result + ((lessons == null) ? 0 : lessons.hashCode());
		result = prime * result + ((groupName == null) ? 0 : groupName.hashCode());
		result = prime * result + ((students == null) ? 0 : students.hashCode());
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
		Group other = (Group) obj;
		if (capacity != other.capacity)
			return false;
		if (groupId != other.groupId)
			return false;
		if (lessons == null) {
			if (other.lessons != null)
				return false;
		} else if (!lessons.equals(other.lessons))
			return false;
		if (groupName == null) {
			if (other.groupName != null)
				return false;
		} else if (!groupName.equals(other.groupName))
			return false;
		if (students == null) {
			if (other.students != null)
				return false;
		} else if (!students.equals(other.students))
			return false;
		if (version != other.version)
			return false;
		return true;
	}

}
