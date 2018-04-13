package inc.morsecode.data;

public class StructuredDataObject {

    final private int id;
    final private String name;
    final private String description;

    public StructuredDataObject(int id, String name, String description) {
        this.id= id;
        this.name= name;
        this.description= description;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }

}
