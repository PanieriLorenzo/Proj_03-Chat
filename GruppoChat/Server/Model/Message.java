package Model;

import java.util.ArrayList;

public class Message {
	private boolean isSend;
	private boolean isCommand;
	private boolean isConnect;
	private ClientUser sender;
	private String color;
	private Room room;
	private ArrayList<ClientUser> recipients;
	private String message;
	
	public Message(boolean isSend, boolean isConnect){
		this.isSend = isSend;
		this.isConnect = isConnect;
	}
	
	public void toggleSend(){
		this.isSend = true;
	}
	
	public boolean isSend(){
		return isSend;
	}
	
	public void toggleReceive(){
		this.isSend = false;
	}
	
	public boolean isReceive(){
		return !isSend;
	}
	
	public void toggleCommand(){
		this.isCommand = true;
		this.isConnect = false;
	}
	
	public boolean isCommand(){
		return isCommand;
	}
	
	public void toggleMessage(){
		this.isCommand = false;
		this.isConnect = false;
	}
	
	public boolean isMessage(){
		return !isCommand && !isConnect;
	}
	
	public void toggleConnect(){
		this.isConnect = true;
		this.isCommand = false;
	}
	
	public boolean isConnect(){
		return isConnect;
	}

	public ClientUser getSender() {
		return sender;
	}

	public void setSender(ClientUser sender) {
		this.sender = sender;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public ArrayList<ClientUser> getRecipients() {
		return recipients;
	}

	public void setRecipients(ArrayList<ClientUser> recipients) {
		this.recipients = recipients;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
