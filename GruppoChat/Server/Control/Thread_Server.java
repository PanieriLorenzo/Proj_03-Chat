package Control;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

import Model.ClientUser;
import Model.Message;

public class Thread_Server extends Thread{
	@Override
	public void run() {
		System.out.println("Thread_Server avviato");
		byte[] receiveBuffer = new byte[1024];
		byte[] sendBuffer = new byte[1024];
		DatagramPacket receivePacket;
		DatagramPacket sendPacket;
		String[] receiveRawMSG;
		StringBuilder sendRawMSG = new StringBuilder();
		Message message = null;
		InetAddress tempAddress = null;
		int tempPort = 0;
		System.out.println("Variabili inizializzate");
		
		try{
			DatagramSocket serverSocket = new DatagramSocket(ThreadAttributes_Server.port);
			System.out.println("TRY> serverSocket creato on porta: " + ThreadAttributes_Server.port);
			for(;;){
				System.out.println("TRY> FOR> Ricezione...");
/*				receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
				serverSocket.receive(receivePacket);
				tempAddress = receivePacket.getAddress();
				tempPort = receivePacket.getPort();
				receiveRawMSG = (new String(receivePacket.getData())).split(" ");*/
				//TEST
				receiveRawMSG = new String[1];
				System.out.println("TRY> FOR> inizializzato receiveRawMSG");
				receiveRawMSG[0] = "HAND";
				System.out.println("TRY> FOR> impostata la prima parola di receiveRawMSG a HAND");
				//FINE TEST
				System.out.println("TRY> FOR> ricevuto pacchetto con indirizzo: " + tempAddress + "/" + tempPort);
				System.out.println("\te messaggio: " + receiveRawMSG[0]);
				
				if(receiveRawMSG[0].equals("HAND")){
					System.out.println("TRY> FOR> HAND> Gnerazione handshake...");
					message = new Message(true);
					message.toggleHanshake();
					message.setMessage(ThreadAttributes_Server.manager.toString());
					System.out.println("TRY> FOR> HAND> Generato sendMSG con messaggio: " + ThreadAttributes_Server.manager.toString());
				}else if(receiveRawMSG[0].equals("CNCT")){
					
				}else if(receiveRawMSG[0].equals("MESG")){
					
				}else{
					System.out.println("Errore: Header messaggio errato/corrotto");
					throw new Exception();
				}
				System.out.println("TRY> FOR> Ricezione completata!");
				
				System.out.println("TRY> FOR> Spedizione...");
				if(message.isHandshake()){
					System.out.println("TRY> FOR> HAND> Spedizione handshake...");
					sendRawMSG.append("HAND ");
					sendRawMSG.append(message.getMessage());
					sendBuffer = sendRawMSG.toString().getBytes();
//					sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, tempAddress, tempPort);
					System.out.println("TRY> FOR> inviato pacchetto ad indirizzo: " + tempAddress + "/" + tempPort);
					System.out.println("\te messaggio: " + sendRawMSG.toString());
				}else if(message.isCommand()){
					
				}else if(message.isMessage()){
					
				}else{
					System.out.println("Errore: Header messaggio errato/corrotto");
					throw new Exception();
				}
				System.out.println("TRY> FOR> Spedizione completata!");
				System.exit(0);
			}
		}catch (Exception e){
			System.out.println("ERRORE: Thread_Server");
			e.printStackTrace();
		}
	}	
}
