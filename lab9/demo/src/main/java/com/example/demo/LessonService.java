package com.example.project.service;

import com.example.project.model.Lesson;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LessonService {
    private static final String DB_URL = "jdbc:sqlite:db.db";

    public List<Lesson> getAllLessons() throws SQLException {
        List<Lesson> lessons = new ArrayList<>();
        String query = "SELECT * FROM lessons";

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                lessons.add(new Lesson(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("teacher"),
                    resultSet.getString("day"),
                    resultSet.getString("time")
                ));
            }
        }
        return lessons;
    }

    public void addLesson(Lesson lesson) throws SQLException {
        String query = "INSERT INTO lessons (name, teacher, day, time) VALUES (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, lesson.getName());
            statement.setString(2, lesson.getTeacher());
            statement.setString(3, lesson.getDay());
            statement.setString(4, lesson.getTime());
            statement.executeUpdate();
        }
    }

    public void updateLesson(Lesson lesson) throws SQLException {
        String query = "UPDATE lessons SET name = ?, teacher = ?, day = ?, time = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, lesson.getName());
            statement.setString(2, lesson.getTeacher());
            statement.setString(3, lesson.getDay());
            statement.setString(4, lesson.getTime());
            statement.setInt(5, lesson.getId());
            statement.executeUpdate();
        }
    }

    public void deleteLesson(int id) throws SQLException {
        String query = "DELETE FROM lessons WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public List<Lesson> getScheduleForDay(String day) throws SQLException {
        List<Lesson> lessons = new ArrayList<>();
        String query = "SELECT * FROM lessons WHERE day = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, day);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                lessons.add(new Lesson(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("teacher"),
                    resultSet.getString("day"),
                    resultSet.getString("time")
                ));
            }
        }
        return lessons;
    }

    public List<List<Lesson>> getScheduleForWeek() throws SQLException {
        List<List<Lesson>> schedule = new ArrayList<>();
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};

        for (String day : days) {
            schedule.add(getScheduleForDay(day));
        }
        return schedule;
    }
}
