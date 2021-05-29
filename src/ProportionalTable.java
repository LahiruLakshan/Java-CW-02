import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/** Table bound to a proportional width */
public class ProportionalTable extends Application {
	final static private TableView<LoremIpsum> table = new TableView(LoremIpsum.data);
	private Scene scene;

	public void start(final Stage stage) throws Exception {
		scene = new Scene(table, 300, 200);
		table.getColumns().addAll(makeColumn("alpha"), makeColumn("beta"), makeColumn("gamma"));
		stage.setScene(scene);
		stage.show();
	}

	private TableColumn<LoremIpsum, String> makeColumn(String name) {
		TableColumn<LoremIpsum, String> column = new TableColumn<LoremIpsum, String>(name);
		column.prefWidthProperty().bind(scene.widthProperty().divide(3).subtract(2.1/3));
		column.maxWidthProperty().bind(column.prefWidthProperty());
		column.setResizable(false);
		column.setCellValueFactory(new PropertyValueFactory<LoremIpsum, String>(name.substring(0, 1)));

		return column;
	}
	public static void main(String[] args) throws Exception { launch(args); }
}