# java-gym-master
Repository for homework project.

# Сюда выпишу переваренное тз для удобства

CRM для управления записями и расписаниями

Group - (String title, Age age, int duration) сущность группы с названием, делением на возраст и длительностью занятия
Coach - (String surname, String name, String middleName) сущность тренера с 3 переменными полного имени

TrainingSession - (Group group, Coach coach, DayOfWeek dayOfWeek, TimeOfDay timeOfDay) сущность занятия
TimeOfDay - (int hours, int minutes) сущность времени для тренировки

Timetable - HashMap<DayOfWeek, TreeMap<TimeOfDay, List<TrainingSession>>>

Age - enum с вариантами CHILD, ADULT
DayOfWeek - enum с вариантами MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY

# Требования:

* Получение всех тренировок, упорядоченных по времени начала, за конкретный день недели. O(1)
  (Скорее всего получение )
* Получение всех тренировок, начинающихся в конкретное время, за конкретный день недели. =< O(log(n))
* Добавление новой тренировки в расписание. =< O(log(n))
* Получение отсортированного списка тренеров

# Методы:

* getTrainingSessionsForDay
* getTrainingSessionsForDayAndTime
* getCountByCoaches

## Список задач:

1. Создать дев от мейна ✅
2. Придумать схему для хранения расписания ✅
3. Добавление элементов с сортировкой ✅
4. Получение отсортированного расписание ✅
5. Получение тренировок в день + время ✅
6. Получение кол-ва тренировок по тренеру ✅
7. Написать тесты на методы ✅