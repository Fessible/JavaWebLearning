package entity;

import java.util.HashSet;
import java.util.Set;

public class Student {

    private Integer sid;

    private String studentName;

    private Set<Course> courseSet = new HashSet<>();

    @Override
    public String toString() {
        return "Student{" +
                "sid=" + sid +
                ", studentName='" + studentName + '\'' +
                ", courseSet=" + courseSet +
                '}';
    }

    public Set<Course> getCourseSet() {
        return courseSet;
    }

    public void setCourseSet(Set<Course> courseSet) {
        this.courseSet = courseSet;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
