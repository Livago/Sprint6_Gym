package ru.yandex.practicum.gym;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.gym.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class TimetableTest {

    @Test
    void testGetTrainingSessionsForDaySingleSession() {
        Timetable timetable = new Timetable();

        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        TrainingSession trainingSession = new TrainingSession(group, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));

        timetable.addNewTrainingSession(trainingSession);

        TreeMap<TimeOfDay, List<TrainingSession>> sessionsForDay =
                timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY);

        Assertions.assertEquals(1, sessionsForDay.size());
        Assertions.assertEquals(List.of(trainingSession), sessionsForDay.get(new TimeOfDay(13, 0)));
    }

    @Test
    void testGetTrainingSessionsForDayMultipleSessionsSortedByTime() {
        Timetable timetable = new Timetable();

        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        Group groupAdult = new Group("Акробатика для взрослых", Age.ADULT, 90);
        Group groupChild = new Group("Акробатика для детей", Age.CHILD, 60);
        TrainingSession eveningTrainingSession = new TrainingSession(groupAdult, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(20, 0));
        TrainingSession afternoonTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(13, 0));

        timetable.addNewTrainingSession(eveningTrainingSession);
        timetable.addNewTrainingSession(afternoonTrainingSession);

        TreeMap<TimeOfDay, List<TrainingSession>> sessionsForDay =
                timetable.getTrainingSessionsForDay(DayOfWeek.THURSDAY);

        Assertions.assertEquals(List.of(new TimeOfDay(13, 0), new TimeOfDay(20, 0)),
                new ArrayList<>(sessionsForDay.keySet()));
    }

    @Test
    void testGetTrainingSessionsForDayWithoutSessionsReturnsNull() {
        Timetable timetable = new Timetable();

        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        TrainingSession trainingSession = new TrainingSession(group, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));

        timetable.addNewTrainingSession(trainingSession);

        Assertions.assertNull(timetable.getTrainingSessionsForDay(DayOfWeek.TUESDAY));
    }

    @Test
    void testGetTrainingSessionsForDayAndTimeSingleSession() {
        Timetable timetable = new Timetable();

        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        TrainingSession trainingSession = new TrainingSession(group, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));

        timetable.addNewTrainingSession(trainingSession);

        List<TrainingSession> sessions = timetable.getTrainingSessionsForDayAndTime(
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));

        Assertions.assertEquals(List.of(trainingSession), sessions);
    }

    @Test
    void testGetTrainingSessionsForDayAndTimeMultipleSessionsAtSameTime() {
        Timetable timetable = new Timetable();

        Coach firstCoach = new Coach("Васильев", "Николай", "Сергеевич");
        Coach secondCoach = new Coach("Кузнецова", "Дарья", "Александровна");
        Group firstGroup = new Group("Акробатика для детей", Age.CHILD, 60);
        Group secondGroup = new Group("Плавание для детей", Age.CHILD, 60);
        TimeOfDay time = new TimeOfDay(15, 0);
        TrainingSession firstTrainingSession = new TrainingSession(firstGroup, firstCoach,
                DayOfWeek.MONDAY, time);
        TrainingSession secondTrainingSession = new TrainingSession(secondGroup, secondCoach,
                DayOfWeek.MONDAY, time);

        timetable.addNewTrainingSession(firstTrainingSession);
        timetable.addNewTrainingSession(secondTrainingSession);

        List<TrainingSession> sessions = timetable.getTrainingSessionsForDayAndTime(DayOfWeek.MONDAY, time);

        Assertions.assertEquals(List.of(firstTrainingSession, secondTrainingSession), sessions);
    }

    @Test
    void testGetTrainingSessionsForDayAndTimeWithoutSessionsThrowsException() {
        Timetable timetable = new Timetable();

        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        TrainingSession trainingSession = new TrainingSession(group, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));

        timetable.addNewTrainingSession(trainingSession);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> timetable.getTrainingSessionsForDayAndTime(DayOfWeek.MONDAY, new TimeOfDay(14, 0)));
    }

    @Test
    void testGetCountByCoachesForEmptyTimetable() {
        Timetable timetable = new Timetable();

        HashMap<Coach, Integer> countByCoaches = timetable.getCountByCoaches();

        Assertions.assertTrue(countByCoaches.isEmpty());
    }

    @Test
    void testGetCountByCoachesForSingleCoach() {
        Timetable timetable = new Timetable();

        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        Group group = new Group("Акробатика", Age.CHILD, 60);

        timetable.addNewTrainingSession(new TrainingSession(group, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0)));
        timetable.addNewTrainingSession(new TrainingSession(group, coach,
                DayOfWeek.WEDNESDAY, new TimeOfDay(13, 0)));
        timetable.addNewTrainingSession(new TrainingSession(group, coach,
                DayOfWeek.FRIDAY, new TimeOfDay(13, 0)));

        HashMap<Coach, Integer> countByCoaches = timetable.getCountByCoaches();

        Assertions.assertEquals(1, countByCoaches.size());
        Assertions.assertEquals(3, countByCoaches.get(coach));
    }

    @Test
    void testGetCountByCoachesSortedBySessionsCountDescending() {
        Timetable timetable = new Timetable();

        Coach firstCoach = new Coach("Васильев", "Николай", "Сергеевич");
        Coach secondCoach = new Coach("Кузнецова", "Дарья", "Александровна");
        Coach thirdCoach = new Coach("Романов", "Сергей", "Петрович");
        Group group = new Group("Акробатика", Age.CHILD, 60);

        timetable.addNewTrainingSession(new TrainingSession(group, firstCoach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0)));
        timetable.addNewTrainingSession(new TrainingSession(group, firstCoach,
                DayOfWeek.WEDNESDAY, new TimeOfDay(13, 0)));
        timetable.addNewTrainingSession(new TrainingSession(group, firstCoach,
                DayOfWeek.FRIDAY, new TimeOfDay(13, 0)));
        timetable.addNewTrainingSession(new TrainingSession(group, secondCoach,
                DayOfWeek.TUESDAY, new TimeOfDay(14, 0)));
        timetable.addNewTrainingSession(new TrainingSession(group, secondCoach,
                DayOfWeek.THURSDAY, new TimeOfDay(14, 0)));
        timetable.addNewTrainingSession(new TrainingSession(group, thirdCoach,
                DayOfWeek.SATURDAY, new TimeOfDay(15, 0)));

        HashMap<Coach, Integer> countByCoaches = timetable.getCountByCoaches();

        Assertions.assertEquals(List.of(3, 2, 1), new ArrayList<>(countByCoaches.values()));
        Assertions.assertEquals(3, countByCoaches.get(firstCoach));
        Assertions.assertEquals(2, countByCoaches.get(secondCoach));
        Assertions.assertEquals(1, countByCoaches.get(thirdCoach));
    }
}
