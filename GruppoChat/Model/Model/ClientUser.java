package Model;

import java.net.InetAddress;
import java.net.Socket;

import javafx.scene.paint.Color;

public class ClientUser {
	private String nickname;
	private Room room;
	private Color color;
	private InetAddress address;
	private int port;
	
	public ClientUser(String nickname){
		this.nickname = nickname;
		this.room = null;
		this.color = null;
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
	
	public String toString() {
		return nickname;
	}
	
	public void setColor(String hex) {
		this.color = Color.web(hex);
	}
	
	public String getColor() {
		return String.format( "#%02X%02X%02X",
				(int)( color.getRed() * 255 ),
	            (int)( color.getGreen() * 255 ),
	            (int)( color.getBlue() * 255 ) );
	}

	public void setAddress(InetAddress address) {
		this.address = address;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	public InetAddress getAddress() {
		return address;
	}
	
	public int getPort() {
		return port;
	}

	public void debugPrint() {
		System.out.println("NICK:" + this.nickname);
		System.out.println("PORT:" + this.port);
		System.out.println("IP:" + this.address);
		System.out.println("COLOR:" + this.color);
		System.out.println("ROOM:" + this.room);
		
	}
}
