package Model;

import java.net.Socket;

public class ClientUser {
	private String name;
	private Socket s;
	private Room room;
	
	public ClientUser(String nome, Socket s) {
		super();
		this.name = nome;
		this.s = s;
	}

	public String getNome() {
		return name;
	}

	public void setNome(String nome) {
		this.name = nome;
	}

	public Socket getS() {
		return s;
	}

	public void setS(Socket s) {
		this.s = s;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
	
	
}
