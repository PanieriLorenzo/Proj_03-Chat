package Control;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;
import java.io.File;
import java.lang.reflect.Array;

import org.omg.CORBA.INITIALIZE;

import Model.ClientUser;
import Model.Message;
import Model.Room;
import View.ColoredText;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;

public class Controller_Client implements Initializable {
	//ATTRIBUTI DI CONNETTIVITA':
	private DatagramSocket clientSocket;
	private InetAddress IPAddress;
	private final int SERVER_PORT = 9999;
	private int clientPort;
	private byte[] sendBuffer = new byte[1024];
	private byte[] receiveBuffer = new byte[1024];
	private String[] receiveMSG;
	
	//ATTRIBUTI GRAFICA:
	@FXML Button btnJoin;
	@FXML TextField txtNickname;
	@FXML ComboBox cmbChat;
	@FXML TextField txtRoom;
	private boolean isCreate;
	
	@FXML TabPane tabPane;
	@FXML Tab tabChat;
	@FXML Tab tabWelcome;
	
	//@FXML ListView<String> list = new ListView<String>();
	@FXML ListView<ColoredText> list = new ListView<ColoredText>();
	@FXML ContextMenu menu = new ContextMenu();
	@FXML ContextMenu menuInvia = new ContextMenu();
	@FXML TextField txtMsg;
	@FXML Button btnInvia;
	
	@FXML ImageView img;

	@FXML ImageView imgDown;
	@FXML ToggleButton tgSound;
	
	final Clipboard clipboard = Clipboard.getSystemClipboard();
    final ClipboardContent content = new ClipboardContent();
	
    @FXML Button btnEsci;
  
	
	boolean sound = true;
	
	final AudioClip sInvio = new AudioClip(this.getClass().getResource("../Sound/all-eyes-on-me.mp3").toExternalForm());
	final AudioClip sRicezione = new AudioClip(this.getClass().getResource("../Sound/receiveMSGfx.mp3").toExternalForm());
	final AudioClip sSuona = new AudioClip(this.getClass().getResource("../Sound/ringFx.mp3").toExternalForm());
	final AudioClip sMatematica_veloce = new AudioClip(this.getClass().getResource("../Sound/quick_maths.mp3").toExternalForm());
	
    String nick = null;
    boolean coloreFlag = false;
    Color colore = Color.web("#000000");;
    Color coloreUtente = Color.web("#D41800");
    String room = null;
	
    //ATTRIBUTI THREAD:
    Service<Void> service;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//HANDSHAKE:
		System.out.println("Handshake...");
		try {
			IPAddress = InetAddress.getByName("localhost");
			//SPEDIZIONE
			System.out.println("TRY> Spedizione...");
			clientSocket = new DatagramSocket();
			System.out.println("TRY> Variabili inizializzate, porta: " + clientSocket.getPort());
			String message = "HAND";
			System.out.println("TRY> Generato messaggio: " + message);
			clientSocket.send(new DatagramPacket(message.getBytes(), message.getBytes().length, IPAddress, SERVER_PORT));
			System.out.println("TRY> Inviato messaggio a " + IPAddress.getHostAddress() + ":" + SERVER_PORT);
			System.out.println("TRY> Spedizione completata!");
			//RICEZIONE
			System.out.println("TRY> Ricezione...");
			DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
			System.out.println("TRY> Creato receivePacket");
			clientSocket.receive(receivePacket);
			System.out.println("TRY> ricevuto il pacchetto");
			receiveMSG = new String(receivePacket.getData(), 0, receivePacket.getLength()).split(" ");
			System.out.println("TRY> ricevuto messaggio: " + new String(receivePacket.getData(), 0, receivePacket.getLength()));
			System.out.println("TRY> Ricezione completata!");
			clientSocket.close();
			System.out.println("TRY> Socket chiuso");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//INIT GRAFICA:
		for(int i=1; i<receiveMSG.length; i++) {
			cmbChat.getItems().add(receiveMSG[i]);
		}
		cmbChat.getItems().add("CREA STANZA");
		txtRoom.setVisible(false);
		isCreate = false;
		cmbChat.getSelectionModel().selectFirst();
		list.setId("lv");
		tabChat.setDisable(true);
		tgSound.setSelected(true);
		tgSound.setText("Attivo");
		list.setCellFactory(lv -> new ListCell<ColoredText>() {
            @Override
            protected void updateItem(ColoredText item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null) {
                    setText(null);
                    setTextFill(null);
                } else {
                    setText(item.getText());
                    setTextFill(item.getColor());
                }
            }
        });
		Platform.runLater(()->txtNickname.requestFocus());
		
