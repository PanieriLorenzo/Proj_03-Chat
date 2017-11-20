package Model;

import java.util.ArrayList;

public class Room {
	private String name;
	private ArrayList<ClientUser> users;
	
	public Room(String name){
		users = new ArrayList<ClientUser>();
		this.name = name;
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
			if(users.get(i).getNickname().equals(user.getNickname())){
				users.remove(i);
			}
		}
	}
	
	public String toString(){
		return name;
	}

	public boolean contains(ClientUser user) {
		for(int i=0; i<users.size(); i++){
			if(users.get(i).getNickname().equals(user.getNickname())){
				return true;
			}
		}
		return false;
	}
	
	public ClientUser search(String user) {
		ClientUser ret = null;
		for(int i=0; i<users.size(); i++){
			if(users.get(i).getNickname().equals(user)){
				ret = users.get(i);
			}
		}
		return ret;
	}
	
	public void printAll() {
		for(int i=0; i<users.size(); i++) {
			System.out.println(users.get(i));
		}
	}
}
