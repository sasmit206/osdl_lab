import javafx.application.Application;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class HotelManagementSystem extends Application {

    static class Room {
        SimpleIntegerProperty roomNumber = new SimpleIntegerProperty();
        SimpleStringProperty roomType = new SimpleStringProperty();
        SimpleDoubleProperty price = new SimpleDoubleProperty();
        SimpleBooleanProperty available = new SimpleBooleanProperty(true);

        Room(int num, String type, double p, boolean avail) {
            roomNumber.set(num); roomType.set(type); price.set(p); available.set(avail);
        }

        // Getters for PropertyValueFactory
        public SimpleIntegerProperty roomNumberProperty() { return roomNumber; }
        public SimpleStringProperty roomTypeProperty() { return roomType; }
        public SimpleDoubleProperty priceProperty() { return price; }
        public SimpleBooleanProperty availableProperty() { return available; }
    }

    static class Customer {
        SimpleStringProperty name = new SimpleStringProperty();
        SimpleStringProperty contact = new SimpleStringProperty();
        SimpleIntegerProperty roomNumber = new SimpleIntegerProperty();

        Customer(String n, String c, int r) { 
            name.set(n); contact.set(c); roomNumber.set(r); 
        }

        public SimpleStringProperty nameProperty() { return name; }
        public SimpleStringProperty contactProperty() { return contact; }
        public SimpleIntegerProperty roomNumberProperty() { return roomNumber; }
    }

    ObservableList<Room> rooms = FXCollections.observableArrayList();
    ObservableList<Customer> customers = FXCollections.observableArrayList();
    TableView<Room> roomTable = new TableView<>();
    TableView<Customer> customerTable = new TableView<>();
    Label statusLabel = new Label("Ready");

    @Override
    public void start(Stage stage) {
        // Sample data
        rooms.addAll(
            new Room(101, "Single", 2500, true),
            new Room(102, "Double", 3500, false),
            new Room(103, "Deluxe", 5000, true)
        );

        TabPane tabs = new TabPane();
        tabs.getTabs().addAll(createRoomTab(), createBookingTab());

        VBox root = new VBox(10, tabs, new HBox(statusLabel));
        root.setPadding(new Insets(10));
        stage.setScene(new Scene(root, 900, 600));
        stage.setTitle("Hotel Management System");
        stage.show();
    }

    Tab createRoomTab() {
        // Room table columns
        TableColumn<Room, Integer> roomCol = new TableColumn<>("Room No");
        roomCol.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));

        TableColumn<Room, String> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("roomType"));

        TableColumn<Room, Double> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Room, Boolean> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("available"));

        roomTable.getColumns().addAll(roomCol, typeCol, priceCol, statusCol);
        roomTable.setItems(rooms);

        // Room form
        GridPane form = new GridPane();
        form.setHgap(10); form.setVgap(10); form.setPadding(new Insets(10));
        
        TextField numField = new TextField();
        TextField priceField = new TextField();
        ComboBox<String> typeCombo = new ComboBox<>();
        typeCombo.getItems().addAll("Single", "Double", "Deluxe");

        Button addBtn = new Button("Add Room");
        addBtn.setOnAction(e -> {
            try {
                int num = Integer.parseInt(numField.getText());
                String type = typeCombo.getValue();
                double price = Double.parseDouble(priceField.getText());
                
                if (type != null) {
                    rooms.add(new Room(num, type, price, true));
                    numField.clear(); priceField.clear(); typeCombo.getSelectionModel().clearSelection();
                    statusLabel.setText("Room " + num + " added");
                }
            } catch (Exception ex) {
                statusLabel.setText("Invalid input");
            }
        });

        form.addRow(0, new Label("Room No:"), numField);
        form.addRow(1, new Label("Type:"), typeCombo);
        form.addRow(2, new Label("Price:"), priceField);
        form.addRow(3, addBtn);

        VBox roomLayout = new VBox(10, form, roomTable);
        roomLayout.setPadding(new Insets(10));
        return new Tab("Room Management", roomLayout);
    }

    Tab createBookingTab() {
        GridPane form = new GridPane();
        form.setHgap(10); form.setVgap(10); form.setPadding(new Insets(10));
        
        TextField nameField = new TextField();
        TextField contactField = new TextField();
        TextField roomField = new TextField();

        Button bookBtn = new Button("Book Room");
        bookBtn.setOnAction(e -> {
            try {
                String name = nameField.getText();
                String contact = contactField.getText();
                int roomNum = Integer.parseInt(roomField.getText());

                Room room = rooms.stream()
                    .filter(r -> r.roomNumber.get() == roomNum && r.available.get())
                    .findFirst().orElse(null);

                if (room != null && !name.isEmpty() && !contact.isEmpty()) {
                    room.available.set(false);
                    customers.add(new Customer(name, contact, roomNum));
                    clear(nameField, contactField, roomField);
                    statusLabel.setText("Room " + roomNum + " booked for " + name);
                } else {
                    statusLabel.setText("Room not available or invalid input");
                }
            } catch (Exception ex) {
                statusLabel.setText("Invalid room number");
            }
        });

        Button checkoutBtn = new Button("Checkout");
        checkoutBtn.setOnAction(e -> {
            try {
                int roomNum = Integer.parseInt(roomField.getText());
                rooms.stream()
                    .filter(r -> r.roomNumber.get() == roomNum && !r.available.get())
                    .findFirst()
                    .ifPresent(r -> {
                        r.available.set(true);
                        customers.removeIf(c -> c.roomNumber.get() == roomNum);
                        statusLabel.setText("Room " + roomNum + " checked out");
                    });
            } catch (Exception ex) {
                statusLabel.setText("Invalid room number");
            }
        });

        form.addRow(0, new Label("Name:"), nameField);
        form.addRow(1, new Label("Contact:"), contactField);
        form.addRow(2, new Label("Room No:"), roomField);
        form.addRow(3, bookBtn, checkoutBtn);

        // Customer table
        TableColumn<Customer, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        TableColumn<Customer, String> contactCol = new TableColumn<>("Contact");
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contact"));
        
        TableColumn<Customer, Integer> roomCol = new TableColumn<>("Room");
        roomCol.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));

        customerTable.getColumns().addAll(nameCol, contactCol, roomCol);
        customerTable.setItems(customers);

        VBox bookingLayout = new VBox(10, form, customerTable);
        bookingLayout.setPadding(new Insets(10));
        return new Tab("Booking & Checkout", bookingLayout);
    }

    void clear(TextField... fields) { 
        for (TextField f : fields) f.clear(); 
    }

    public static void main(String[] args) { 
        launch(args); 
    }
}
