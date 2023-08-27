package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskManagment {


    //Criar um metodo que recebe os mesmo argumentos que o dataSource mais o tasktype e
    //criar o task e manda para o ficheiro a cena boa é que assim os values ficam no
    //formato que é suposto.


    private List<Task> taskList;
    private final static Path filePath = Path.of("Tasks.txt");
    private Scanner scanner;

    public TaskManagment() {
        taskList = new ArrayList<>();
        readTasksFromFile();
    }

    public boolean createTask(String taskType, int nif, String... values) {
        return writeTaskToFile(TaskType.getTaskType(taskType), "Open",
                LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                nif,
                String.join(",", values));
    }

    public void printList() {
        if (taskList.isEmpty()) {
            System.out.println("No tasks to show");
            return;
        }
        taskList.forEach(task -> System.out.println(task.toString()));
    }

    private void readTasksFromFile() {
        try {
            scanner = new Scanner(filePath);
            while (scanner.hasNextLine()) {

                int taskID = getID(scanner.nextLine());
                String s = scanner.nextLine();
                String s2 = taskType(s);
                TaskType taskType = TaskType.getTaskType(s2);
                String taskStatus = taskStatus(scanner.nextLine());
                String taskDate = taskDate(scanner.nextLine());
                int nif = getNIF(scanner.nextLine());
                String taskInfo = taskInfo(scanner.nextLine());

                if (taskID == -1 || taskType == null || taskStatus == null ||
                        taskDate == null || nif == -1 || taskInfo == null) {
                    System.out.println("File is empty");
                    return;
                }

                taskList.add(new Task(taskID, taskType, taskStatus,
                        taskDate,
                        nif,
                        taskInfo));

                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }

            }

        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
            throw new RuntimeException(e);
        } finally {
            scanner.close();
        }
        taskList.sort(Task::compareTo);
    }

    private int getNewID() {
        return taskList.isEmpty() ? 1 : taskList.get(taskList.size() - 1).getTaskID() + 1;
    }


    /*create a method that returns a JTextArea with the next task to be done from the taskList
     * the next task to be done is the task at position 0 in the taskList*/
   /* public JTextArea getNextTask() {
        JTextArea textArea = new JTextArea();
        if(taskList.isEmpty()) {
            textArea.setText("No tasks to show");
            return textArea;
        }
        textArea.setText(taskList.get(0).toString());
        return textArea;
    }*/

    public Task getNextTask(int accessLevel) {
        switch (accessLevel) {
            case 2 -> {
                if (taskList.isEmpty()) {
                    return null;
                }
                for (Task task : taskList) {
                    if (task.getTaskStatus().equals("Open")) {
                        return task;
                    }
                }
            }
            case 1 -> {
                return getEmployeeManagerTask();
            }
            case 0 -> {
                return getEmployeeTask();
            }
        }
        return null;
    }


    private Task getEmployeeManagerTask() {
        for (Task task : taskList) {
            if (task.getTaskType() == TaskType.CUSTOMER_DEACTIVATION ||
                    task.getTaskType() == TaskType.EMPLOYEE_DEACTIVATION ||
                    task.getTaskType() == TaskType.VEHICLE_DEACTIVATION ||
                    task.getTaskType() == TaskType.INSURANCE_DEACTIVATION ||
                    task.getTaskType() == TaskType.VEHICLE_REGISTRATION ||
                    task.getTaskType() == TaskType.INSURANCE_REGISTRATION ||
                    task.getTaskType() == TaskType.CUSTOMER_REGISTRATION ||
                    task.getTaskType() == TaskType.CUSTOMER_UPDATE_ADDRESS ||
                    task.getTaskType() == TaskType.CUSTOMER_UPDATE_EMAIL ||
                    task.getTaskType() == TaskType.CUSTOMER_UPDATE_PASSWORD ||
                    task.getTaskType() == TaskType.EMPLOYEE_UPDATE_ACCESS_LEVEL ||

//            TaskType.EMPLOYEE_UPDATE_ADDRESS || TaskType.EMPLOYEE_UPDATE_EMAIL ||
//            TaskType.EMPLOYEE_UPDATE_PASSWORD

                    task.getTaskType() == TaskType.VEHICLE_UPDATE_COLOR ||
                    task.getTaskType() == TaskType.TICKET_UPDATE_PAY) {
                if (task.getTaskStatus().equals("Open")) {
                    return task;
                }
            }
        }
        return null;
    }

    private Task getEmployeeTask() {
        for (Task task : taskList) {
            if (task.getTaskType() == TaskType.CUSTOMER_DEACTIVATION ||
                    task.getTaskType() == TaskType.VEHICLE_DEACTIVATION ||
                    task.getTaskType() == TaskType.INSURANCE_DEACTIVATION ||
                    task.getTaskType() == TaskType.VEHICLE_REGISTRATION ||
                    task.getTaskType() == TaskType.INSURANCE_REGISTRATION ||
                    task.getTaskType() == TaskType.CUSTOMER_REGISTRATION ||
                    task.getTaskType() == TaskType.CUSTOMER_UPDATE_ADDRESS ||
                    task.getTaskType() == TaskType.CUSTOMER_UPDATE_EMAIL ||
                    task.getTaskType() == TaskType.CUSTOMER_UPDATE_PASSWORD ||

//            TaskType.EMPLOYEE_UPDATE_ADDRESS || TaskType.EMPLOYEE_UPDATE_EMAIL ||
//            TaskType.EMPLOYEE_UPDATE_PASSWORD

                    task.getTaskType() == TaskType.VEHICLE_UPDATE_COLOR ||
                    task.getTaskType() == TaskType.TICKET_UPDATE_PAY) {
                if (task.getTaskStatus().equals("Open")) {
                    return task;
                }
            }
        }
        return null;
    }


    public boolean writeTaskToFile(TaskType type, String status, String date, int nif, String info) {
        Task task = new Task(getNewID(), type, status, date, nif, info);
        boolean result = false;
        try {
            Files.write(filePath, task.toString().getBytes(), StandardOpenOption.CREATE,
                    StandardOpenOption.WRITE, StandardOpenOption.APPEND);
            result = true;
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            e.printStackTrace();
        }
        taskList.add(task);
        return result;
    }

    public boolean deleteTaskFromFile(int taskID) {
        boolean result = false;
        if (getTask(taskID) == null) {
            return false;
        } else {
            taskList.removeIf(task -> task.getTaskID() == taskID && task.getTaskStatus().equals("Closed"));
        }
        taskList.sort(Task::compareTo);
        for (int i = 0; i < taskList.size(); i++) {
            taskList.get(i).setTaskID(i + 1);
        }
        try {
            for (Task task : taskList) {
                Files.write(filePath, task.toString().getBytes(), StandardOpenOption.CREATE,
                        StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
            }
            result = true;
        } catch (IOException e) {
            System.out.println("Error deleting file: " + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public boolean updateTaskStatus(String status, int taskID) {
        Path filePath = Path.of("tasks.txt");
        boolean result = false;

        if (getTask(taskID) == null) {
            return false;
        } else {
            getTask(taskID).setTaskStatus(status);
        }

        taskList.sort(Task::compareTo);
        try {
            for (Task task : taskList) {
                Files.write(filePath, task.toString().getBytes(), StandardOpenOption.CREATE,
                        StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
            }
            result = true;
        } catch (IOException e) {
            System.out.println("Error updating file: " + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public Task getTask(int taskID) {
        for (Task task : taskList) {
            if (task.getTaskID() == taskID) {
                return task;
            }
        }
        System.out.println("Task not found");
        return null;
    }

    private int getID(String s) {
        if (s.isEmpty() || s.isBlank()) {
            return -1;
        }
        String[] svalues = s.split(":");
        return Integer.parseInt(svalues[1].trim());
    }

    private String taskType(String s) {
        if (s.isEmpty() || s.isBlank()) {
            return null;
        }
        String newS = s.replace("\n", "");
        String[] svalues = newS.split(":");
        String type = svalues[1];
        return type.trim();
    }

    private String taskStatus(String s) {
        if (s.isEmpty() || s.isBlank()) {
            return null;
        }
        String[] svalues = s.split(":");
        String status = svalues[1];
        return status.trim();
    }

    private String taskDate(String s) {
        if (s.isEmpty() || s.isBlank()) {
            return null;
        }
        String[] svalues = s.split(":");
        java.sql.Date date = java.sql.Date.valueOf(svalues[1].trim());
        return date.toString();
    }

    private int getNIF(String s) {
        if (s.isEmpty() || s.isBlank()) {
            return -1;
        }
        String[] svalues = s.split(":");
        return Integer.parseInt(svalues[1].trim());
    }

    private String taskInfo(String s) {
        if (s.isEmpty() || s.isBlank()) {
            return null;
        }
        String[] svalues = s.split(":");
        return svalues[1].trim();
    }

    public static void main(String[] args) {
        TaskManagment tk = new TaskManagment();
        String file = "Type: Customer Registration " + "\n";
        System.out.println(TaskType.getTaskType(tk.taskType(file)));
    }
}
