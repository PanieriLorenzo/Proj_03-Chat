package Main;

import Control.Control;
import Model.Room_Manager;
import View.Server_frame;

public class Server_main {
	public static void main(String[] args){
		Server_frame f = new Server_frame();
		Room_Manager r = new Room_Manager();
		Control c = new Control(f, r);
	}
}