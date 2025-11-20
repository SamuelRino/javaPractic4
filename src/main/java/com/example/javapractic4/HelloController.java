package com.example.javapractic4;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.AnchorPane;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;

import tables.Customers;
import tables.Orders;
import tables.Categories;
import tables.Furniture;
import tables.OrderItems;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class HelloController {
    // Таблица Customers
    @FXML
    private TableView<Customers> CustomersTable;
    @FXML
    private TableColumn<Customers, Integer> CustomerIdColumn;
    @FXML
    private TableColumn<Customers, String> CustomerNameColumn;
    @FXML
    private TableColumn<Customers, String> CustomerPhoneColumn;
    @FXML
    private TableColumn<Customers, String> CustomerAddressColumn;

    // Таблица Orders
    @FXML
    private TableView<Orders> OrdersTable;
    @FXML
    private TableColumn<Orders, Integer> OrderIdColumn;
    @FXML
    private TableColumn<Orders, Integer> OrderCustomerIdColumn;
    @FXML
    private TableColumn<Orders, Double> OrderTotalColumn;
    @FXML
    private TableColumn<Orders, String> OrderStatusColumn;

    // Таблица Categories
    @FXML
    private TableView<Categories> CategoriesTable;
    @FXML
    private TableColumn<Categories, Integer> CategoryIdColumn;
    @FXML
    private TableColumn<Categories, String> CategoryNameColumn;

    // Таблица Furniture
    @FXML
    private TableView<Furniture> FurnitureTable;
    @FXML
    private TableColumn<Furniture, Integer> FurnitureIdColumn;
    @FXML
    private TableColumn<Furniture, String> FurnitureNameColumn;
    @FXML
    private TableColumn<Furniture, Double> FurniturePriceColumn;
    @FXML
    private TableColumn<Furniture, Integer> FurnitureCategoryIdColumn;

    // Таблица Order_items
    @FXML
    private TableView<OrderItems> OrderItemsTable;
    @FXML
    private TableColumn<OrderItems, Integer> OrderItemIdColumn;
    @FXML
    private TableColumn<OrderItems, Integer> OrderItemOrderIdColumn;
    @FXML
    private TableColumn<OrderItems, Integer> OrderItemFurnitureIdColumn;
    @FXML
    private TableColumn<OrderItems, Integer> OrderItemCountColumn;

    // Списки данных
    List<Customers> customersList;
    List<Orders> ordersList;
    List<Categories> categoriesList;
    List<Furniture> furnitureList;
    List<OrderItems> orderItemsList;

    DbConnector db = new DbConnector();

    public void initialize() throws SQLException, ClassNotFoundException {
        db.Conn();
        loadAllTables();
    }

    private void loadAllTables() throws SQLException, ClassNotFoundException {
        // Загрузка Customers
        ObservableList<Customers> customersData = FXCollections.observableArrayList();
        customersList = (List<Customers>) db.ReadDB("Customers");
        customersData.addAll(customersList);
        CustomerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        CustomerNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        CustomerPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        CustomerAddressColumn.setCellValueFactory(new PropertyValueFactory<>("adress"));
        CustomersTable.setItems(customersData);

        // Загрузка Orders
        ObservableList<Orders> ordersData = FXCollections.observableArrayList();
        ordersList = (List<Orders>) db.ReadDB("Orders");
        ordersData.addAll(ordersList);
        OrderIdColumn.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        OrderCustomerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        OrderTotalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
        OrderStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        OrdersTable.setItems(ordersData);

        // Загрузка Categories
        ObservableList<Categories> categoriesData = FXCollections.observableArrayList();
        categoriesList = (List<Categories>) db.ReadDB("Categories");
        categoriesData.addAll(categoriesList);
        CategoryIdColumn.setCellValueFactory(new PropertyValueFactory<>("categoryId"));
        CategoryNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        CategoriesTable.setItems(categoriesData);

        // Загрузка Furniture
        ObservableList<Furniture> furnitureData = FXCollections.observableArrayList();
        furnitureList = (List<Furniture>) db.ReadDB("Furniture");
        furnitureData.addAll(furnitureList);
        FurnitureIdColumn.setCellValueFactory(new PropertyValueFactory<>("furnitureId"));
        FurnitureNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        FurniturePriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        FurnitureCategoryIdColumn.setCellValueFactory(new PropertyValueFactory<>("categoryId"));
        FurnitureTable.setItems(furnitureData);

        // Загрузка Order_items
        ObservableList<OrderItems> orderItemsData = FXCollections.observableArrayList();
        orderItemsList = (List<OrderItems>) db.ReadDB("Order_items");
        orderItemsData.addAll(orderItemsList);
        OrderItemIdColumn.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        OrderItemOrderIdColumn.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        OrderItemFurnitureIdColumn.setCellValueFactory(new PropertyValueFactory<>("furnitureId"));
        OrderItemCountColumn.setCellValueFactory(new PropertyValueFactory<>("count"));
        OrderItemsTable.setItems(orderItemsData);
    }

    // Общие элементы интерфейса для всех таблиц
    @FXML
    GridPane MainView;

    @FXML AnchorPane CustomerForm;
    @FXML AnchorPane OrderForm;
    @FXML AnchorPane CategoryForm;
    @FXML AnchorPane FurnitureForm;
    @FXML AnchorPane OrderItemForm;

    // Поля для Customers
    @FXML TextField customerIdField;
    @FXML TextField customerNameField;
    @FXML TextField customerPhoneField;
    @FXML TextField customerAddressField;

    // Поля для Orders
    @FXML TextField orderIdField;
    @FXML TextField orderCustomerIdField;
    @FXML TextField orderTotalField;
    @FXML TextField orderStatusField;

    // Поля для Categories
    @FXML TextField categoryIdField;
    @FXML TextField categoryNameField;

    // Поля для Furniture
    @FXML TextField furnitureIdField;
    @FXML TextField furnitureNameField;
    @FXML TextField furniturePriceField;
    @FXML TextField furnitureCategoryIdField;

    // Поля для OrderItems
    @FXML TextField orderItemIdField;
    @FXML TextField orderItemOrderIdField;
    @FXML TextField orderItemFurnitureIdField;
    @FXML TextField orderItemCountField;


    // Методы для показа/скрытия форм
    private void showCustomerForm() {
        MainView.setVisible(false);
        CustomerForm.setVisible(true);
    }

    private void hideCustomerForm() {
        MainView.setVisible(true);
        CustomerForm.setVisible(false);
    }

    private void showOrderForm() {
        MainView.setVisible(false);
        OrderForm.setVisible(true);
    }

    private void hideOrderForm() {
        MainView.setVisible(true);
        OrderForm.setVisible(false);
    }

    private void showCategoryForm() {
        MainView.setVisible(false);
        CategoryForm.setVisible(true);
    }

    private void hideCategoryForm() {
        MainView.setVisible(true);
        CategoryForm.setVisible(false);
    }

    private void showFurnitureForm() {
        MainView.setVisible(false);
        FurnitureForm.setVisible(true);
    }

    private void hideFurnitureForm() {
        MainView.setVisible(true);
        FurnitureForm.setVisible(false);
    }

    private void showOrderItemForm() {
        MainView.setVisible(false);
        OrderItemForm.setVisible(true);
    }

    private void hideOrderItemForm() {
        MainView.setVisible(true);
        OrderItemForm.setVisible(false);
    }


    // Методы для Customers
    @FXML
    protected void onAddCustomer() throws SQLException, ClassNotFoundException {
        showCustomerForm();
        if (!customersList.isEmpty()) {
            Customers lastCustomer = customersList.get(customersList.size() - 1);
            customerIdField.setText(String.valueOf(lastCustomer.getCustomerId() + 1));
        } else {
            customerIdField.setText("1");
        }
        clearCustomerFields();
    }

    @FXML
    protected void onChangeCustomer() throws SQLException, ClassNotFoundException {
        Customers customer = CustomersTable.getSelectionModel().getSelectedItem();
        if (customer != null) {
            showCustomerForm();
            customerIdField.setText(String.valueOf(customer.getCustomerId()));
            customerNameField.setText(customer.getName());
            customerPhoneField.setText(customer.getPhone());
            customerAddressField.setText(customer.getAdress());
        } else {
            showErrorAlert("Ошибка", "Выберите клиента для изменения");
        }
    }

    @FXML
    protected void onDeleteCustomer() throws SQLException, ClassNotFoundException {
        Customers customer = CustomersTable.getSelectionModel().getSelectedItem();
        if (customer != null) {
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Подтверждение удаления");
            confirmAlert.setHeaderText("Удаление клиента");
            confirmAlert.setContentText("Вы уверены, что хотите удалить клиента: " + customer.getName() + "?");

            Optional<ButtonType> result = confirmAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    db.DelDB(customer, "Customers");
                    loadAllTables();
                } catch (SQLException e) {
                    // Проверяем, является ли ошибка нарушением внешнего ключа
                    if (e.getMessage().contains("foreign key") || e.getMessage().contains("FOREIGN KEY")) {
                        showErrorAlert("Ошибка удаления",
                                "Нельзя удалить клиента " + customer.getName() + "!\n" +
                                        "Этот клиент связан с существующими заказами.\n" +
                                        "Сначала удалите все заказы этого клиента.");
                    } else {
                        showErrorAlert("Ошибка базы данных", "Не удалось удалить клиента: " + e.getMessage());
                    }
                }
            }
        } else {
            showErrorAlert("Ошибка", "Выберите клиента для удаления");
        }
    }

