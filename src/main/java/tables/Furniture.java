package tables;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Furniture {
    private SimpleIntegerProperty _furnitureId;
    public Integer getFurnitureId() { return _furnitureId.get(); }
    public void setFurnitureId(Integer value) { _furnitureId.set(value); }

    private SimpleStringProperty _name;
    public String getName() { return _name.get(); }
    public void setName(String value) { _name.set(value); }

    private SimpleDoubleProperty _price;
    public Double getPrice() { return _price.get(); }
    public void setPrice(Double value) { _price.set(value); }

    private SimpleIntegerProperty _categoryId;
    public Integer getCategoryId() { return _categoryId.get(); }
    public void setCategoryId(Integer value) { _categoryId.set(value); }

    public Furniture(Integer furnitureId, String name, Double price, Integer categoryId) {
        this._furnitureId = new SimpleIntegerProperty(furnitureId);
        this._name = new SimpleStringProperty(name);
        this._price = new SimpleDoubleProperty(price);
        this._categoryId = new SimpleIntegerProperty(categoryId);
    }
}
