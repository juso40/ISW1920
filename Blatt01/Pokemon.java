public class Pokemon{
    private String name;
    private Type type;
    private int number;
    static private int nextNumber = 1;

    public Pokemon(String newName, Type newType){
        this.name = newName;
        this.type = newType;
        this.number = nextNumber;
        nextNumber++;
    }

    // Das keyword "this" wird benutzt um auf das jeweilige Object zuzugreifen.
    // Da mehrere Objekte von der selben Klasse sein können, aber alle unterschiedliche
    // Werte besitzen, benutzen wir "this" um nicht die ganze Klasse anzusprechen.
    // Da wir nur das jeweilige Objekt ändern wollen benutzen wir in dem setter "this".
    public void setName(String newName){
        this.name = newName;
    }

    public void setType(Type newType){
        this.type = newType;
    }

    public String getName(){
        return this.name;
    }

    public Type getType(){
        return this.type;
    }

    public int getNumber(){
        return this.number;
    }

    public String toString(){
        String myAttrs = "Name: " + this.name;
        myAttrs += "\nType: " + String.valueOf(this.type);
        myAttrs += "\nNumber: " + String.valueOf(this.number);
        return myAttrs;
    }
}