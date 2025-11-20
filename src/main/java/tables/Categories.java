package tables;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Categories {
    private SimpleIntegerProperty _categoryId;
    public Integer getCategoryId() { return _categoryId.get(); }
    public void setCategoryId(Integer value) { _categoryId.set(value); }

    private SimpleStringProperty _name;
    public String getName() { return _name.get(); }
    public void setName(String value) { _name.set(value); }

    public Categories(Integer categoryId, String name) {
        this._categoryId = new SimpleIntegerProperty(categoryId);
        this._name = new SimpleStringProperty(name);
    }
}
