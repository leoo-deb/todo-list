package todolist;

public class Todo {
    private String name;
    private String description;

    public Todo(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("Titulo: %s \nDescricao: %s \n", name, description);
    }
}
