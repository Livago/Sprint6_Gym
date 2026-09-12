package ru.yandex.practicum.gym;

import ru.yandex.practicum.gym.model.*;
import static ru.yandex.practicum.gym.model.DayOfWeek.*;

public class App {

    public static void main(String[] args) {

        Group groupActobatics = new Group("Акробатика для детей", Age.CHILD, 60);
        Group groupHeavy = new Group("Тяжелая алтетика", Age.ADULT, 60);
        Group groupLight = new Group("Легкая алтетика", Age.ADULT, 60);
        Group groupSwimming = new Group("Плаванье для детей", Age.CHILD, 60);
        Group groupDance = new Group("Танцы", Age.CHILD, 30);
        Coach coachNicolai = new Coach("Васильев", "Николай", "Сергеевич");
        Coach coachSergei = new Coach("Романов", "Сергей", "Петрович");
        Coach coachDaria = new Coach("Кузнецова", "Дарья", "Александровна");
        TrainingSession firstTrainingSession = new TrainingSession(groupActobatics, coachNicolai,
                MONDAY, new TimeOfDay(13, 0));
        TrainingSession secondTrainingSession = new TrainingSession(groupHeavy, coachSergei,
                MONDAY, new TimeOfDay(14, 0));
        TrainingSession secondAndHalfTrainingSession = new TrainingSession(groupDance, coachDaria,
                MONDAY, new TimeOfDay(14, 30));
        TrainingSession thirdTrainingSession = new TrainingSession(groupLight, coachSergei,
                MONDAY, new TimeOfDay(15, 0));
        TrainingSession third1TrainingSession = new TrainingSession(groupSwimming, coachDaria,
                MONDAY, new TimeOfDay(15, 0));
        TrainingSession fifthTrainingSession = new TrainingSession(groupSwimming, coachDaria,
                TUESDAY, new TimeOfDay(15, 0));
        TrainingSession sixthTrainingSession = new TrainingSession(groupDance, coachNicolai,
                TUESDAY, new TimeOfDay(15, 0));


        Timetable table = new Timetable();
        table.addNewTrainingSession(sixthTrainingSession);
        table.addNewTrainingSession(fifthTrainingSession);

        table.addNewTrainingSession(firstTrainingSession);
        table.addNewTrainingSession(secondAndHalfTrainingSession);
        table.addNewTrainingSession(secondTrainingSession);
        table.addNewTrainingSession(thirdTrainingSession);
        table.addNewTrainingSession(third1TrainingSession);


    }


}
