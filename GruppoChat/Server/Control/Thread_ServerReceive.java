package Control;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import Model.Message;

public class Thread_ServerReceive extends Thread{
	@Override
	public void run() {
		System.out.println("lel");
		byte[] buf = new byte[256];
		try{
			DatagramSocket serverSocket = new DatagramSocket(ThreadAttributes_ServerReceive.port);
		}catch (Exception e){
			e.printStackTrace();
		}
	}	
}
