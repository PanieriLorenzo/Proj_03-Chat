package Control;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;

import Model.ClientUser;
import Model.Message;

public class Thread_ServerReceive extends Thread{
	@Override
	public void run() {
		System.out.println("lel");
		byte[] receiveBuffer = new byte[1024];
		try{
			DatagramSocket serverSocket = new DatagramSocket(ThreadAttributes_ServerReceive.port);
			for(;;){
				DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
				serverSocket.receive(receivePacket);
				String[] receiveRawMSG = (new String(receivePacket.getData())).split(" ");
				Message receiveMSG = new Message(false);
				if(receiveRawMSG[0].equals("HAND")){
					receiveMSG.toggleHanshake();
					Message sendMSG = new Message(true);
					sendMSG.toggleHanshake();
					//fetch lista stanze dal manager e caricala sul messaggio
					ThreadAttributes_ServerReceive.sendMSG = sendMSG;
				}else if(receiveRawMSG[0].equals("CNCT")){
					
				}else if(receiveRawMSG[0].equals("MESG")){
					
				}else{
					System.out.println("Errore: Header messaggio errato/corrotto");
					throw new Exception();
				}
				
				
			}
		}catch (Exception e){
			System.out.println("ERRORE: Thread_ServerReceive");
			e.printStackTrace();
		}
	}	
}
