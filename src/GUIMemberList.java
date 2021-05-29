//import javafx.application.Application;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.geometry.Insets;
//import javafx.scene.Scene;
//import javafx.scene.control.Label;
//import javafx.scene.control.SelectionMode;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.layout.VBox;
//import javafx.scene.text.Font;
//import javafx.scene.text.FontPosture;
//import javafx.scene.text.FontWeight;
//import javafx.stage.Stage;
//public class GUIMemberList extends Application {
//    public void start(Stage stage) {
//
//        Label label = new Label("Members Table");
//        Font font = Font.font("calibri", FontWeight.BOLD, FontPosture.REGULAR, 12);
//        label.setFont(font);
//        //Creating a table view
//        TableView<DefaultMember> table = new TableView<DefaultMember>();
//        final ObservableList<DefaultMember> data = FXCollections.observableArrayList(
//                MyGymManager.memberDetailList
//
//        );
//
//        TableColumn membershipId = new TableColumn("Membership ID");
//        membershipId.setCellValueFactory(new PropertyValueFactory("membershipNumber"));
//
//        TableColumn memberName = new TableColumn("Member Name");
//        memberName.setCellValueFactory(new PropertyValueFactory("name"));
//
//        TableColumn memberRegisterDate = new TableColumn("Member Register Date");
//        memberRegisterDate.setCellValueFactory(new PropertyValueFactory("startMembershipDate"));
//
//        TableColumn schoolName = new TableColumn("School Name");
//        schoolName.setCellValueFactory(new PropertyValueFactory("schoolName"));
//
//        TableColumn memberWeight = new TableColumn("Member Weight(kg)");
//        memberWeight.setCellValueFactory(new PropertyValueFactory("weight"));
//
//        TableColumn memberAge = new TableColumn("Member Age");
//        memberAge.setCellValueFactory(new PropertyValueFactory("memberAge"));
//
//
//        ObservableList<String> list = FXCollections.observableArrayList();
//        table.setItems(data);
//        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//        table.getColumns().addAll(membershipId, memberName, memberRegisterDate, schoolName, memberWeight, memberAge);
//
//        table.setMaxSize(2000, 400);
//        VBox vbox = new VBox();
//        vbox.setSpacing(5);
//        vbox.setPadding(new Insets(10, 50, 50, 60));
//        vbox.getChildren().addAll(label, table);
//
//        Scene scene = new Scene(vbox, 900, 500);
//        stage.setTitle("Member List");
//        stage.setScene(scene);
//        stage.show();
//    }
//    public static void main(String[] args){
//        launch(args);
//    }
//}



import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GUIMemberList extends Application {

    private final TableView<DefaultMember> table = new TableView<DefaultMember>();
    private final ObservableList<DefaultMember> data = FXCollections.observableArrayList(MyGymManager.memberDetailList);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("Table View Sample");
        stage.setWidth(1040);
        stage.setHeight(550);

        final Label label = new Label("Members Book");
        label.setFont(new Font("Arial", 20));

        table.setEditable(true);

        TableColumn memberID = new TableColumn("Membership ID");
        memberID.setMinWidth(100);
        memberID.setCellValueFactory(new PropertyValueFactory<DefaultMember, String>("membershipNumber"));

        TableColumn memberName = new TableColumn("Member Name");
        memberName.setMinWidth(100);
        memberName.setCellValueFactory(new PropertyValueFactory<DefaultMember, String>("name"));

        TableColumn memberRegisterDate = new TableColumn("Member Register Date");
        memberRegisterDate.setMinWidth(200);
        memberRegisterDate.setCellValueFactory(new PropertyValueFactory<DefaultMember, String>("startMembershipDate"));

        TableColumn schoolName = new TableColumn("School Name");
        schoolName.setMinWidth(200);
        schoolName.setCellValueFactory(new PropertyValueFactory<DefaultMember, String>("schoolName"));

        TableColumn memberWeight = new TableColumn("Member Weight");
        memberWeight.setMinWidth(200);
        memberWeight.setCellValueFactory(new PropertyValueFactory<DefaultMember, String>("weight"));

        TableColumn memberAge = new TableColumn("Member Age");
        memberAge.setMinWidth(200);
        memberAge.setCellValueFactory(new PropertyValueFactory<DefaultMember, String>("memberAge"));

        //Pass the data to a filtered list
        FilteredList<DefaultMember> member = new FilteredList(data, p -> true);
        //Set the table's items using the filtered list
        table.setItems(member);
        table.getColumns().addAll(memberID, memberName, memberRegisterDate,schoolName,memberWeight,memberAge);

        //Adding ChoiceBox and TextField here!
        ChoiceBox<String> choiceBox = new ChoiceBox();
        choiceBox.getItems().addAll("Member ID", "Name", "Register Date");
        choiceBox.setValue("Member ID");

        TextField textField = new TextField();
        textField.setPromptText("Search here!");
        textField.setOnKeyReleased(keyEvent ->
        {
           switch (choiceBox.getValue())//Switch on choiceBox value
            {
                case "Member ID":
                    member.setPredicate(search -> search.getMembershipNumber().toLowerCase().contains(textField.getText().toLowerCase().trim()));//filter table by Member Id
                    break;
                case "Name":
                    member.setPredicate(search -> search.getName().toLowerCase().contains(textField.getText().toLowerCase().trim()));//filter table by Name
                    break;
                case "Register Date":
                    member.setPredicate(search -> search.getStartMembershipDate().toLowerCase().contains(textField.getText().toLowerCase().trim()));//filter table by Register Date
                    break;
            }
        });

        choiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) ->
        {//reset table and textfield when new choice is selected
            if (newVal != null) {
                textField.setText("");
                member.setPredicate(null);//This is same as saying member.setPredicate(p->true);
            }
        });
        HBox hBox = new HBox(choiceBox, textField);//Add choiceBox and textField to hBox
        hBox.setAlignment(Pos.CENTER);//Center HBox
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table, hBox);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }
}