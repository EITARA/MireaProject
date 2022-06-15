package ru.mirea.lomako.mireaproject;

public class RecyclerData {
    private String courseName;
    private String courseimg;
    private String courseMode;
    private String courseLabs;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseimg() {
        return courseimg;
    }

    public void setCourseimg(String courseimg) {
        this.courseimg = courseimg;
    }

    public String getCourseMode() {
        return courseMode;
    }

    public void setCourseMode(String courseMode) {
        this.courseMode = courseMode;
    }

    public String getcourseLabs() {

        return courseLabs;
    }

    public void setCourseTracks(String courseLabs) {
        this.courseLabs = courseLabs;
    }

    public RecyclerData(String courseName, String courseimg, String courseMode, String courseLabs) {
        this.courseName = courseName;
        this.courseimg = courseimg;
        this.courseMode = courseMode;
        this.courseLabs = courseLabs;
    }

}