// === МЕТОДЫ ДЛЯ ФОРМ (вызываются из кнопок в формах) ===

    @FXML
    protected void saveCustomer() throws SQLException, ClassNotFoundException {
        try {
            Integer id = Integer.valueOf(customerIdField.getText());
            String name = customerNameField.getText();
            String phone = customerPhoneField.getText();
            String address = customerAddressField.getText();

            if (name == null || name.trim().isEmpty()) {
                showErrorAlert("Ошибка ввода", "ФИО не может быть пустым");
                return;
            }

            Customers customer = new Customers(id, name.trim(), phone, address);

            // Определяем, это добавление или изменение
            boolean isNew = customersList.stream().noneMatch(c -> c.getCustomerId().equals(id));

            if (isNew) {
                db.WriteDB(customer, "Customers");
            } else {
                db.UpdateDB(customer, "Customers");
            }

            hideCustomerForm();
            loadAllTables();

        } catch (NumberFormatException e) {
            showErrorAlert("Ошибка ввода", "Проверьте правильность введенных данных");
        }
    }

    @FXML
    protected void cancelCustomer() {
        hideCustomerForm();
    }



    private void clearCustomerFields() {
        customerNameField.clear();
        customerPhoneField.clear();
        customerAddressField.clear();
    }

    // Методы для Orders
    @FXML
    protected void onAddOrder() throws SQLException, ClassNotFoundException {
        showOrderForm();
        if (!ordersList.isEmpty()) {
            Orders lastOrder = ordersList.get(ordersList.size() - 1);
            orderIdField.setText(String.valueOf(lastOrder.getOrderId() + 1));
        } else {
            orderIdField.setText("1");
        }
        clearOrderFields();
    }

    @FXML
    protected void onChangeOrder() throws SQLException, ClassNotFoundException {
        Orders order = OrdersTable.getSelectionModel().getSelectedItem();
        if (order != null) {
            showOrderForm();
            orderIdField.setText(String.valueOf(order.getOrderId()));
            orderCustomerIdField.setText(String.valueOf(order.getCustomerId()));
            orderTotalField.setText(String.valueOf(order.getTotal()));
            orderStatusField.setText(order.getStatus());
        } else {
            showErrorAlert("Ошибка", "Выберите заказ для изменения");
        }
    }

    @FXML
    protected void onDeleteOrder() throws SQLException, ClassNotFoundException {
        Orders order = OrdersTable.getSelectionModel().getSelectedItem();
        if (order != null) {
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Подтверждение удаления");
            confirmAlert.setHeaderText("Удаление заказа");
            confirmAlert.setContentText("Вы уверены, что хотите удалить заказ ID: " + order.getOrderId() + "?");

            Optional<ButtonType> result = confirmAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    db.DelDB(order, "Orders");
                    loadAllTables();
                } catch (SQLException e) {
                    // Проверяем, является ли ошибка нарушением внешнего ключа
                    if (e.getMessage().contains("foreign key") || e.getMessage().contains("FOREIGN KEY")) {
                        showErrorAlert("Ошибка удаления",
                                "Нельзя удалить заказ ID: " + order.getOrderId() + "!\n" +
                                        "Этот заказ связан с существующими позициями заказов.\n" +
                                        "Сначала удалите все позиции этого заказа.");
                    } else {
                        showErrorAlert("Ошибка базы данных", "Не удалось удалить заказ: " + e.getMessage());
                    }
                }
            }
        } else {
            showErrorAlert("Ошибка", "Выберите заказ для удаления");
        }
    }

