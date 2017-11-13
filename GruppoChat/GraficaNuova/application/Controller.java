package application;

import java.net.URL;
import java.util.ResourceBundle;

import org.omg.CORBA.INITIALIZE;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextBuilder;

public class Controller implements Initializable {
	@FXML Button btnJoin;
	@FXML TextField txtNickname;
	@FXML ComboBox cmbChat;
	@FXML TabPane tabPane;
	@FXML ListView<String> list = new ListView<String>();
	@FXML ObservableList<String> item =FXCollections.observableArrayList("ciao","boh","dad","sdasd","sdasdwq","sdqwqe","qwqwe");
	@FXML TextField txtMsg;
	@FXML Button btnInvia;
	@FXML ImageView img;
	@FXML ContextMenu menu = new ContextMenu();
	@FXML ContextMenu menuInvia = new ContextMenu();
	@FXML ImageView imgDown;

	final Clipboard clipboard = Clipboard.getSystemClipboard();
    final ClipboardContent content = new ClipboardContent();
	
    @FXML TableView t;
    @FXML TableColumn tc;
	private ObservableList<String> datitab = FXCollections.observableArrayList("ciao");
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cmbChat.getItems().add("ciao");
		cmbChat.getSelectionModel().selectFirst();
		list.setId("lv");
		Platform.runLater(()->txtNickname.requestFocus());
	}
	
	public void clickJoin() {
		txtNickname.setText("ciao");
		list.setItems(item);

	}

	public void clickLista() {
		System.out.println(list.getSelectionModel().getSelectedItem());
		list.setOnContextMenuRequested(e -> menu.show(list, e.getScreenX(), e.getScreenY()));
	}
	
	public void clickCopia() {

	    content.putString(list.getSelectionModel().getSelectedItem());
	    clipboard.setContent(content);

	}

	public void clickTextA() {
		txtMsg.setOnContextMenuRequested(e -> menuInvia.show(txtMsg, e.getScreenX(), e.getScreenY()));
	}

	public void clickIncolla() {
		if(clipboard.getString() != null) {
			txtMsg.setText(txtMsg.getText()+clipboard.getString());
		}
	}
	
	public void cambioTab() {
		switch (tabPane.getSelectionModel().getSelectedIndex()) {
		case 0:{
			txtNickname.requestFocus();
		}break;
		case 1:{
			txtMsg.requestFocus();
		}break;
		default:
			break;
		}
	}
	
	/*public void clickInvia() {

		list.getItems().add("[You]: "+ txtMsg.getText());
		txtMsg.setText("");
		txtMsg.requestFocus();
	}*/
	
	public void textInvio(KeyEvent ke)
    {
        if (ke.getCode().equals(KeyCode.ENTER))
        {
           // clickInvia();
        }
    }
	
	public void clickInvia() {
		//TableCell c = new TableCell<>();
		//tc.setCellValueFactory(new PropertyValueFactory<String, String>("email"));
		t.getColumns().addAll(tc);
		System.out.println("debuhg");
	}
}
