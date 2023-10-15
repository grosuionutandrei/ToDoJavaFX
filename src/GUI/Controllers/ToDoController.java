package GUI.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ToDoController {


    @FXML
    private VBox createToDo;
    @FXML
    private VBox inProgress;
    @FXML
    private VBox done;
    @FXML
    private String currentName="";
    @FXML
    private int removeIndex;


    public void moveToProgress(ActionEvent event) {

        removeNodeFromList();



        if(!currentName.isEmpty()){
            inProgress.getChildren().add(todoTextField(currentName));
            currentName="";
        }
    }

    private void removeNodeFromList() {
        for(Node node:createToDo.getChildren()){
            TextField field = (TextField)node;
            if(field.getText().equals(currentName)){
                createToDo.getChildren().remove(field);
                return;
            }
        }
    }

    public void moveBackToNew(ActionEvent event) {
    }

    public void moveToDone(ActionEvent event) {
    }

    public void moveBackToProgress(ActionEvent event) {
    }

    public void createNewToDo(ActionEvent actionEvent) {
        TextField txt = todoTextField("Todo name");
        createToDo.getChildren().add(txt);

    }

    private TextField todoTextField(String title) {
        TextField txt =  new TextField();
        txt.setText(title);
        txt.setEditable(false);

        txt.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                txt.setEditable(true);
            }
        });
        txt.textProperty().addListener((observable, oldValue, newValue) -> {
            this.currentName=txt.getText();
        });

        txt.setOnMouseClicked(event -> {
            if(event.getClickCount()==1){
                this.currentName =((TextField)(Node)event.getSource()).getText();
                txt.setEditable(false);
            }
            if (event.getClickCount() == 2) {
                txt.setEditable(true);
            }
        });
        return txt;
    }


    private String getToDoName(StackPane stackPane){
        String name= "";
        for(Node node:stackPane.getChildren()){

            if(node instanceof Label ){
                name=((Label) node).getText();
            }
        }
        return name;
    }
}
