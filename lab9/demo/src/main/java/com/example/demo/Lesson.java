package com.example.project.model;

public class Lesson {
    private Integer id;
    private String name;
    private String teacher;
    private String day;
    private String time;

    public Lesson() {}

    public Lesson(Integer id, String name, String teacher, String day, String time) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
        this.day = day;
        this.time = time;
    }

    // Гетери та сетери
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
