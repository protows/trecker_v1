package trecker.v1;
import java.util.ArrayList;

public class Main2 {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        System.out.println("Пустые задачи: ");
        printAll(manager);

        Task practice = new Task("Практика", "Работа над трекером");
        Task lecture = new Task("Лекция", "Повторить наследование");

        manager.createTask(practice); // id - 1
        manager.createTask(lecture);

        Epic move = new Epic("Переезд", "Подготовить переезд");
        Epic study = new Epic("Учёба", "Изучить Java");

        manager.createEpic(move);
        manager.createEpic(study);

        Subtask boxes = new Subtask("Коробки", "Упаковать вещи", move.getTaskid());
        Subtask transport = new Subtask("Машина", "Заказать авто", move.getTaskid());
        Subtask oop = new Subtask("ООП", "Решить упражнения", study.getTaskid());

        manager.createSubtask(boxes);
        manager.createSubtask(transport);
        manager.createSubtask(oop);

        System.out.println("\nПосле создания:");
        printAll(manager);

       // System.out.println("Детей переезда: " + manager.getEpicSubtasks(move.getId()).size());
       // System.out.println("Детей учёбы: " + manager.getEpicSubtasks(study.getId()).size());

        Task updatedPractice = new Task(practice.getName(), practice.getDescription(), TaskStatus.IN_PROGRESS);
        //updatedPractice.setId(practice.getTaskid());
        manager.updateTask(updatedPractice);

        Subtask updatedBoxes = new Subtask(boxes.getName(), boxes.getDescription(), TaskStatus.DONE, boxes.getEpicId());
        updatedBoxes.setTaskid(boxes.getTaskid());
        manager.updateSubtask(updatedBoxes);

        Subtask updatedOop = new Subtask(oop.getName(), oop.getDescription(), TaskStatus.DONE, oop.getEpicId());
        updatedOop.setTaskid(oop.getTaskid());
        manager.updateSubtask(updatedOop);

        System.out.println("\nПосле первых обновлений:");
        printAll(manager);

        System.out.println("Переезд: " + manager.getEpicById(move.getTaskid()).getStatus());
        System.out.println("Учёба: " + manager.getEpicById(study.getTaskid()).getStatus());

        Subtask updatedTransport = new Subtask(transport.getName(), transport.getDescription(), TaskStatus.DONE, transport.getEpicId());
        updatedTransport.setTaskid(transport.getTaskid());
        manager.updateSubtask(updatedTransport);

        System.out.println("\nВсе шаги переезда выполнены: " + manager.getEpicById(move.getTaskid()).getStatus());

        Epic renamed = new Epic("Переезд в квартиру", "Уточнённый план", TaskStatus.NEW, new ArrayList<>());
        //renamed.setId(move.getTaskid());
        manager.updateEpic(renamed);

        System.out.println("После переименования: " + manager.getEpicById(move.getTaskid()));
        System.out.println("ID детей переезда: " + manager.getEpicById(move.getTaskid()).getAllIdsSubsTaksOfEpic());

        manager.deleteTaskById(lecture.getTaskid());
        manager.removeEpicById(study.getTaskid());

        System.out.println("\nПосле удаления:");
        printAll(manager);

        System.out.println("Удалённая лекция: " + manager.getTaskById(lecture.getTaskid()));
        System.out.println("Удалённая подзадача ООП: " + manager.getSubtaskById(oop.getTaskid()));

        System.out.println("Количество: " + manager.getAllTasks().size() + "/"
                + manager.getAllEpics().size() + "/" + manager.getAllSubtasks().size());

        manager.deleteAllSubtasks();
        System.out.println("После очистки подзадач:");
        printAll(manager);

        System.out.println("Пустой переезд: " + manager.getEpicById(move.getTaskid()).getStatus());
        System.out.println("Его ID детей: " + manager.getEpicById(move.getTaskid()).getAllIdsSubsTaksOfEpic());

        manager.removeAllEpics();
        System.out.println("После очистки эпиков:");
        printAll(manager);

        System.out.println("Удалённый переезд: " + manager.getEpicById(move.getTaskid()));

        System.out.println("Итог: " + manager.getAllTasks().size() + "/"
                + manager.getAllEpics().size() + "/" + manager.getAllSubtasks().size());

    }

    private static void printAll(TaskManager manager) {
        System.out.println("Задачи: " + manager.getAllTasks());
        System.out.println("Эпики: " + manager.getAllEpics());
        System.out.println("Подзадачи: " + manager.getAllSubtasks());
    }
}
