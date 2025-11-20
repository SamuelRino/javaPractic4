package tables;

import javafx.beans.property.SimpleIntegerProperty;

public class OrderItems {
    private SimpleIntegerProperty _itemId;
    public Integer getItemId() { return _itemId.get(); }
    public void setItemId(Integer value) { _itemId.set(value); }

    private SimpleIntegerProperty _orderId;
    public Integer getOrderId() { return _orderId.get(); }
    public void setOrderId(Integer value) { _orderId.set(value); }

    private SimpleIntegerProperty _furnitureId;
    public Integer getFurnitureId() { return _furnitureId.get(); }
    public void setFurnitureId(Integer value) { _furnitureId.set(value); }

    private SimpleIntegerProperty _count;
    public Integer getCount() { return _count.get(); }
    public void setCount(Integer value) { _count.set(value); }

    public OrderItems(Integer itemId, Integer orderId, Integer furnitureId, Integer count) {
        this._itemId = new SimpleIntegerProperty(itemId);
        this._orderId = new SimpleIntegerProperty(orderId);
        this._furnitureId = new SimpleIntegerProperty(furnitureId);
        this._count = new SimpleIntegerProperty(count);
    }
}
