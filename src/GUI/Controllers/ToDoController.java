package GUI.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


// refactor the logic so it can use model, controller ,view.
public class ToDoController {


    @FXML
    private VBox createToDo;
    @FXML
    private VBox inProgress;
    @FXML
    private VBox done;
    @FXML
    private String createdTodoSelected ="";
    @FXML
    private String inProgressSelected="";
    @FXML
    private String doneToDoSelected="";


    private enum Selector{
        CREATED_SELECTED,IN_PROGRESS_SELECTED,DONE_SELECTED
    }


    public void moveToProgress(ActionEvent event) {
        removeNodeFromList(createToDo,createdTodoSelected);
        if(!createdTodoSelected.isEmpty()){
            inProgress.getChildren().add(todoTextField(createdTodoSelected,Selector.IN_PROGRESS_SELECTED));
            createdTodoSelected ="";
        }
    }

    private void removeNodeFromList(VBox container,String selector) {
        for(Node node:container.getChildren()){
            TextField field = (TextField)node;
            if(field.getText().equals(selector)){
                container.getChildren().remove(field);
                return;
            }
        }
    }

    public void moveBackToNew(ActionEvent event) {
        removeNodeFromList(inProgress,inProgressSelected);
        if(!inProgressSelected.isEmpty()){
            createToDo.getChildren().add(todoTextField(inProgressSelected,Selector.CREATED_SELECTED));
            inProgressSelected = "";
        }
    }

    public void moveToDone(ActionEvent event) {
        removeNodeFromList(inProgress,inProgressSelected);
        if(!inProgressSelected.isEmpty()){
            done.getChildren().add(todoTextField(inProgressSelected,Selector.DONE_SELECTED));
            inProgressSelected = "";
        }

    }

    public void moveBackToProgress(ActionEvent event) {
        removeNodeFromList(done,doneToDoSelected);
        if(!doneToDoSelected.isEmpty()){
            inProgress.getChildren().add(todoTextField(doneToDoSelected,Selector.IN_PROGRESS_SELECTED));
            doneToDoSelected = "";
        }
    }

    public void createNewToDo(ActionEvent actionEvent) {
        TextField txt = todoTextField("Todo name",Selector.CREATED_SELECTED);
        createToDo.getChildren().add(txt);

    }

    private TextField todoTextField(String title,Selector selector) {
        TextField txt =  new TextField();
        txt.setText(title);
        txt.setEditable(false);

        txt.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                txt.setEditable(true);
            }
        });
        txt.textProperty().addListener((observable, oldValue, newValue) -> {
            switchSelector(selector, txt.getText());

        });

        txt.setOnMouseClicked(event -> {
            if(event.getClickCount()==1){

                switchSelector(selector,((TextField)(Node)event.getSource()).getText());
                txt.setEditable(false);
            }
            if (event.getClickCount() == 2) {
                txt.setEditable(true);
            }
        });
        return txt;
    }

    private void switchSelector(Selector selector, String txt) {
        switch(selector){
            case CREATED_SELECTED:
                this.createdTodoSelected= txt;
                break;
            case IN_PROGRESS_SELECTED:
                this.inProgressSelected= txt;
                break;
            case DONE_SELECTED:
                this.doneToDoSelected= txt;
                break;
            default:
                throw new IllegalArgumentException("No support for this selector");
        }
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
