package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.Room_Manager;
import View.Server_frame;

public class Control implements ActionListener{
	Server_frame f;
	Room_Manager r;
	
	public Control(Server_frame f, Room_Manager r){
		this.f = f;
		this.r = r;
		
		f.getBtnAvvia().addActionListener(this);
		f.getBtnChiudi().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		if(evt.getSource() == f.getBtnAvvia()){
			f.getBtnAvvia().setEnabled(false);
			Thread_Server thread = new Thread_Server();
			ThreadAttributes_Server.manager = r;
			thread.start();
			f.getBtnChiudi().setEnabled(true);	
		}
		if(evt.getSource() == f.getBtnChiudi()){
			f.getBtnChiudi().setEnabled(false);	
			
			f.getBtnAvvia().setEnabled(true);
		}
		
	}
	
	
	

}
