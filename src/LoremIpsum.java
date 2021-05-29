import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

/** Sample data for a table view */
public class LoremIpsum {
	final public static ObservableList data = FXCollections.unmodifiableObservableList(FXCollections.observableArrayList(
			new LoremIpsum("Lorem","ipsum","dolor"),
			new LoremIpsum("sit","amet,","consectetur"),
			new LoremIpsum("adipiscing","elit.","Fusce"),
			new LoremIpsum("adipiscing","dui","et"),
			new LoremIpsum("tellus","ornare","adipiscing.")
	));

	final private String a,b,g;
	LoremIpsum(String a, String b, String g) {
		this.a = a; this.b = b; this.g = g;
	}
	public String getA() { return a; }
	public String getG() { return g; }
	public String getB() { return b; }
}