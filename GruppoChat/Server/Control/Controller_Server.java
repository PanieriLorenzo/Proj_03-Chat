package Control;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.io.File;
import java.lang.reflect.Array;

import org.omg.CORBA.INITIALIZE;

import Model.ClientUser;
import Model.Message;
import Model.Room;
import Model.Room_Manager;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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

public class Controller_Server implements Initializable{
	//ATTRIBUTI CONNETTIVITA':
	Room_Manager r;
//	Thread_Server thread; 
	
	//ATTRIBUTI GRAFICA:
	@FXML Button btnAvvia;
	@FXML Button btnTermina;
	@FXML ListView<String> lista;
	
	//ATTRIBUTI THREAD:
    Service<Void> service;
    private final ClientUser NEW_USER = new ClientUser("NEW_USER");
	private final int port = 9999;
	private Room_Manager manager;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//INIT SERVER:
		this.r = new Room_Manager();
		
		r.addRoom(new Room("Generale"));
		r.addRoom(new Room("Test"));
		
		//INIT GRAFICA:
		btnTermina.setDisable(true);
		
		//INIT THREAD:
		service = new Service<Void>() {			
			@Override
			protected Task<Void> createTask() {
				return new Task<Void>() {
					@Override
					protected Void call() throws Exception {
						System.out.println("Thread_Server avviato");
						String[] colors = {"#18206f","#376996","#d41800","#353535","#86b541"};
						byte[] receiveBuffer = new byte[1024];
						byte[] sendBuffer = new byte[1024];
						DatagramPacket receivePacket;
						DatagramPacket sendPacket;
						String[] receiveRawMSG;
						StringBuilder sendRawMSG; 
						Message message = null;
						InetAddress tempAddress = null;
						int tempPort = 0;
						System.out.println("Variabili inizializzate");
						
						try{
							DatagramSocket serverSocket = new DatagramSocket(port);
							System.out.println("TRY> serverSocket creato on porta: " + port);
							for(;;){
								System.out.println("TRY> FOR> Ricezione...");
								receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
								serverSocket.receive(receivePacket);
								tempAddress = receivePacket.getAddress();
								tempPort = receivePacket.getPort();
								receiveRawMSG = (new String(receivePacket.getData())).split(" ");
/*								//TEST
								receiveRawMSG = ("MESG lol #000000 Generale tua madre").split(" ");
								System.out.println("TRY> FOR> inizializzato receiveRawMSG");*/
								//FINE TEST
								System.out.println("TRY> FOR> ricevuto pacchetto con indirizzo: " + tempAddress + ":" + tempPort);
								System.out.println("\te messaggio: " + receiveRawMSG[0].trim());
								
								if(receiveRawMSG[0].trim().equals("HAND")){
									System.out.println("TRY> FOR> HAND> Gnerazione handshake...");
									message = new Message(true);
									message.toggleHanshake();
									message.setMessage(manager.toString());
									System.out.println("TRY> FOR> HAND> Generato sendMSG con messaggio: " + manager.toString());
								}else if(receiveRawMSG[0].trim().equals("CNCT")){
									System.out.println("TRY> FOR> CNCT> Gnerazione connect...");
									message = new Message(true);
									message.toggleConnect();
									Room tempRoom = manager.search(receiveRawMSG[3].trim());
									System.out.println("Ricercato :" + receiveRawMSG[3].trim());
									System.out.println("Trovato :" + tempRoom);
									ClientUser tempUser = new ClientUser(receiveRawMSG[1].trim());
									System.out.println("Ricercato :" + receiveRawMSG[1].trim());
									System.out.println("Trovato :" + tempUser);
									tempUser.setRoom(tempRoom);
									tempUser.setAddress(tempAddress);
									tempUser.setPort(tempPort);
/*									for(int i=0;i<100;i++) {
										System.out.println("" + colors[((int)(Math.random()*1000))%5]);
									}*/
									tempUser.setColor(colors[((int)(Math.random()*1000))%5]);
									if(tempRoom == null) {
										tempRoom = new Room(receiveRawMSG[3].trim());
										tempUser.setRoom(tempRoom);
										tempRoom.addUser(tempUser);
										manager.addRoom(tempRoom);
										String exploitTempRoom = tempRoom.toString();
										Platform.runLater(()->lista.getItems().add("creata stanza [" + exploitTempRoom + "]"));
										message.setMessage("CNCT true " + tempPort);
										Platform.runLater(()->lista.getItems().add("["+ tempUser + "] entrato in [" + exploitTempRoom + "]"));
										
									}else{
										if(tempRoom.contains(tempUser)) {
											message.setMessage("CNCT false");
										}else{
											manager.search(receiveRawMSG[3].trim()).addUser(tempUser);
											String exploitTempRoom = tempRoom.toString();
											Platform.runLater(()->lista.getItems().add("["+ tempUser + "] entrato in [" + exploitTempRoom + "]"));
											message.setMessage("CNCT true " + tempPort);
										}
									}
									
								}else if(receiveRawMSG[0].trim().equals("MESG")){
									System.out.println("TRY> FOR> MESG> Gnerazione messaggio/comando...");
									message = new Message(true);
									Room tempRoom = manager.search(receiveRawMSG[1].trim());
									System.out.println("Ricercato :" + receiveRawMSG[1].trim());
									System.out.println("Trovato :" + tempRoom);
									ClientUser tempUser = tempRoom.search(receiveRawMSG[2].trim());
									
									System.out.println(receiveRawMSG[4].trim().charAt(0));
									if(receiveRawMSG[4].trim().charAt(0) == '/') {
										System.out.println(receiveRawMSG[4].trim());
										//COMANDO
										message.toggleCommand();
										if(receiveRawMSG[4].trim().equals("/ring")) {
											//RING:
											ClientUser tempUser2 = tempRoom.search(receiveRawMSG[5].trim());
											if(tempUser2 != null) {
												ArrayList<ClientUser> tempArray = new ArrayList<ClientUser>();
												tempArray.add(tempUser2);
												message.setRecipients(tempArray);
												message.setMessage("~§r;");
											}else {
												ArrayList<ClientUser> tempArray = new ArrayList<ClientUser>();
												tempArray.add(tempUser);
												message.setRecipients(tempArray);
												message.setMessage("[SERVER-BOT]: Utente non trovato");
											}
										}else if(receiveRawMSG[4].trim().equals("/quick_maths")) {
											//2+2=4, 4-1=3, quick maths!:
											ClientUser tempUser2 = tempRoom.search(receiveRawMSG[5].trim());
											if(tempUser2 != null) {
												ArrayList<ClientUser> tempArray = new ArrayList<ClientUser>();
												tempArray.add(tempUser2);
												message.setRecipients(tempArray);
												message.setMessage("~§q;");
											}else {
												ArrayList<ClientUser> tempArray = new ArrayList<ClientUser>();
												tempArray.add(tempUser);
												message.setRecipients(tempArray);
												message.setMessage("[SERVER-BOT]: Utente non trovato");
											}
										}else if(receiveRawMSG[4].trim().equals("/whisper")) {
											//WHISPER:
											ClientUser tempUser2 = tempRoom.search(receiveRawMSG[5].trim());
											StringBuilder tempMSG = new StringBuilder();
											for(int i=6; i<receiveRawMSG.length;i++) {
												tempMSG.append(receiveRawMSG[i].trim() + " ");
											}
											if(tempUser2 != null) {
												ArrayList<ClientUser> tempArray = new ArrayList<ClientUser>();
												tempArray.add(tempUser2);
												message.setRecipients(tempArray);
												message.setMessage("[WHISPER/" + tempUser +"]: " + tempMSG);
											}else {
												ArrayList<ClientUser> tempArray = new ArrayList<ClientUser>();
												tempArray.add(tempUser);
												message.setRecipients(tempArray);
												message.setMessage("[SERVER-BOT]: Utente non trovato");
											}
										}else if(receiveRawMSG[4].trim().equals("/exit")) {
											//EXIT:
											message.toggleExit();
											ArrayList<ClientUser> tempArray = new ArrayList<ClientUser>();
											tempArray.add(tempUser);
											message.setRecipients(tempArray);
											message.setRoom(tempRoom);
										}else {
											//HELP:
											ArrayList<ClientUser> tempArray = new ArrayList<ClientUser>();
											tempArray.add(tempUser);
											message.setRecipients(tempArray);
											message.setMessage("[SERVER-BOT]: Ecco una lista dei comandi:\n"
													+ "\t/help  -  mostra l'elenco dei comandi\n"
													+ "\t/ring [nome_utente]  -  fa suonare il programma dell'utente specificato\n"
													+ "\t/whisper [nome_utente]  -  invia un messaggio privato all'utente specificato\n"
													+ "\t/exit  -  esce dalla stanza corrente");
										}
									}else {
										//MESSAGGIO
										message.toggleMessage();
										message.setRoom(tempRoom);
										message.setSender(tempUser);
										message.setColor(tempUser.getColor());
										StringBuilder tempMSG = new StringBuilder();
										for(int i=4; i<receiveRawMSG.length;i++) {
											tempMSG.append(receiveRawMSG[i].trim() + " ");
										}
										message.setMessage(tempMSG.toString());
									}
								}else{
									System.out.println("Errore: Header messaggio errato/corrotto");
									throw new Exception();
								}
								receiveBuffer = new byte[1024];
								System.out.println("TRY> FOR> Ricezione completata!");
								
								System.out.println("TRY> FOR> Spedizione...");
								if(message.isHandshake()){
									System.out.println("TRY> FOR> HAND> Spedizione handshake...");
									sendRawMSG = new StringBuilder();
									sendRawMSG.append("HAND ");
									sendRawMSG.append(message.getMessage());
									sendBuffer = sendRawMSG.toString().getBytes();
									sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, tempAddress, tempPort);
									serverSocket.send(sendPacket);
									System.out.println("TRY> FOR> inviato pacchetto ad indirizzo: " + tempAddress + "/" + tempPort);
									System.out.println("\te messaggio: " + sendRawMSG.toString());
									sendRawMSG = null;
								}else if(message.isConnect()){
									System.out.println("TRY> FOR> CNCT> Spedizione connect...");
									sendRawMSG = new StringBuilder();
									sendRawMSG.append(message.getMessage());
									sendBuffer = sendRawMSG.toString().getBytes();
									sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, tempAddress, tempPort);
									serverSocket.send(sendPacket);
									System.out.println("TRY> FOR> inviato pacchetto ad indirizzo: " + tempAddress + "/" + tempPort);
									System.out.println("\te messaggio: " + sendRawMSG.toString());
								}else if(message.isCommand()){
									System.out.println("TRY> FOR> COMD> Spedizione command...");
									sendRawMSG = new StringBuilder();
									sendRawMSG.append("COMD ");
									sendRawMSG.append(message.getSender() + " ");
									sendRawMSG.append(message.getMessage());
									sendBuffer = sendRawMSG.toString().getBytes();
									//sendToAll(message.getRoom(), sendBuffer);
									for(int i=0; i<message.getRecipients().size(); i++){
										message.getRecipients().get(i).debugPrint();
										sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, message.getRecipients().get(i).getAddress(), message.getRecipients().get(i).getPort()-20000);
										System.out.println("Creato pacchetto con indirizzo: " + message.getRecipients().get(i).getAddress() + " e port: " + (message.getRecipients().get(i).getPort()-20000));
										System.out.println("\te messaggio: " + sendRawMSG.toString());
										serverSocket.send(sendPacket);
										System.out.println("TRY> FOR> inviato pacchetto ad indirizzo: " + message.getRecipients().get(i).getAddress() + "/" + (message.getRecipients().get(i).getPort()-20000));
										System.out.println("\te messaggio: " + sendRawMSG.toString());
									}
								}else if(message.isMessage()){
									System.out.println("TRY> FOR> MESG> Spedizione message...");
									sendRawMSG = new StringBuilder();
									sendRawMSG.append("MESG ");
									sendRawMSG.append(message.getSender() + " ");
									sendRawMSG.append(message.getColor() + " ");
									sendRawMSG.append(message.getRoom() + " ");
									sendRawMSG.append(message.getMessage());
									sendBuffer = sendRawMSG.toString().getBytes();
									//sendToAll(message.getRoom(), sendBuffer);
									for(int i=0; i<message.getRoom().getArray().size(); i++){
										message.getRoom().getUser(i).debugPrint();
										if(!message.getRoom().getUser(i).getNickname().equals(message.getSender().getNickname())) {
											sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, message.getRoom().getUser(i).getAddress(), message.getRoom().getUser(i).getPort()-20000);
											System.out.println("Creato pacchetto con indirizzo: " + message.getRoom().getUser(i).getAddress() + " e port: " + (message.getRoom().getUser(i).getPort()-20000));
											System.out.println("\te messaggio: " + sendRawMSG.toString());
											serverSocket.send(sendPacket);
											System.out.println("TRY> FOR> inviato pacchetto ad indirizzo: " + message.getRoom().getUser(i).getAddress() + "/" + (message.getRoom().getUser(i).getPort()-20000));
											System.out.println("\te messaggio: " + sendRawMSG.toString());
										}
									}
								}else if(message.isExit()){
									System.out.println("TRY> FOR> EXIT> Spedizione exit...");
									sendBuffer = "EXIT".getBytes();
									//sendToAll(message.getRoom(), sendBuffer);
									message.getRecipients().get(0).debugPrint();
									sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, message.getRecipients().get(0).getAddress(), message.getRecipients().get(0).getPort()-20000);
									System.out.println("Creato pacchetto con indirizzo: " + message.getRecipients().get(0).getAddress() + " e port: " + (message.getRecipients().get(0).getPort()-20000));
									System.out.println("\te messaggio: " + "EXIT");
									serverSocket.send(sendPacket);
									System.out.println("TRY> FOR> inviato pacchetto ad indirizzo: " + message.getRecipients().get(0).getAddress() + "/" + (message.getRecipients().get(0).getPort()-20000));
									System.out.println("\te messaggio: " + "EXIT");
									message.getRoom().removeUser(message.getRecipients().get(0));
									message.getRoom().printAll();
									String exploitTempRoom = message.getRoom().toString();
									String exploitTempUser = message.getRecipients().get(0).toString();
									Platform.runLater(()->lista.getItems().add("["+ exploitTempUser + "] uscito da [" + exploitTempRoom + "]"));
									if(!message.getRoom().getName().equals("Generale") && message.getRoom().getArray().size()==0) {
										manager.removeRoom(message.getRoom().toString());
									}
								}else{
									System.out.println("Errore: Header messaggio errato/corrotto");
									throw new Exception();
								}
								sendRawMSG = null;
								sendBuffer = new byte[1024];
								System.out.println("TRY> FOR> Spedizione completata!");
							}
						}catch (Exception e){
							System.out.println("ERRORE: Thread_Server");
							e.printStackTrace();
						}
						return null;
					}
				};
			}
		};
	}
    
	public void avviaServer() {
		lista.getItems().add("avvio");
		btnAvvia.setDisable(true);
//		thread = new Thread_Server();
		manager = r;
		System.out.println("Inizializzato il thread");
//		thread.start();
		service.start();
		System.out.println("Avviato il thread");
		btnTermina.setDisable(false);
	}
	
	public void terminaServer() {
		lista.getItems().add("termina");
		btnTermina.setDisable(true);
		System.exit(0);
		btnAvvia.setDisable(false);
	}
	
	
}
