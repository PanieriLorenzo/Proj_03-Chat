package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class Control_graph implements ActionListener{
	DatagramSocket clientSocket;
	InetAddress ipAddress;
	
	public Control_graph() throws Exception{
		clientSocket = new DatagramSocket();
		ipAddress = InetAddress.getByName("localhost");
		
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub	
	}
}

class ThreadAscolto extends Thread{
	
}
