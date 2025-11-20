package tables;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Orders {
    private SimpleIntegerProperty _orderId;
    public Integer getOrderId() { return _orderId.get(); }
    public void setOrderId(Integer value) { _orderId.set(value); }

    private SimpleIntegerProperty _customerId;
    public Integer getCustomerId() { return _customerId.get(); }
    public void setCustomerId(Integer value) { _customerId.set(value); }

    private SimpleDoubleProperty _total;
    public Double getTotal() { return _total.get(); }
    public void setTotal(Double value) { _total.set(value); }

    private SimpleStringProperty _status;
    public String getStatus() { return _status.get(); }
    public void setStatus(String value) { _status.set(value); }

    public Orders(Integer orderId, Integer customerId, Double total, String status) {
        this._orderId = new SimpleIntegerProperty(orderId);
        this._customerId = new SimpleIntegerProperty(customerId);
        this._total = new SimpleDoubleProperty(total);
        this._status = new SimpleStringProperty(status);
    }
}