// === МЕТОДЫ ДЛЯ ФОРМ (вызываются из кнопок в формах) ===

    @FXML
    protected void saveOrder() throws SQLException, ClassNotFoundException {
        try {
            Integer id = Integer.valueOf(orderIdField.getText());
            Integer customerId = Integer.valueOf(orderCustomerIdField.getText());
            Double total = Double.valueOf(orderTotalField.getText());
            String status = orderStatusField.getText();

            if (customerId <= 0) {
                showErrorAlert("Ошибка ввода", "ID клиента должен быть положительным числом");
                return;
            }

            if (total < 0) {
                showErrorAlert("Ошибка ввода", "Сумма не может быть отрицательной");
                return;
            }

            if (status == null || status.trim().isEmpty()) {
                showErrorAlert("Ошибка ввода", "Статус не может быть пустым");
                return;
            }

            Orders order = new Orders(id, customerId, total, status.trim());

            // Определяем, это добавление или изменение
            boolean isNew = ordersList.stream().noneMatch(o -> o.getOrderId().equals(id));

            try {
                if (isNew) {
                    db.WriteDB(order, "Orders");
                } else {
                    db.UpdateDB(order, "Orders");
                }

                hideOrderForm();
                loadAllTables();

            } catch (SQLException e) {
                // Проверяем, является ли ошибка нарушением внешнего ключа
                if (e.getMessage().contains("foreign key") || e.getMessage().contains("FOREIGN KEY")) {
                    showErrorAlert("Ошибка внешнего ключа",
                            "Клиент с ID " + customerId + " не существует.\n" +
                                    "Пожалуйста, выберите существующего клиента.");
                } else {
                    showErrorAlert("Ошибка базы данных", "Не удалось сохранить заказ: " + e.getMessage());
                }
            }

        } catch (NumberFormatException e) {
            showErrorAlert("Ошибка ввода", "Проверьте правильность введенных числовых значений");
        }
    }

    @FXML
    protected void cancelOrder() {
        hideOrderForm();
    }

    private void clearOrderFields() {
        orderCustomerIdField.clear();
        orderTotalField.clear();
        orderStatusField.clear();
    }

    // Методы для Categories
    @FXML
    protected void onAddCategory() throws SQLException, ClassNotFoundException {
        showCategoryForm();
        if (!categoriesList.isEmpty()) {
            Categories lastCategory = categoriesList.get(categoriesList.size() - 1);
            categoryIdField.setText(String.valueOf(lastCategory.getCategoryId() + 1));
        } else {
            categoryIdField.setText("1");
        }
        clearCategoryFields();
    }

    @FXML
    protected void onChangeCategory() throws SQLException, ClassNotFoundException {
        Categories category = CategoriesTable.getSelectionModel().getSelectedItem();
        if (category != null) {
            showCategoryForm();
            categoryIdField.setText(String.valueOf(category.getCategoryId()));
            categoryNameField.setText(category.getName());
        } else {
            showErrorAlert("Ошибка", "Выберите категорию для изменения");
        }
    }

    @FXML
    protected void onDeleteCategory() throws SQLException, ClassNotFoundException {
        Categories category = CategoriesTable.getSelectionModel().getSelectedItem();
        if (category != null) {
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Подтверждение удаления");
            confirmAlert.setHeaderText("Удаление категории");
            confirmAlert.setContentText("Вы уверены, что хотите удалить категорию: " + category.getName() + "?");

            Optional<ButtonType> result = confirmAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    db.DelDB(category, "Categories");
                    loadAllTables();
                } catch (SQLException e) {
                    // Проверяем, является ли ошибка нарушением внешнего ключа
                    if (e.getMessage().contains("foreign key") || e.getMessage().contains("FOREIGN KEY")) {
                        showErrorAlert("Ошибка удаления",
                                "Нельзя удалить категорию " + category.getName() + "!\n" +
                                        "Эта категория связана с существующей мебелью.\n" +
                                        "Сначала удалите или измените всю мебель этой категории.");
                    } else {
                        showErrorAlert("Ошибка базы данных", "Не удалось удалить категорию: " + e.getMessage());
                    }
                }
            }
        } else {
            showErrorAlert("Ошибка", "Выберите категорию для удаления");
        }
    }

