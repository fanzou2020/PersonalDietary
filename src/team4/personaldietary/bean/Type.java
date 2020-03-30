package team4.personaldietary.bean;

public class Type {

    private int typeId;
    private String typeName;

    public Type() {
    }

    public Type(String type_name) {
        this.typeName = type_name;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
