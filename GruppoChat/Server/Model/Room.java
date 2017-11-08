package Model;

import java.util.ArrayList;

public class Room {
	private String name;
	private ArrayList<ClientUser> users;
	
	public Room(){
		users = new ArrayList<ClientUser>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<ClientUser> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<ClientUser> users) {
		this.users = users;
	}
}
