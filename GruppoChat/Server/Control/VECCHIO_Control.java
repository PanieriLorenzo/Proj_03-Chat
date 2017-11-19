package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.Room;
import Model.Room_Manager;
import View.Server_frame;

public class VECCHIO_Control implements ActionListener{
	Server_frame f;
	Room_Manager r;
	
	public VECCHIO_Control(){
		this.f = new Server_frame();
		this.r = new Room_Manager();
		
		r.addRoom(new Room("Generale"));
		r.addRoom(new Room("Test"));
		
		f.getBtnAvvia().addActionListener(this);
		f.getBtnChiudi().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource() == f.getBtnAvvia()){
			System.out.println("Premuto btnAvvia");
			f.getBtnAvvia().setEnabled(false);
			Thread_Server thread = new Thread_Server();
			ThreadAttributes_Server.manager = r;
			System.out.println("Inizializzato il thread");
			thread.start();
			System.out.println("Avviato il thread");
			f.getBtnChiudi().setEnabled(true);	
		}
		if(evt.getSource() == f.getBtnChiudi()){
			f.getBtnChiudi().setEnabled(false);	
			
			f.getBtnAvvia().setEnabled(true);
		}	
	}
}
