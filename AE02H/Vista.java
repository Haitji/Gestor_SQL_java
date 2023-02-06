package ejT2.AE02H;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Vista extends JFrame {

	JFrame frame;
	JTextField textField;
	JButton btnTancarConexio;
	JButton btnMostrarInfo;
	JButton btnRealitzarConsulta;
	JButton btnReconectar;
	JTable table_info;
	JTable table_properties;
	JLabel lblTabla;
	public Vista() {
		initialize();
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 937, 685);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		btnTancarConexio = new JButton("Tancar Conexió");
		btnTancarConexio.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnTancarConexio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTancarConexio.setBounds(249, 569, 145, 38);
		frame.getContentPane().add(btnTancarConexio);
		
		btnMostrarInfo = new JButton("Seleccionar tabla");
		btnMostrarInfo.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnMostrarInfo.setBounds(650, 569, 153, 38);
		frame.getContentPane().add(btnMostrarInfo);
		
		btnRealitzarConsulta = new JButton("Realitzar Consulta");
		btnRealitzarConsulta.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRealitzarConsulta.setBounds(650, 511, 153, 38);
		frame.getContentPane().add(btnRealitzarConsulta);
		
		textField = new JTextField();
		textField.setBounds(249, 512, 391, 38);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("CONSULTES A BASE DE DADES");
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 14));
		lblNewLabel.setBounds(47, 513, 220, 31);
		frame.getContentPane().add(lblNewLabel);
		
		btnReconectar = new JButton("Reconectar");
		btnReconectar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnReconectar.setBounds(439, 569, 145, 38);
		frame.getContentPane().add(btnReconectar);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(47, 78, 832, 217);
		frame.getContentPane().add(scrollPane_1);
		
		table_info = new JTable();
		table_info.setEnabled(false);
		table_info.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		scrollPane_1.setViewportView(table_info);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(47, 336, 830, 166);
		frame.getContentPane().add(scrollPane);
		
		table_properties = new JTable();
		table_properties.setEnabled(false);
		scrollPane.setViewportView(table_properties);
		
		JLabel lblNewLabel_1 = new JLabel("Propietats de la taula");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(47, 305, 197, 31);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Informació de la taula");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(47, 47, 197, 31);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("Taula:\r\n");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(299, 10, 68, 31);
		frame.getContentPane().add(lblNewLabel_2);
		
		lblTabla = new JLabel("======\r\n");
		lblTabla.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTabla.setBounds(377, 10, 158, 31);
		frame.getContentPane().add(lblTabla);
		
		this.frame.setVisible(true);
	}
}