		//INIT THREAD:
		service = new Service<Void>() {
	        @Override
	        protected Task<Void> createTask() {
	            return new Task<Void>() {
	                @Override
	                protected Void call() throws Exception {
	                	boolean running = true;
	                	System.out.println("Thread_Server avviato");
						byte[] receiveBuffer = new byte[1024];
						DatagramPacket receivePacket;
						String[] receiveRawMSG;
						System.out.println("Variabili inizializzate");
						
						try{
							DatagramSocket clientSocket = new DatagramSocket(clientPort-20000);
							System.out.println("TRY> clientSocket creato on porta: " + (clientPort-20000));
							while(running){
								System.out.println("TRY> FOR> Ricezione...");
								receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
								System.out.println("a");
								clientSocket.receive(receivePacket);
								System.out.println("b");
								receiveRawMSG = (new String(receivePacket.getData())).split(" ");
								System.out.println("TRY> FOR> ricevuto pacchetto");
								System.out.println("\te messaggio: " + receiveRawMSG[0].trim());
								
								if(receiveRawMSG[0].trim().equals("HAND")){
									assert true; //non fa nulla
								}else if(receiveRawMSG[0].trim().equals("CNCT")){
									assert true; //non fa nulla
								}else if(receiveRawMSG[0].trim().equals("MESG")){
									StringBuilder tempMSG = new StringBuilder();
									for(int i=4; i<receiveRawMSG.length;i++) {
										tempMSG.append(receiveRawMSG[i].trim() + " ");
									}
									String exploitTempNick = receiveRawMSG[1].trim();
									
									coloreFlag = true;
									colore = Color.web(receiveRawMSG[2].trim());
									System.out.println("--------"+receiveRawMSG[2].trim());
									String exploit = "[" + exploitTempNick + "]: "+ aCapoAuto(tempMSG.toString());
									String exploitColore = receiveRawMSG[2].trim();
									//Platform.runLater(()-> list.getItems().add("[" + exploitTempNick + "]: "+ aCapoAuto(tempMSG.toString())));
									Platform.runLater(()-> list.getItems().add(new ColoredText(exploit, Color.web(exploitColore))));
									Platform.runLater(()-> list.scrollTo(list.getItems().size()));
									suona(1);
								}else if(receiveRawMSG[0].trim().equals("COMD")) {
									if(receiveRawMSG[2].trim().equals("~§r;")) {
										suona(2);
									}else if(receiveRawMSG[2].trim().equals("~§q;")){
										suona(3);
									}else {
										StringBuilder tempMSG = new StringBuilder();
										for(int i=2; i<receiveRawMSG.length;i++) {
											tempMSG.append(receiveRawMSG[i].trim() + " ");
										}
										String exploit = aCapoAuto(tempMSG.toString());
										//Platform.runLater(()-> list.getItems().add(aCapoAuto(tempMSG.toString())));
										Platform.runLater(()-> list.getItems().add(new ColoredText(exploit, Color.web("#000000"))));
										Platform.runLater(()-> list.scrollTo(list.getItems().size()));
										suona(1);
									}
								}else if(receiveRawMSG[0].trim().equals("EXIT")) {								
									running = false;
								}else{
									System.out.println("Errore: Header messaggio errato/corrotto");
									throw new Exception();
								}
								receiveBuffer = new byte[1024];
								System.out.println("TRY> FOR> Ricezione completata!");
							}
						}catch (Exception e){
							System.out.println("ERRORE: Thread_Client");
							e.printStackTrace();
						}
						System.exit(0);
						return null;
	                }
	            };
	        }
	    };
	}
	
	public void clickJoin() {
		boolean ok = true;
		boolean okStanza = true;
		nick = txtNickname.getText();
		if(nick.equals("")){
			ok = false;
			System.out.println("ALERT: nickname vuoto");
			Alert alert = new Alert(AlertType.ERROR, "Il nickname non può essere vuoto!" , ButtonType.OK);
			alert.showAndWait();
		}else if(nick.contains(" ")){
			ok = false;
			System.out.println("ALERT: nickname contiene spazi");
			Alert alert = new Alert(AlertType.ERROR, "Il nickname non può contenere spazi!" , ButtonType.OK);
			alert.showAndWait();
		}
		
		if(ok) {
			nick = nick.toLowerCase();
			try {
				//SPEDIZIONE
				clientSocket = new DatagramSocket();
				if(isCreate) {
					room = txtRoom.getText();
					if(room.equals("")) {
						okStanza = false;
						Alert alert = new Alert(AlertType.ERROR, "Il nome della stanza non può essere vuoto!" , ButtonType.OK);
						alert.showAndWait();
					}else {
						okStanza = true;
					}
				}else {
					room = (String)cmbChat.getSelectionModel().getSelectedItem();
				}
				if(okStanza) {
					String message = "CNCT " + nick + " " + "#000000" + " " + room;
					clientSocket.send(new DatagramPacket(message.getBytes(), message.getBytes().length, IPAddress, SERVER_PORT));
					//RICEZIONE
					DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
					clientSocket.receive(receivePacket);
					receiveMSG = new String(receivePacket.getData(), 0, receivePacket.getLength()).split(" ");
					if(receiveMSG[1].trim().equals("false")) {
						ok = false;
						System.out.println("ALERT: nickname non disponibile");
						Alert alert = new Alert(AlertType.ERROR, "Il nickname non è disponibile!" , ButtonType.OK);
						alert.showAndWait();
					}else {
						clientPort = Integer.parseInt(receiveMSG[2].trim());
					}
					clientSocket.close();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(ok && okStanza) {
			service.start();
			
			tabChat.setDisable(false);
			tabPane.getSelectionModel().select(1);
			txtMsg.requestFocus();
			tabWelcome.setDisable(true);
		}
	}

	public void clickLista() {
		System.out.println(list.getSelectionModel().getSelectedItem());
		list.setOnContextMenuRequested(e -> menu.show(list, e.getScreenX(), e.getScreenY()));
	}
	
	public void clickCopia() {
		ColoredText strRaw = list.getSelectionModel().getSelectedItem();
		String str = strRaw.getText();
		if(str.contains("[") && str.contains("]")) {
			int startIndex = str.indexOf("[");
			int endIndex = str.indexOf("]");
			String replacement = "";
			String toBeReplaced = str.substring(startIndex , endIndex + 3);
			str = str.replace(toBeReplaced, replacement);
		}
	    content.putString(str);
	    clipboard.setContent(content);
	}

	public void clickIncolla() {
		if(clipboard.getString() != null) {
			txtMsg.setText(txtMsg.getText()+clipboard.getString());
		}
	}
	
	public void clickTextA() {
		txtMsg.setOnContextMenuRequested(e -> menuInvia.show(txtMsg, e.getScreenX(), e.getScreenY()));
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
	
	public void clickInvia() {
		String msg;
		coloreFlag = false;
		if(txtMsg.getText().equals("")) {
			txtMsg.requestFocus();
		}else {
			try {
				clientSocket = new DatagramSocket(clientPort);
				String message = "MESG " + room + " " + nick + " " + "#000000" + " " + txtMsg.getText();
				clientSocket.send(new DatagramPacket(message.getBytes(), message.getBytes().length, IPAddress, SERVER_PORT));
				clientSocket.close();
			}catch (Exception e) {
				
			}
			colore = Color.web("#D41800");
			//list.getItems().add("[You]: "+ aCapoAuto(txtMsg.getText()));
			String exploitInvio = "[You]: "+ aCapoAuto(txtMsg.getText());
			Platform.runLater(()-> list.getItems().add(new ColoredText(exploitInvio, Color.web("#D41800"))));
			txtMsg.setText("");
			txtMsg.requestFocus();
			list.scrollTo(list.getItems().size());
			suona(0);
		}
		
		
	}
	
	public void textInvio(KeyEvent ke)
    {
        if (ke.getCode().equals(KeyCode.ENTER))
        {
           clickInvia();
        }
    }
	
	public void cambioSound() {
		if(tgSound.isSelected()) {
			System.out.println("suoni");
			sound = true;
			tgSound.setText("Attivo");
		}else {
			System.out.println("no suoni");
			sound = false;
			tgSound.setText("Disattivo");
		}
	}
	
	public void clickEsci() {
		if(room == null) {
			System.exit(0);
		}else {
			try {
				clientSocket = new DatagramSocket(clientPort);
				String message = "MESG " + room + " " + nick + " " + "#000000" + " " + "/exit";
				clientSocket.send(new DatagramPacket(message.getBytes(), message.getBytes().length, IPAddress, SERVER_PORT));
				clientSocket.close();
			}catch (Exception e) {
				
			}
		}
/*		tabWelcome.setDisable(false);
		tabChat.setDisable(true);
		tabPane.getSelectionModel().select(0);
		list.getItems().clear();*/
	}
	
	private void suona(int i) {
		if(sound) {
			switch (i) {
			case 0:{
				sInvio.play();
			}break;
			case 1:{
				sRicezione.play();
			}break;
			case 2:{
				sSuona.play();
			}break;
			case 3:{
				sMatematica_veloce.play();
			}break;
			default:
				break;
			}
		}
	}
	
	private String aCapoAuto(String raw) {
		String msg = "";
		if(raw.length() > 15) {
	
			String[] arr = raw.split(" ");
	
	
			ArrayList<String> ar = new ArrayList<>(Arrays.asList(arr));
	
			boolean aCapo = true;
			int i = 15;
			while((i + 15) < ar.size()) {
				if(ar.size() > i) {
					ar.add(i, "\n");
					i = i + 15;
				}
			}
			if(ar.size() > 15) {
				ar.add(ar.size() - 15, "\n");
			}
			
			for(int j = 0; j < ar.size(); j++) {
				msg += ar.get(j) + " ";
			}

		}else {
			msg = raw;
		}
		return msg;
	}
	
	public void cmbChatAction() {
		if((((String)cmbChat.getSelectionModel().getSelectedItem()).equals("CREA STANZA"))) {
			txtRoom.setVisible(true);
			txtRoom.requestFocus();
			System.out.println("crea stanza");
			txtRoom.clear();
			isCreate = true;
		}else {
			txtRoom.clear();
			isCreate = false;
			txtRoom.setVisible(false);
		}
	}
}
