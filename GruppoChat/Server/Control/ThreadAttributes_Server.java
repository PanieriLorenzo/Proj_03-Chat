package Control;

import Model.ClientUser;
import Model.Message;
import Model.Room_Manager;

public class ThreadAttributes_Server {
	public static final ClientUser NEW_USER = new ClientUser("NEW_USER");
	public static final int port = 9999;
	public static Room_Manager manager;
}