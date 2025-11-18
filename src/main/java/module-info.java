module edu.utsa.cs3443.uzd052_lab4 {
    requires javafx.controls;
    requires javafx.fxml;

    opens edu.utsa.cs3443.uzd052_lab4 to javafx.fxml;
    exports edu.utsa.cs3443.uzd052_lab4;
    exports edu.utsa.cs3443.uzd052_lab4.model;
}