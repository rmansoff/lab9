package com.example.project.controller;

import com.example.project.model.Lesson;
import com.example.project.service.LessonService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {
    private final LessonService lessonService = new LessonService();

    @GetMapping
    public List<Lesson> getAllLessons() throws SQLException {
        return lessonService.getAllLessons();
    }

    @PostMapping
    public void addLesson(@RequestBody Lesson lesson) throws SQLException {
        lessonService.addLesson(lesson);
    }

    @PutMapping
    public void updateLesson(@RequestBody Lesson lesson) throws SQLException {
        lessonService.updateLesson(lesson);
    }

    @DeleteMapping("/{id}")
    public void deleteLesson(@PathVariable int id) throws SQLException {
        lessonService.deleteLesson(id);
    }
}

@RestController
@RequestMapping("/api/schedule")
class ScheduleController {
    private final LessonService lessonService = new LessonService();

    @GetMapping("/{day}")
    public List<Lesson> getScheduleForDay(@PathVariable String day) throws SQLException {
        return lessonService.getScheduleForDay(day);
    }

    @GetMapping
    public List<List<Lesson>> getScheduleForWeek() throws SQLException {
        return lessonService.getScheduleForWeek();
    }
}
