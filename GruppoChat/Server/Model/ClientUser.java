package Model;

import java.net.Socket;

public class ClientUser {
	private String nickname;
	private Room room;
	
	public ClientUser(String nickname){
		this.nickname = nickname;
		this.room = null;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
}
