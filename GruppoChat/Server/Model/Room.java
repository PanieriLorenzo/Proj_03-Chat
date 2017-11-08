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
	
	public void addUser(ClientUser user){
		users.add(user);
	}
	
	public ClientUser getUser(int i){
		return users.get(i);
	}
	
	public ArrayList<ClientUser> getArray(){
		return users;
	}
	
	public void removeUser(ClientUser user){
		for(int i=0; i<users.size(); i++){
			if(users.get(i).getNome() == user.getNome()){
				users.remove(i);
			}
		}
	}
}