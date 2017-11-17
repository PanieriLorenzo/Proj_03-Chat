package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;

import View.Client_Frame;

public class Control_Client implements ActionListener{
	private Client_Frame f;
	private DatagramSocket clientSocket;
	private InetAddress IPAddress;
	private final int PORT = 9999;
	private byte[] sendBuffer = new byte[1024];
	private byte[] receiveBuffer = new byte[1024];
	private String[] receiveMSG;
	public Control_Client(){
		try {
			IPAddress = InetAddress.getByName("localhost");
			//SPEDIZIONE
			clientSocket = new DatagramSocket();
			String message = "HAND";
			clientSocket.send(new DatagramPacket(message.getBytes(), message.getBytes().length, IPAddress, PORT));
			//RICEZIONE
			DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
			clientSocket.receive(receivePacket);
			receiveMSG = new String(receivePacket.getData(), 0, receivePacket.getLength()).split(" ");
			clientSocket.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//caricare sulla combo box
		f = new Client_Frame();
		f.getBtnEntra().addActionListener(this);
	}
	
//	@Override
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource() == f.getBtnEntra()){
			try {
				clientSocket = new DatagramSocket();
				String nickname = f.getUsername().getText().toLowerCase();
				if(nickname.contains(" ")){
					//errore, reinserisci
				}else{
					String message = "CNCT " + nickname + " " + "#000000 " + (String)f.getComboBox().getSelectedItem();
					clientSocket.send(new DatagramPacket(message.getBytes(), message.getBytes().length, IPAddress, PORT));
				}
				clientSocket.close();
				//lanciare il thread
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}

