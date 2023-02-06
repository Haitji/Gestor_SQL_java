package ejT2.AE02H;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Controlador {
	private static Vista vista;
	private static Model model;
	
	
	public Controlador(Vista vista, Model model) {
		this.vista = vista;
		this.model = model;
		try {
			InitEventHandler();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void clearTable(JTable table) {
		table.setModel(new DefaultTableModel());
	}
	
	private void InitEventHandler() {

		/**
		 * Programa el botó per a visualitzar una taula donantli a elegir entre aquestes
		 */
		ActionListener escucha = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tablas = model.nomTablas(model.base);
				String[] opciones = tablas.split(" ");
		        String tabla = (String)JOptionPane.showInputDialog(null, "Nom de la taula a visualitzar", 
		                "Elegir taula", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
				String text = model.mostrarContingut(tabla, model.numColumnas(tabla));
				DefaultTableModel tableModel=(DefaultTableModel)vista.table_info.getModel();
				DefaultTableModel tableModel2=(DefaultTableModel)vista.table_properties.getModel();
				try {
					tableModel=model.rellenarTabla("Select * from "+tabla+";");
					tableModel2=model.rellenarTabla("SHOW COLUMNS FROM "+tabla+";");
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				vista.table_info.setModel(tableModel);
				vista.table_properties.setModel(tableModel2);
				vista.lblTabla.setText(tabla);
			}
		};
		
		/**
		 * Programa el botó per a tancar la sesió del programa
		 */
		ActionListener cerrarS = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.cierraConexion();
				if(model.conexion == null) {
					vista.btnTancarConexio.setEnabled(false);
					vista.btnReconectar.setEnabled(true);
				}else {
					vista.btnTancarConexio.setEnabled(false);
					vista.btnReconectar.setEnabled(true);
					vista.btnMostrarInfo.setEnabled(false);
					vista.btnRealitzarConsulta.setEnabled(false);
				}
				clearTable(vista.table_properties);
				clearTable(vista.table_info);
				vista.textField.setEnabled(false);
				vista.textField.setText("");
				vista.lblTabla.setText("");
			}
		};
		
		/**
		 * Programa el botó d'obrir sesió i nou login
		 */
		ActionListener abrirS = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(model.Conexion() != null) {
						try {
							do {
								
							}while(login() == false);
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "Error al conectarse");
						}
						vista.btnTancarConexio.setEnabled(true);
						vista.btnReconectar.setEnabled(false);
						vista.btnMostrarInfo.setEnabled(true);
						vista.btnRealitzarConsulta.setEnabled(true);
						JOptionPane.showMessageDialog(null, "Conexió oberta");
					}else {
						
					}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParserConfigurationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SAXException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				vista.textField.setEnabled(true);
			}
		};
		
		/**
		 * Programa el botó per a realitzar consultes
		 */		
		ActionListener realizarConsulta = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(model.comprobarConsulta(vista.textField.getText().toUpperCase())==0) {
					   JOptionPane.showMessageDialog(null, "Camp buit");
				}else if(model.comprobarConsulta(vista.textField.getText().toUpperCase())==1) {
			        	clearTable(vista.table_info);
			        	clearTable(vista.table_properties);
			        	DefaultTableModel tableModel=(DefaultTableModel)vista.table_info.getModel();
						DefaultTableModel tableModel2=(DefaultTableModel)vista.table_properties.getModel();
						try {
							tableModel=model.rellenarTabla(vista.textField.getText().toUpperCase());
							String tabla=model.buscarTablas(vista.textField.getText().toUpperCase());
							tableModel2=model.rellenarTabla("SHOW COLUMNS FROM "+tabla+";");
							vista.lblTabla.setText(tabla);
							
						} catch (SQLException e1) {
							e1.printStackTrace();
						}

						vista.table_info.setModel(tableModel);
						vista.table_properties.setModel(tableModel2);
			        	vista.textField.setText("");
					}
			        else if(model.comprobarConsulta(vista.textField.getText().toUpperCase())==2){
			        	int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro?", "Alerta!", JOptionPane.YES_NO_OPTION);
			        	if(resp==0) {
			        		if(vista.textField.getText().toUpperCase().contains("INSERT INTO USERS")) {
			        			String passw=model.extraerContrasena(vista.textField.getText());
			        			String consulta = model.sustituirEncrypt(vista.textField.getText(), passw);
			        			model.realizarCIUD(consulta);
			        		}else {
					        	model.realizarCIUD(vista.textField.getText());
			        		}
			        		clearTable(vista.table_info);
				        	clearTable(vista.table_properties);
				        	DefaultTableModel tableModel=(DefaultTableModel)vista.table_info.getModel();
							DefaultTableModel tableModel2=(DefaultTableModel)vista.table_properties.getModel();
							try {							
								String tabla=model.buscarTablas(vista.textField.getText().toUpperCase());
								tableModel=model.rellenarTabla("SELECT * FROM "+tabla+";");
								tableModel2=model.rellenarTabla("SHOW COLUMNS FROM "+tabla+";");
								vista.lblTabla.setText(tabla);
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							vista.table_info.setModel(tableModel);
							vista.table_properties.setModel(tableModel2);
				        	vista.textField.setText("");

			        	}
			        	
			        }else {
			        	JOptionPane.showMessageDialog(null, "Consulta erronea");
			        }
			}
		};
		
		try {
			if(model.Conexion() != null) {
				vista.btnTancarConexio.setEnabled(true);
				vista.btnReconectar.setEnabled(false);
				do {
					
				}while(login() == false);
		   }else {
			   vista.btnTancarConexio.setEnabled(false);
				vista.btnReconectar.setEnabled(true);
				vista.btnMostrarInfo.setEnabled(false);
				vista.btnRealitzarConsulta.setEnabled(false);
		   }
			   vista.btnRealitzarConsulta.addActionListener(realizarConsulta);
			   vista.btnMostrarInfo.addActionListener(escucha);
			   vista.btnTancarConexio.addActionListener(cerrarS);
			   vista.btnReconectar.addActionListener(abrirS);
		} catch (Exception ex) {
			ex.printStackTrace();
	    }
	}
	
	/**
	 * Funció que obri una pantalla de login
 	 * @return boolean si el login es correcte o no
	 * @throws Exception
	 */
	public static boolean login() throws Exception {
		boolean correcte = false;
		JTextField usu= new JTextField();
		JPasswordField pass = new JPasswordField();
		
		Object[] fields = {
				"Usuari: ", usu,
				"Contrasenya: ", pass
		};   		
		int opcion = JOptionPane.showConfirmDialog(null,fields,"Iniciar sesió",JOptionPane.OK_CANCEL_OPTION);
			if (opcion == JOptionPane.OK_OPTION)
			{
			    String value1 = usu.getText();
			    String value2 = model.Encrypt(pass.getText());
			    if(!model.comprobarLogin(value1, value2)) {
			    	JOptionPane.showMessageDialog(null, "Contrasenya o usuari incorrectes");
			    }else {
			    	correcte = true;
			    }
			}else {
				System.exit(0);
			}
		return correcte;
	}
}
