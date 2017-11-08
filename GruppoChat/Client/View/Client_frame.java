package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import UI.GUITheme;
import UI.MaterialLookAndFeel;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Client_frame extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JTextField scrittura;
	private JComboBox comboBox;
	private JButton btnEntra;
	private JPanel Welcome;
	private JPanel Chat;
	private JList list;
	private JButton Muta;
	private	JScrollPane scrollPane;
	private JButton Invia;
	private JButton Esci;
	
	/**
	 * Create the frame.
	 */
	public Client_frame() {
		MaterialLookAndFeel ui = new MaterialLookAndFeel(GUITheme.LIGHT_THEME);
		try{
			UIManager.setLookAndFeel(ui.getName());
		}catch(Exception e){
			System.out.println("Errore grafica: "+e.getMessage());
		}
		
		setTitle("Bla Bla Chat");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 364);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 434, 326);
		contentPane.add(tabbedPane);
		
		Welcome = new JPanel();
		tabbedPane.addTab("Benvenuto", null, Welcome, null);
		Welcome.setLayout(null);
		
		JLabel lblBenvenuto = new JLabel("Benvenuto");
		lblBenvenuto.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
		lblBenvenuto.setBounds(141, 11, 197, 26);
		Welcome.add(lblBenvenuto);
		
		JLabel lblComeTiVuoi = new JLabel("Come ti vuoi chiamare?");
		lblComeTiVuoi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblComeTiVuoi.setBounds(26, 92, 206, 14);
		Welcome.add(lblComeTiVuoi);
		
		username = new JTextField();
		username.setBounds(251, 91, 168, 20);
		Welcome.add(username);
		username.setColumns(10);
		
		JLabel lblInCheChat = new JLabel("In che chat vuoi entrare?");
		lblInCheChat.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblInCheChat.setBounds(26, 158, 197, 14);
		Welcome.add(lblInCheChat);
		
		comboBox = new JComboBox();
		comboBox.setBounds(251, 157, 168, 20);
		Welcome.add(comboBox);
		
		btnEntra = new JButton("ENTRA");
		btnEntra.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEntra.setBounds(174, 222, 89, 23);
		Welcome.add(btnEntra);
		
		Chat = new JPanel();
		tabbedPane.addTab("Chat", null, Chat, null);
		Chat.setLayout(null);
		
		scrittura = new JTextField();
		scrittura.setBounds(10, 221, 409, 20);
		Chat.add(scrittura);
		scrittura.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 409, 174);
		Chat.add(scrollPane);
		
		list = new JList();
		scrollPane.setViewportView(list);
		
		Muta = new JButton("Muta\r\n");
		Muta.setBounds(42, 264, 89, 23);
		Chat.add(Muta);
		
		Invia = new JButton("Invia");
		Invia.setBounds(311, 264, 89, 23);
		Chat.add(Invia);
		
		Esci = new JButton("Esci");
		Esci.setBounds(171, 264, 89, 23);
		Chat.add(Esci);
		
		JLabel lblIniziaAChattare = new JLabel("INIZIA A CHATTARE CON I TUOI AMICI");
		lblIniziaAChattare.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIniziaAChattare.setBounds(73, 11, 376, 14);
		Chat.add(lblIniziaAChattare);
		
		this.setVisible(true);
	}

	public JTextField getUsername() {
		return username;
	}

	public void setUsername(JTextField username) {
		this.username = username;
	}

	public JTextField getScrittura() {
		return scrittura;
	}

	public void setScrittura(JTextField scrittura) {
		this.scrittura = scrittura;
	}

	public JComboBox getComboBox() {
		return comboBox;
	}

	public void setComboBox(JComboBox comboBox) {
		this.comboBox = comboBox;
	}

	public JButton getBtnEntra() {
		return btnEntra;
	}

	public void setBtnEntra(JButton btnEntra) {
		this.btnEntra = btnEntra;
	}

	public JPanel getWelcome() {
		return Welcome;
	}

	public void setWelcome(JPanel welcome) {
		Welcome = welcome;
	}

	public JPanel getChat() {
		return Chat;
	}

	public void setChat(JPanel chat) {
		Chat = chat;
	}

	public JList getList() {
		return list;
	}

	public void setList(JList list) {
		this.list = list;
	}

	public JButton getMuta() {
		return Muta;
	}

	public void setMuta(JButton muta) {
		Muta = muta;
	}

	public JButton getInvia() {
		return Invia;
	}

	public void setInvia(JButton invia) {
		Invia = invia;
	}

	public JButton getEsci() {
		return Esci;
	}

	public void setEsci(JButton esci) {
		Esci = esci;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}
	
	
}
