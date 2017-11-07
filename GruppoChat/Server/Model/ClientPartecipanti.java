package Model;

import java.net.Socket;

public class ClientPartecipanti {
	private String nome;
	private Socket s;
	
	public ClientPartecipanti(String nome, Socket s) {
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
