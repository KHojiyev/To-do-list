package module3.lesson6_AlternativeLibraries.work2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ToDoTable {
    public static List<UserToDo> userToDos = new ArrayList<>();
    public static int newTodoId = 201;
    public static List<UserToDo> currentUserToDo = new ArrayList<>();
    public static int userId;

    public static void main(String[] args) throws FileNotFoundException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\user\\IdeaProjects\\JavaWithMaven\\src\\main\\java\\module3\\lesson6_AlternativeLibraries\\work2\\todos.json"));
        List<UserToDo> temp = Arrays.asList(gson.fromJson(bufferedReader, UserToDo[].class));
        userToDos.addAll(temp);
        System.out.println("==Welcome ToDo List==");
        System.out.print("Enter userId: ");
        Scanner scanner = new Scanner(System.in);
        userId = scanner.nextInt();
        while (true) {
            currentUserToDoSort(userId);
            showAllToDos(userId);
            scanner = new Scanner(System.in);
            System.out.println("\n[ n.Add New e.Edit r.Remove s.Search by title x.Exit ]");
            String command = scanner.nextLine();
            switch (command) {
                case "n":
                    addNewToDo();
                    break;
                case "e":
                    editToDO();
                    break;
                case "r":
                    removeToDO();
                    break;
                case "s":
                    searchByTitle();
                    break;
                case "x":
                    System.out.println("\n==bye==");
                    return;
                default:
                    break;
            }
        }

    }

    private static void currentUserToDoSort(int userId) {
        for (UserToDo userToDo : userToDos) {
            if (userToDo.getUserId() == userId) {
                currentUserToDo.add(new UserToDo(userToDo.getUserId(), userToDo.getId(), userToDo.getTitle(), userToDo.isCompleted()));
            }
        }

    }

    private static void searchByTitle() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Title: ");
        String searchByTitle = scanner.nextLine();
        boolean notFound = true;
        for (UserToDo userToDo : currentUserToDo) {
            if (userToDo.getTitle().startsWith(searchByTitle)) {
                notFound = false;
                System.out.println("Id: " + userToDo.getId() + " || Title: " + userToDo.getTitle() + " || Done: " + userToDo.isCompleted());
            }
        }
        if (notFound) {
            System.out.println("Such title not found");
        }
        currentUserToDo.removeAll(currentUserToDo);
    }

    private static void removeToDO() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("id: ");
        int id = scanner.nextInt();
        for (UserToDo userDo : currentUserToDo) {
            if (userDo.getId() == id) {
                System.out.println("id: " + userDo.getId() + " || Title: " + userDo.getTitle() + " || Done?: " + userDo.isCompleted());
                break;
            }
        }
        System.out.print("Do you remove? yes/no: ");
        scanner = new Scanner(System.in);
        String removeCommand = scanner.nextLine();
        switch (removeCommand) {
            case "yes":
                for (int i = 0; i < userToDos.size(); i++) {
                    if (userToDos.get(i).getId() == id) {
                        UserToDo remove = userToDos.remove(i);
                        currentUserToDo.removeAll(currentUserToDo);
                    }
                }
                System.out.println("==Done!!==");
                break;
            case "no":
                currentUserToDo.removeAll(currentUserToDo);
                System.out.println("==Done!!==");
                break;
            default:
                currentUserToDo.removeAll(currentUserToDo);
                break;
        }

    }

    private static void editToDO() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("id: ");
        int id = scanner.nextInt();
        while (true) {
            for (UserToDo userDo : currentUserToDo) {
                if (userDo.getId() == id) {
                    System.out.println("id: " + userDo.getId() + " || Title: " + userDo.getTitle() + " || Done?: " + userDo.isCompleted());
                    break;
                }
            }
            System.out.println("[ e.Edit Title d.Done? x.Exit ]");
            scanner = new Scanner(System.in);
            String editCommand = scanner.nextLine();
            switch (editCommand) {
                case "e":
                    System.out.print("New Title: ");
                    scanner = new Scanner(System.in);
                    String newTitle = scanner.nextLine();
                    for (UserToDo toDo : userToDos) {
                        if (toDo.getId() == id && toDo.getUserId() == userId) {
                            toDo.setTitle(newTitle);
                        }
                    }
                    for (UserToDo toDo : currentUserToDo) {
                        if (toDo.getId() == id && toDo.getUserId() == userId) {
                            toDo.setTitle(newTitle);
                        }
                    }
                    System.out.println("==Done!!==");
                    break;
                case "d":
                    for (UserToDo toDo : userToDos) {
                        if (toDo.getId() == id && toDo.getUserId() == userId) {
                            toDo.setCompleted(!toDo.isCompleted());
                        }
                    }
                    for (UserToDo toDo : currentUserToDo) {
                        if (toDo.getId() == id && toDo.getUserId() == userId) {
                            toDo.setCompleted(!toDo.isCompleted());
                        }
                    }
                    System.out.println("==Done!!==");
                    break;
                case "x":
                    return;
                default:
                    break;
            }
        }
    }

    private static void addNewToDo() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Title: ");
        String newTitle = scanner.nextLine();
        userToDos.removeAll(userToDos);
        userToDos.add(new UserToDo(userId, newTodoId, newTitle, false));
        newTodoId++;
        System.out.println("\n==Done!!==\n");

    }

    private static void showAllToDos(int userId) {
        System.out.println("=================================================");
        for (UserToDo userToDo : currentUserToDo) {
            if (userId == userToDo.getUserId() && !userToDo.isCompleted()) {
                System.out.println("id: " + userToDo.getId() + " || Title: " + userToDo.getTitle() + " || done?: " + userToDo.isCompleted());
            }
        }
        System.out.println("-------------------------------------------------");
        for (UserToDo userToDo : currentUserToDo) {
            if (userId == userToDo.getUserId() && userToDo.isCompleted()) {
                System.out.println("id: " + userToDo.getId() + " || Title: " + userToDo.getTitle() + " || done?: " + userToDo.isCompleted());
            }
        }
        System.out.println("=================================================");

    }
}
