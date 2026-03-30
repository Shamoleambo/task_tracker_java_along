public class MyApp {

    public void main(String[] args) {
        TaskTracker taskTracker = new TaskTracker();
//        taskTracker.addTask("explore cave");
//        taskTracker.addTask("get some wood");
//        taskTracker.addTask("build a house");
//        taskTracker.addTask("find diamonds");


        taskTracker.markDone("317ab0ce-bb50-455c-8648-6ba4930eb562");
        taskTracker.quit();

        taskTracker.listTasks("Done");
    }
}
