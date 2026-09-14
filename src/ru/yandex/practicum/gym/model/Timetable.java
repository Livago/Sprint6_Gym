package ru.yandex.practicum.gym.model;

import java.util.*;
import java.util.stream.Collectors;

public class Timetable {

    private HashMap<DayOfWeek, TreeMap<TimeOfDay, List<TrainingSession>>> timetable = new HashMap<DayOfWeek, TreeMap<TimeOfDay, List<TrainingSession>>>();
    private HashMap<Coach, Integer> coachesCounter = new HashMap<Coach, Integer>();

    public void addNewTrainingSession(TrainingSession trainingSession) {
        timetable.computeIfAbsent(trainingSession.getDayOfWeek(), d -> new TreeMap<>())
                .computeIfAbsent(trainingSession.getTimeOfDay(), d -> new ArrayList<>())
                .add(trainingSession);

        Coach currentCoach = trainingSession.getCoach();
        coachesCounter.put(currentCoach, coachesCounter.getOrDefault(currentCoach, 0) + 1);

        // Здесь мы сохраняем занятия:
        // computeIfAbsent (НЕ БЫЛО В КУРСЕ!) - получаем TreeMap по DayOfWeek или создаем новую
        // computeIfAbsent - получаем ArrayList или создаем новый
        // С помощью add кладем найденный/созданный на прошлом шаге ArrayList -> TreeMap -> HashMap

        // В TimeOfDay уже реализован Comparable<TimeOfDay>, поэтому TreeMap будет отсортирован при создании или добавлении
    }

    public TreeMap<TimeOfDay, List<TrainingSession>> getTrainingSessionsForDay(DayOfWeek dayOfWeek) {
        return timetable.get(dayOfWeek);

        // Так как просто получаем по ключу - O(1)
    }

    public List<TrainingSession> getTrainingSessionsForDayAndTime(DayOfWeek dayOfWeek, TimeOfDay timeOfDay) {
        TreeMap<TimeOfDay, List<TrainingSession>> sessionsForDay = timetable.get(dayOfWeek);

        if (sessionsForDay == null) {
        throw new IllegalArgumentException("В календаре нет занятий на этот день!");
        }

        List<TrainingSession> sessions = sessionsForDay.get(timeOfDay);

        if (sessions == null) {
        throw new IllegalArgumentException("В этот день нет занятий на это время!");
        }

        return timetable.get(dayOfWeek).get(timeOfDay);

        // В теории O(log n)
    }

    public HashMap<Coach, Integer> getCountByCoaches() {
        return coachesCounter.entrySet().stream()
                .sorted(Map.Entry.<Coach, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new));
        // Стримы еще не проходили, но откопал вот такое
    }
}
