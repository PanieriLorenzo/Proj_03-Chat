package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
	private JTextField textField;

	
	/**
	 * Create the frame.
	 */
	public Client_frame() {
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
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
		
		JPanel Welcome = new JPanel();
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
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(251, 157, 168, 20);
		Welcome.add(comboBox);
		
		JButton btnEntra = new JButton("ENTRA");
		btnEntra.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEntra.setBounds(174, 222, 89, 23);
		Welcome.add(btnEntra);
		
		JPanel Chat = new JPanel();
		tabbedPane.addTab("Chat", null, Chat, null);
		Chat.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 221, 409, 20);
		Chat.add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 409, 174);
		Chat.add(scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		
		JButton btnNewButton = new JButton("Muta\r\n");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(42, 264, 89, 23);
		Chat.add(btnNewButton);
		
		JButton btnInvia = new JButton("Invia");
		btnInvia.setBounds(311, 264, 89, 23);
		Chat.add(btnInvia);
		
		JButton btnEsci = new JButton("Esci");
		btnEsci.setBounds(171, 264, 89, 23);
		Chat.add(btnEsci);
		
		JLabel lblIniziaAChattare = new JLabel("INIZIA A CHATTARE CON I TUOI AMICI");
		lblIniziaAChattare.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIniziaAChattare.setBounds(73, 11, 376, 14);
		Chat.add(lblIniziaAChattare);
		
		
		
		this.setVisible(true);
	}
}
