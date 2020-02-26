package team4.personaldietary.GUI;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import team4.personaldietary.bean.Dining;

public class BooleanCell extends TableCell<Dining, Boolean> {
    private CheckBox checkBox;
    public BooleanCell() {
        checkBox = new CheckBox();
//        checkBox.setDisable(true);
        checkBox.selectedProperty().addListener((observableValue, oldValue, newValue) -> {
            if (isEditing())
                commitEdit(newValue == null ? false : newValue);
//            System.out.println(newValue);
        });
        this.setGraphic(checkBox);
        this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        this.setEditable(true);
    }
    //    @Override
//    public void startEdit() {
//        super.startEdit();
//        if (isEmpty()) {
//            return;
//        }
//        checkBox.setDisable(false);
//        checkBox.requestFocus();
//    }
//    @Override
//    public void cancelEdit() {
//        super.cancelEdit();
//        checkBox.setDisable(true);
//    }
//    public void commitEdit(Boolean value) {
//        super.commitEdit(value);
//        checkBox.setDisable(true);
//    }
    @Override
    public void updateItem(Boolean item, boolean empty) {
        super.updateItem(item, empty);
        if (!isEmpty()) {
            checkBox.setSelected(item);
        }
    }




}

