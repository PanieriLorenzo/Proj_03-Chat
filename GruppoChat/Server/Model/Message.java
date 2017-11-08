package Model;

import java.util.ArrayList;

public class Message {
	private String msg;
	private ClientUser sender;
	private ArrayList<ClientUser> recipients;
	private String room;
	
	public Message(String msg, ClientUser sender){
		this.msg = msg;
		this.sender = sender;
		this.recipients = new ArrayList<ClientUser>();
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public ClientUser getSender() {
		return sender;
	}

	public void setSender(ClientUser sender) {
		this.sender = sender;
	}
	
	public void addRecipient(ClientUser recipient){
		recipients.add(recipient);
	}
	
	public ClientUser getRecipient(int i){
		return recipients.get(i);
	}
	
	public void clearRecipients(){
		recipients.clear();
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}
	
	
}
