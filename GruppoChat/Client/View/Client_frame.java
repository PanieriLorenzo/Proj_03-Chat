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

public class Client_frame extends JFrame {

	private JPanel contentPane;
	private JLabel lblBenvenuto;
	private JLabel lblComeTiVuoi;
	private JTextField txtNick;
	private JComboBox cmbChat;
	private JLabel lblInCheChat;
	private JButton btnEntra;

	
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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblBenvenuto = new JLabel("Benvenuto!\r\n\r\n");
		lblBenvenuto.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblBenvenuto.setBounds(10, 11, 414, 54);
		contentPane.add(lblBenvenuto);
		
		lblComeTiVuoi = new JLabel("Come ti vuoi chiamare?");
		lblComeTiVuoi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblComeTiVuoi.setBounds(10, 76, 206, 54);
		contentPane.add(lblComeTiVuoi);
		
		txtNick = new JTextField();
		txtNick.setBounds(214, 88, 210, 35);
		contentPane.add(txtNick);
		txtNick.setColumns(10);
		
		cmbChat = new JComboBox();
		cmbChat.setBounds(214, 164, 210, 35);
		contentPane.add(cmbChat);
		
		lblInCheChat = new JLabel("In che chat vuoi entrare?");
		lblInCheChat.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblInCheChat.setBounds(10, 152, 199, 54);
		contentPane.add(lblInCheChat);
		
		btnEntra = new JButton("Entra");
		btnEntra.setBounds(160, 228, 89, 23);
		contentPane.add(btnEntra);
		
		this.setVisible(true);
	}
}
