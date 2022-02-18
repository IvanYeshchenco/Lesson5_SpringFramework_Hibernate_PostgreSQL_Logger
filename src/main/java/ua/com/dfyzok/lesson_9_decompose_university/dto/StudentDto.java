package ua.com.dfyzok.lesson_9_decompose_university.dto;

public class StudentDto extends PersonDto {

    private int studentId;
    private int groupId;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "Student [studentId=" + studentId + ", group=" + groupId + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + groupId;
        result = prime * result + studentId;
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
        StudentDto other = (StudentDto) obj;
        if (groupId != other.groupId)
            return false;
        if (studentId != other.studentId)
            return false;
        return true;
    }

}