// === МЕТОДЫ ДЛЯ ФОРМ (вызываются из кнопок в формах) ===

    @FXML
    protected void saveCategory() throws SQLException, ClassNotFoundException {
        try {
            Integer id = Integer.valueOf(categoryIdField.getText());
            String name = categoryNameField.getText();

            if (name == null || name.trim().isEmpty()) {
                showErrorAlert("Ошибка ввода", "Название категории не может быть пустым");
                return;
            }

            Categories category = new Categories(id, name.trim());

            // Определяем, это добавление или изменение
            boolean isNew = categoriesList.stream().noneMatch(c -> c.getCategoryId().equals(id));

            if (isNew) {
                db.WriteDB(category, "Categories");
            } else {
                db.UpdateDB(category, "Categories");
            }

            hideCategoryForm();
            loadAllTables();

        } catch (NumberFormatException e) {
            showErrorAlert("Ошибка ввода", "Проверьте правильность введенных данных");
        }
    }

    @FXML
    protected void cancelCategory() {
        hideCategoryForm();
    }

    private void clearCategoryFields() {
        categoryNameField.clear();
    }

    // Методы для Furniture
    @FXML
    protected void onAddFurniture() throws SQLException, ClassNotFoundException {
        showFurnitureForm();
        if (!furnitureList.isEmpty()) {
            Furniture lastFurniture = furnitureList.get(furnitureList.size() - 1);
            furnitureIdField.setText(String.valueOf(lastFurniture.getFurnitureId() + 1));
        } else {
            furnitureIdField.setText("1");
        }
        clearFurnitureFields();
    }

    @FXML
    protected void onChangeFurniture() throws SQLException, ClassNotFoundException {
        Furniture furniture = FurnitureTable.getSelectionModel().getSelectedItem();
        if (furniture != null) {
            showFurnitureForm();
            furnitureIdField.setText(String.valueOf(furniture.getFurnitureId()));
            furnitureNameField.setText(furniture.getName());
            furniturePriceField.setText(String.valueOf(furniture.getPrice()));
            furnitureCategoryIdField.setText(String.valueOf(furniture.getCategoryId()));
        } else {
            showErrorAlert("Ошибка", "Выберите мебель для изменения");
        }
    }

    @FXML
    protected void onDeleteFurniture() throws SQLException, ClassNotFoundException {
        Furniture furniture = FurnitureTable.getSelectionModel().getSelectedItem();
        if (furniture != null) {
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Подтверждение удаления");
            confirmAlert.setHeaderText("Удаление мебели");
            confirmAlert.setContentText("Вы уверены, что хотите удалить: " + furniture.getName() + "?");

            Optional<ButtonType> result = confirmAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    db.DelDB(furniture, "Furniture");
                    loadAllTables();
                } catch (SQLException e) {
                    // Проверяем, является ли ошибка нарушением внешнего ключа
                    if (e.getMessage().contains("foreign key") || e.getMessage().contains("FOREIGN KEY")) {
                        showErrorAlert("Ошибка удаления",
                                "Нельзя удалить мебель " + furniture.getName() + "!\n" +
                                        "Эта мебель связана с существующими позициями заказов.\n" +
                                        "Сначала удалите все позиции заказов с этой мебелью.");
                    } else {
                        showErrorAlert("Ошибка базы данных", "Не удалось удалить мебель: " + e.getMessage());
                    }
                }
            }
        } else {
            showErrorAlert("Ошибка", "Выберите мебель для удаления");
        }
    }

