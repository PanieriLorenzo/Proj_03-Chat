//QUESTO E' UN TEST, NON CONINUATE IL SUO SVILUPPO E NON IMPLEMENTATELO

package Main;

import java.util.ArrayList;

import Model.ClientUser;
import Model.Message;
import Model.Room;

public class Test_CommandManager_main {
	private static final String MESSAGGIO = "/lel";	//MODIFICA

	public static void main(String[] args) {
		Room room1 = new Room(), room2 = new Room();
		room1.setName("Globale");
		room2.setName("Privata");
		for(int i=0; i<10; i++){
			ClientUser userA = new ClientUser("a" + i);
			ClientUser userB = new ClientUser("b" + i);
			room1.addUser(userA);
			userB.setRoom(room2);
			room2.addUser(userB);
		}
		//genero messaggio, il mittente (ClientUser) e il messaggio si possono dedurre ed estrarre rispettivamente dal socket
		ClientUser sender = room1.getUser(5);	//dedotto dal socket
		String rawMsg = MESSAGGIO;					//estratto dal socket
		
		//creo il messaggio
		Message msg = new Message(rawMsg, sender);
		
		//processo il messaggio
			//Step 1: genero lista destinatari
		Room room = msg.getSender().getRoom();
		ArrayList<ClientUser> recipients = room.getArray();
		for(int i=0; i<recipients.size(); i++){
			msg.addRecipient(recipients.get(i));
			//togliere il mittente
		}
		
			//Step 2: individua comandi
		String temp = msg.getMessage();
		if(temp.charAt(0) == '/'){
			if(temp.equals("/test")){
				msg.setMessage(msg.getSender().getNickname() + ": TEST!!!");
			}else{
				msg.setMessage("LISTA DI COMANDI:\n/help - mostra questo elenco\n/test - testa i comandi");
				msg.setRecipients(new ArrayList<ClientUser>());
				msg.addRecipient(msg.getSender());
			}
		}else{
			msg.setMessage(msg.getSender().getNickname() + ": " + msg.getMsg());
		}
		
			//Step 3: character stuffing (per ora no)
		
		//Test:
		System.out.println("RECIPIENTS:");
		for(int i=0; i<msg.arraySize();i++){
			System.out.println(msg.getRecipient(i).getName());
		}
		System.out.println("MESSAGE:");
		System.out.println(msg.getMsg());
		
		

	}

}
