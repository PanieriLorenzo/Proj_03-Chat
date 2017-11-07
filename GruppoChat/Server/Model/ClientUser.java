package Model;

import java.net.Socket;

public class ClientUser {
	private String nome;
	private Socket s;
	
	public ClientUser(String nome, Socket s) {
		super();
		this.nome = nome;
		this.s = s;
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
	
	
}