// === МЕТОДЫ ДЛЯ ФОРМ (вызываются из кнопок в формах) ===

    @FXML
    protected void saveFurniture() throws SQLException, ClassNotFoundException {
        try {
            Integer id = Integer.valueOf(furnitureIdField.getText());
            String name = furnitureNameField.getText();
            Double price = Double.valueOf(furniturePriceField.getText());
            Integer categoryId = Integer.valueOf(furnitureCategoryIdField.getText());

            if (name == null || name.trim().isEmpty()) {
                showErrorAlert("Ошибка ввода", "Название мебели не может быть пустым");
                return;
            }

            if (price < 0) {
                showErrorAlert("Ошибка ввода", "Цена не может быть отрицательной");
                return;
            }

            if (categoryId <= 0) {
                showErrorAlert("Ошибка ввода", "ID категории должен быть положительным числом");
                return;
            }

            Furniture furniture = new Furniture(id, name.trim(), price, categoryId);

            // Определяем, это добавление или изменение
            boolean isNew = furnitureList.stream().noneMatch(f -> f.getFurnitureId().equals(id));

            try {
                if (isNew) {
                    db.WriteDB(furniture, "Furniture");
                } else {
                    db.UpdateDB(furniture, "Furniture");
                }

                hideFurnitureForm();
                loadAllTables();

            } catch (SQLException e) {
                // Проверяем, является ли ошибка нарушением внешнего ключа
                if (e.getMessage().contains("foreign key") || e.getMessage().contains("FOREIGN KEY")) {
                    showErrorAlert("Ошибка внешнего ключа",
                            "Категория с ID " + categoryId + " не существует.\n" +
                                    "Пожалуйста, выберите существующую категорию.");
                } else {
                    showErrorAlert("Ошибка базы данных", "Не удалось сохранить мебель: " + e.getMessage());
                }
            }

        } catch (NumberFormatException e) {
            showErrorAlert("Ошибка ввода", "Проверьте правильность введенных числовых значений");
        }
    }

    @FXML
    protected void cancelFurniture() {
        hideFurnitureForm();
    }

    private void clearFurnitureFields() {
        furnitureNameField.clear();
        furniturePriceField.clear();
        furnitureCategoryIdField.clear();
    }

    // Методы для OrderItems
    @FXML
    protected void onAddOrderItem() throws SQLException, ClassNotFoundException {
        showOrderItemForm();
        if (!orderItemsList.isEmpty()) {
            OrderItems lastOrderItem = orderItemsList.get(orderItemsList.size() - 1);
            orderItemIdField.setText(String.valueOf(lastOrderItem.getItemId() + 1));
        } else {
            orderItemIdField.setText("1");
        }
        clearOrderItemFields();
    }

    @FXML
    protected void onChangeOrderItem() throws SQLException, ClassNotFoundException {
        OrderItems orderItem = OrderItemsTable.getSelectionModel().getSelectedItem();
        if (orderItem != null) {
            showOrderItemForm();
            orderItemIdField.setText(String.valueOf(orderItem.getItemId()));
            orderItemOrderIdField.setText(String.valueOf(orderItem.getOrderId()));
            orderItemFurnitureIdField.setText(String.valueOf(orderItem.getFurnitureId()));
            orderItemCountField.setText(String.valueOf(orderItem.getCount()));
        } else {
            showErrorAlert("Ошибка", "Выберите позицию заказа для изменения");
        }
    }

    @FXML
    protected void onDeleteOrderItem() throws SQLException, ClassNotFoundException {
        OrderItems orderItem = OrderItemsTable.getSelectionModel().getSelectedItem();
        if (orderItem != null) {
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Подтверждение удаления");
            confirmAlert.setHeaderText("Удаление позиции заказа");
            confirmAlert.setContentText("Вы уверены, что хотите удалить позицию заказа ID: " + orderItem.getItemId() + "?");

            Optional<ButtonType> result = confirmAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                db.DelDB(orderItem, "Order_items");
                loadAllTables();
            }
        } else {
            showErrorAlert("Ошибка", "Выберите позицию заказа для удаления");
        }
    }

