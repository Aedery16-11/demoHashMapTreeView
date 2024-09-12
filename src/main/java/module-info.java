module sio.demohashmaptreeview {
    requires javafx.controls;
    requires javafx.fxml;


    opens sio.demohashmaptreeview to javafx.fxml;
    exports sio.demohashmaptreeview;
}