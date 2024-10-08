import java.util.ArrayList;
import java.util.Scanner;

public class Alice {
    private static ArrayList<Task> Tasks = new ArrayList<>();

    public static String separator = "____________________________________________________________\n";

    public static void printTasks(){
        for (int i = 0; i < Tasks.size(); i++){
            System.out.println((i+1) + "." + Tasks.get(i).toString());
        }
    }

    public static void separatorMessage(String message){
        System.out.print(separator);
        System.out.println(message);
        System.out.print(separator);
    }


    public static void main(String[] args) {

        String intro =
                "____________________________________________________________\n" +
                "Hello! I'm Alice, here to make you magically organized!\n" +
                        "How may I help you?\n" +
                "____________________________________________________________";

        String ending =
                "____________________________________________________________\n" +
                "Bye. Back to my wonderland!\n" +
                "____________________________________________________________";

        System.out.println(intro);
        String line;
        Scanner input = new Scanner(System.in);
        line = input.nextLine();
        String[] instruction = line.split(" ");
        while(!instruction[0].equals("bye")){
            String body = "";
            String param = "";
            String param2 = "";
            int i = 2;
            switch (instruction[0]) {
                case "list":
                    System.out.print(separator);
                    System.out.println("Here are the tasks in your list:");
                    printTasks();
                    System.out.print(separator);
                    break;
                case "delete":
                    if (instruction.length == 1){
                        separatorMessage("Do specify the task number to delete!");
                        break;
                    }
                    try {
                        String task = Tasks.get(Integer.parseInt(instruction[1]) - 1).toString();
                        System.out.print(separator);
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(task);
                        Tasks.remove(Integer.parseInt(instruction[1]) - 1);
                        System.out.print("Now you have " + Tasks.size() + " in the list." + "\n" + separator);

                    }catch (IndexOutOfBoundsException e){
                        separatorMessage("Task number not found!");
                        break;
                    }
                    break;
                case "mark":
                    if (instruction.length == 1){
                        separatorMessage("Do specify the task number!");
                        break;
                    }

                    System.out.print(separator);
                    Task task_mark = Tasks.get(Integer.parseInt(instruction[1]) - 1);
                    task_mark.setDone(true);
                    System.out.println("Nice! I've marked this task as done:)");
                    System.out.println(task_mark.toString());
                    System.out.print(separator);
                    break;
                case "unmark":
                    if (instruction.length == 1){
                        separatorMessage("Do specify the task number!");
                        break;
                    }

                    System.out.print(separator);
                    Task task_unmark = Tasks.get(Integer.parseInt(instruction[1]) - 1);
                    task_unmark.setDone(false);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(task_unmark.toString());
                    System.out.print(separator);
                    break;
                case "todo":
                    if (instruction.length == 1){
                        separatorMessage("The description of todo cannot be empty!");
                        break;
                    }
                    body += instruction[1];
                    while(i < instruction.length) {
                        body += " " + instruction[i];
                        i++;
                    }
                    Tasks.add(new Todo(body));
                    System.out.print(separator);
                    System.out.println("Got it, I've added this task: \n" + Tasks.getLast().toString());
                    System.out.print("Now you have " + Tasks.size() + " in the list." + "\n" + separator);

                    break;
                case "deadline":
                    if (instruction.length == 1){
                        separatorMessage("The description of deadline cannot be empty!");
                        break;
                    }
                    body += instruction[1];
                    try {
                        while (!instruction[i].equals("/by")) {
                            body += " " + instruction[i];
                            i++;
                        }
                    } catch (ArrayIndexOutOfBoundsException e){
                        separatorMessage("Do include the /by flag!");
                        break;
                    }
                    i++;
                    try {
                        param += instruction[i];
                    } catch (ArrayIndexOutOfBoundsException e){
                        separatorMessage("Do include the day after /by!");
                        break;
                    }

                    i++;
                    while(i < instruction.length) {
                        param += " " + instruction[i];
                        i++;
                    }
                    Tasks.add(new Deadline(body, param));
                    System.out.print(separator);
                    System.out.println("Got it, I've added this task: \n" + Tasks.getLast().toString());
                    System.out.print("Now you have " + Tasks.size() + " in the list." + "\n" + separator);

                    break;
                case "event":
                    body += instruction[1];
                    try {
                        while (!instruction[i].equals("/from")) {
                            body += " " + instruction[i];
                            i++;
                        }
                    }catch (ArrayIndexOutOfBoundsException e){
                        separatorMessage("Do include the /from flag!");
                        break;
                    }
                    i++;
                    try {
                        param += instruction[i];
                    }catch (ArrayIndexOutOfBoundsException e){
                        separatorMessage("Do include the day after /from!");
                        break;
                    }
                    i++;
                    try {
                        while (!instruction[i].equals("/to")) {
                            param += " " + instruction[i];
                            i++;
                        }
                    }catch (ArrayIndexOutOfBoundsException e){
                        separatorMessage("Do include the /to flag!");
                        break;
                    }
                    i++;
                    try {
                        param2 += instruction[i];
                    }catch (ArrayIndexOutOfBoundsException e){
                        separatorMessage("Do include the day after /to!");
                        break;
                    }
                    i++;
                    while(i < instruction.length) {
                        param2 += " " + instruction[i];
                        i++;
                    }
                    Tasks.add(new Event(body, param, param2));
                    System.out.print(separator);
                    System.out.println("Got it, I've added this task: \n" + Tasks.getLast().toString());
                    System.out.print("Now you have " + Tasks.size() + " in the list." + "\n" + separator);

                    break;
                default:
                    separatorMessage("Please put an instruction I can understand :(");
            }

            input = new Scanner(System.in);
            line = input.nextLine();
            instruction = line.split(" ");
        }
        System.out.println(ending);
    }
}
