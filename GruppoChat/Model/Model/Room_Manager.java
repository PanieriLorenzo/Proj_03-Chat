package Model;

import java.util.ArrayList;

public class Room_Manager {
	private ArrayList<Room> rooms;
	
	public Room_Manager(){
		rooms = new ArrayList<Room>();
	}

	public ArrayList<Room> getRooms() {
		return rooms;
	}

	public void setRooms(ArrayList<Room> rooms) {
		this.rooms = rooms;
	}
	
	public void addRoom(Room room){
		rooms.add(room);
	}
	
	public String toString(){
		StringBuilder ret = new StringBuilder();
		for(int i=0; i<rooms.size(); i++){
			ret.append(rooms.get(i) + " ");
		}
		ret.deleteCharAt(ret.length()-1);
		return ret.toString();
	}

	public Room search(String room) {
		Room ret = null;
		for(int i=0; i<rooms.size(); i++) {
			if(rooms.get(i).toString().equals(room)) {
				ret = rooms.get(i);
			}
		}
		return ret;
	}

	public void removeRoom(String room) {
		for(int i=0; i<rooms.size(); i++) {
			if(rooms.get(i).toString().equals(room)) {
				rooms.remove(i);
			}
		}
	}
}
