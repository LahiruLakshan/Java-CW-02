import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TableSortSelectionTest extends Application {

	@Override
	public void start(Stage primaryStage) {

		final TableView<IntegerProperty> table = new TableView<>();
		table.getItems().addAll(new SimpleIntegerProperty(1), new SimpleIntegerProperty(2), new SimpleIntegerProperty(3));
		final TableColumn<IntegerProperty, Integer> col = new TableColumn<>("Value");
		col.setCellValueFactory(new PropertyValueFactory<IntegerProperty, Integer>("value"));
		table.getColumns().add(col);

		table.getSelectionModel().selectedItemProperty() .addListener(new ChangeListener<IntegerProperty>() {
			@Override
			public void changed(ObservableValue<? extends IntegerProperty> observable, IntegerProperty oldValue, IntegerProperty newValue) {

				if (newValue == null) {
					System.out.println("Nothing selected");
				} else {
					System.out.println(newValue.getValue());
				}
			}
		});

		Button button = new Button("Show selection model data");
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				SelectionModel<IntegerProperty> selectionModel = table .getSelectionModel();
				System.out.println("Selected index: " + selectionModel.getSelectedIndex());
				System.out.println("Selected item: " + selectionModel.getSelectedItem());
			}
		});

		final BorderPane root = new BorderPane();
		root.setCenter(table);
		root.setBottom(button);
		primaryStage.setScene(new Scene(root, 200, 400));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}