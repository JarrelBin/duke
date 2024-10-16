package alice.command;

import alice.storage.Storage;
import alice.task.TaskList;
import alice.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static alice.command.AddCommand.buildFormatter;

public class ListCommand extends Command {
    private LocalDate dateQuery;

    public ListCommand(String dateQuery) {
        this.dateQuery = LocalDate.parse(dateQuery, buildFormatter());
    }

    public ListCommand() {

    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (dateQuery != null){
            System.out.println("Here are the tasks in the date "+ dateQuery.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +":");
            tasks.printTasks(dateQuery);
        }
        else {
            System.out.println("Here are the tasks in your list:");
            tasks.printTasks();
        }
    }
}
