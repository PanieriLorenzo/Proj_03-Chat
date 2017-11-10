package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Server_frame extends JFrame implements WindowListener {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JList list;
	private DefaultListModel model;
	private JButton btnAvvia;
	private JButton btnChiudi;
	

	/**
	 * Create the frame.
	 */
	public Server_frame() {
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		this.addWindowListener(this);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 207, 262);
		contentPane.add(scrollPane);
		
		list = new JList();
		scrollPane.setViewportView(list);
		
		model = new DefaultListModel();
		list.setModel(model);
		
		btnAvvia = new JButton("Avvia");
		btnAvvia.setBounds(266, 78, 89, 23);
		contentPane.add(btnAvvia);
		
		btnChiudi = new JButton("Chiudi");
		btnChiudi.setEnabled(false);
		btnChiudi.setBounds(266, 112, 89, 23);
		contentPane.add(btnChiudi);
		
		this.setVisible(true);
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		int i = JOptionPane.showConfirmDialog(null, "Vuoi veramente uscire?", "Uscita", JOptionPane.YES_NO_OPTION, 0);
		if(i == JOptionPane.NO_OPTION){
			this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		}else{
			System.exit(0);
		}
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public JList getList() {
		return list;
	}

	public void setList(JList list) {
		this.list = list;
	}

	public DefaultListModel getModel() {
		return model;
	}

	public void setModel(DefaultListModel model) {
		this.model = model;
	}

	public JButton getBtnAvvia() {
		return btnAvvia;
	}

	public void setBtnAvvia(JButton btnAvvia) {
		this.btnAvvia = btnAvvia;
	}

	public JButton getBtnChiudi() {
		return btnChiudi;
	}

	public void setBtnChiudi(JButton btnChiudi) {
		this.btnChiudi = btnChiudi;
	}
	
	
}
