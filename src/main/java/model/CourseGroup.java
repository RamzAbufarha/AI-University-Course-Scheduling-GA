package model;

public class CourseGroup {

    private String courseId;
    private String groupId;

    public CourseGroup(String courseId, String groupId) {
        this.courseId = courseId;
        this.groupId = groupId;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getGroupId() {
        return groupId;
    }
}