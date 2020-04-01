package team4.personaldietary.bean;

import java.util.Objects;

public class Type {

    private int typeId;
    private String typeName;

    public Type() {this("");}

    public Type(String typeName) {
        this.setTypeId(-1);
        this.setTypeName(typeName);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Type type = (Type) o;
        return typeId == type.typeId &&
                Objects.equals(typeName, type.typeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeId, typeName);
    }
}