// === МЕТОДЫ ДЛЯ ФОРМ (вызываются из кнопок в формах) ===

    @FXML
    protected void saveOrderItem() throws SQLException, ClassNotFoundException {
        try {
            Integer id = Integer.valueOf(orderItemIdField.getText());
            Integer orderId = Integer.valueOf(orderItemOrderIdField.getText());
            Integer furnitureId = Integer.valueOf(orderItemFurnitureIdField.getText());
            Integer count = Integer.valueOf(orderItemCountField.getText());

            if (orderId <= 0) {
                showErrorAlert("Ошибка ввода", "ID заказа должен быть положительным числом");
                return;
            }

            if (furnitureId <= 0) {
                showErrorAlert("Ошибка ввода", "ID мебели должен быть положительным числом");
                return;
            }

            if (count <= 0) {
                showErrorAlert("Ошибка ввода", "Количество должно быть положительным числом");
                return;
            }

            OrderItems orderItem = new OrderItems(id, orderId, furnitureId, count);

            // Определяем, это добавление или изменение
            boolean isNew = orderItemsList.stream().noneMatch(oi -> oi.getItemId().equals(id));

            try {
                if (isNew) {
                    db.WriteDB(orderItem, "Order_items");
                } else {
                    db.UpdateDB(orderItem, "Order_items");
                }

                hideOrderItemForm();
                loadAllTables();

            } catch (SQLException e) {
                // Проверяем, является ли ошибка нарушением внешнего ключа
                String errorMessage = e.getMessage().toLowerCase();
                if (errorMessage.contains("foreign key")) {
                    if (errorMessage.contains("order_id")) {
                        showErrorAlert("Ошибка внешнего ключа",
                                "Заказ с ID " + orderId + " не существует.\n" +
                                        "Пожалуйста, выберите существующий заказ.");
                    } else if (errorMessage.contains("furniture_id")) {
                        showErrorAlert("Ошибка внешнего ключа",
                                "Мебель с ID " + furnitureId + " не существует.\n" +
                                        "Пожалуйста, выберите существующую мебель.");
                    } else {
                        showErrorAlert("Ошибка внешнего ключа",
                                "Нарушение целостности данных. Проверьте существование заказа и мебели.");
                    }
                } else {
                    showErrorAlert("Ошибка базы данных", "Не удалось сохранить позицию заказа: " + e.getMessage());
                }
            }

        } catch (NumberFormatException e) {
            showErrorAlert("Ошибка ввода", "Проверьте правильность введенных числовых значений");
        }
    }

    @FXML
    protected void cancelOrderItem() {
        hideOrderItemForm();
    }

    private void clearOrderItemFields() {
        orderItemOrderIdField.clear();
        orderItemFurnitureIdField.clear();
        orderItemCountField.clear();
    }


    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
