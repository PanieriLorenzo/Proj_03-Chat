package Control;

import Model.Message;

public class Control_text_srvr {
	private static final int PORT = 9999;
	public static void main (String[] args){
		Thread_ServerReceive threadReceive = new Thread_ServerReceive();
		threadReceive.start();
		for(;;){
			Message sendMSG = ThreadAttributes_ServerReceive.sendMSG;
			String sendRawMSG = serialize(sendMSG);
		}
	}
	
	private static String serialize(Message sendMSG) {
		return null;
	}
}
