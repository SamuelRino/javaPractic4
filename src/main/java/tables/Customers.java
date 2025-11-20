package tables;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Customers {
    private SimpleIntegerProperty _id;
    public Integer getCustomerId() {return _id.get(); }
    public void setCustomerId(Integer value) {_id.set(value); }

    private SimpleStringProperty _name;
    public String getName() {return _name.get(); }
    public void setName(String value) {_name.set(value); }

    private SimpleStringProperty _phone;
    public String getPhone() {return _phone.get(); }
    public void setPhone(String value) {_phone.set(value); }

    private SimpleStringProperty _adress;
    public String getAdress() {return _adress.get(); }
    public void setAdress(String value) {_adress.set(value); }

    public Customers(Integer id, String name, String phone, String adress)
    {
        this._id = new SimpleIntegerProperty(id);
        this._name = new SimpleStringProperty(name);
        this._phone = new SimpleStringProperty(phone);
        this._adress = new SimpleStringProperty(adress);
    }
}

