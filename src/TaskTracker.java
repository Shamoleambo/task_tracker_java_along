import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TaskTracker {

    //I don't know what to pass on to this Path.of() method, so I'm gonna leave it like this for now and make some tests
    private final Path FILE_PATH = Path.of("./");
    private List<Task> tasks;

    public TaskTracker() {
        this.tasks = loadTasks();
    }

    private List<Task> loadTasks() {
        List<Task> stored_tasks = new ArrayList<>();

        if (!Files.exists(FILE_PATH)) {
            return new ArrayList<>();
        }

        try {
            String jsonContent = Files.readString(FILE_PATH);
            String[] taskList = jsonContent.replace("[", "")
                    .replace("]", "")
                    .split("},");

            for (String taskJson : taskList) {
                if (!taskJson.endsWith("}")) {
                    taskJson = taskJson + "}";
                    stored_tasks.add(Task.fromJson(taskJson));
                } else {
                    stored_tasks.add(Task.fromJson(taskJson));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stored_tasks;
    }

    private void saveTasks() throws IOException {
        if (!Files.exists(FILE_PATH)) {
            Files.createFile(FILE_PATH);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(tasks.get(i).toJson());
            if (i < tasks.size() - 1) {
                sb.append(",\n");
            }
        }
        sb.append("\n]");

        String jsonContent = sb.toString();
        Files.writeString(FILE_PATH, jsonContent);
    }
}
