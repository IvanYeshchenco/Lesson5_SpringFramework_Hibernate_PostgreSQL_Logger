package ua.com.dfyzok.lesson_9_decompose_university.dto;

public class LessonSlotDto {

    private Long userId;
    // @DateTimeFormat(iso = ISO.DATE)
    private String timeBegin;
    // @DateTimeFormat(iso = ISO.DATE)
    private String timeEnd;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(String timeBegin) {
        this.timeBegin = timeBegin;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    @Override
    public String toString() {
        return "LessonSlotDto [userId=" + userId + ", timeBegin=" + timeBegin + ", timeEnd=" + timeEnd + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((timeBegin == null) ? 0 : timeBegin.hashCode());
        result = prime * result + ((timeEnd == null) ? 0 : timeEnd.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
        LessonSlotDto other = (LessonSlotDto) obj;
        if (timeBegin == null) {
            if (other.timeBegin != null)
                return false;
        } else if (!timeBegin.equals(other.timeBegin))
            return false;
        if (timeEnd == null) {
            if (other.timeEnd != null)
                return false;
        } else if (!timeEnd.equals(other.timeEnd))
            return false;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        return true;
    }

}
