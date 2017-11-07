package Model;

import java.net.Socket;

public class ClientUser {
	private String nome;
	private Socket s;
	private String room;
	
	public ClientUser(String nome, Socket s, String room) {
		super();
		this.nome = nome;
		this.s = s;
		this.room = room;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Socket getS() {
		return s;
	}

	public void setS(Socket s) {
		this.s = s;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}
	
	
}
