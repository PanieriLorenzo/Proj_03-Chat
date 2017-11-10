package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.Server_frame;

public class Control implements ActionListener{
	Server_frame f;
	
	public Control(Server_frame f){
		this.f = f;
		
		f.getBtnAvvia().addActionListener(this);
		f.getBtnChiudi().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		if(evt.getSource() == f.getBtnAvvia()){
			f.getBtnAvvia().setEnabled(false);
			
			f.getBtnChiudi().setEnabled(true);	
		}
		if(evt.getSource() == f.getBtnChiudi()){
			f.getBtnChiudi().setEnabled(false);	
			
			f.getBtnAvvia().setEnabled(true);
		}
		
	}
	
	
	

}
