package models;

public enum Grade {

    FIRST("first"),
    SECOND("second"),
    THIRD("third"),
    FOURTH("fourth"),
	FIFTH("fifth"),
	SIXTH("sixth");

    private String grade;

    Grade(String grade) { this.grade = grade; }

    public String getGrade() { return grade; }




}