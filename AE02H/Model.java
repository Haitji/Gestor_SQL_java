package ejT2.AE02H;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.System.Logger;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Model {
	
	public static Connection conexion = null;
	public static String base = null, pass = null, user = null;
	
	/**
	 * Crea la conexió entre la base de dades i el programa
	 * @return retorna un objecte de tipus Connection
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static Connection Conexion() throws ParserConfigurationException, SAXException, IOException {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document document = (Document) dBuilder.parse(new File("./conexion.xml"));
			NodeList nodeList = document.getElementsByTagName("conexion");
			for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;
					base = eElement.getElementsByTagName("base").item(0).getTextContent();
					user = eElement.getElementsByTagName("user").item(0).getTextContent();
					pass = eElement.getElementsByTagName("pass").item(0).getTextContent();
				}
			}
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection(base,user,pass);
			JOptionPane.showMessageDialog(null, "Conectad amb exit");
		} catch (ClassNotFoundException | SQLException ex ) {
            JOptionPane.showMessageDialog(null, "Error al conectarse a la base de dades");
        }
		return conexion;
	}
	
	/**
	 * Comproba el contingut d'una consulta per a separarle per tipus
	 * @param consulta a base de dades
	 * @return int per cada tipus de contingut
	 */
	public int comprobarConsulta(String consulta) {
		   if(consulta.equals("")) {
			   return 0;
		   }
		   if(consulta.contains("SELECT")) {
			   return 1;
		   }else if(consulta.contains("INSERT")||consulta.contains("UPDATE")||consulta.contains("DELETE")) {
			   return 2;
		   }else {
			   return 3;
		   }
	   }
	
	/**
	 * Realitza les consultes a base de dades que inclouen un INSERT, UPDATE o DELETE
	 * @param consulta a base de dades
	 */
	public static void realizarCIUD(String consulta) {
		try {
			PreparedStatement psInsertar = conexion.prepareStatement(consulta);
			int resultadoInsertar = psInsertar.executeUpdate();
			if(resultadoInsertar == 0) {
				JOptionPane.showMessageDialog(null, "Error al executar la consulta");
			}
			psInsertar.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al executar la consulta");
		}
	}
	
	/**
	 * Comproba que les credencials del login siguen correctes
	 * @param userN nom del usuari
	 * @param passN contrasenya del usuari
	 * @return boolean true en cas de ser correcte o false en cas contrari
	 */
	public boolean comprobarLogin(String userN, String passN) {
		boolean correcto = false;
		try {
			String user = "";
			String pass = "";

			Statement stmt = conexion.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM users");
			while(rs.next()) {
				user = rs.getString(2);
				pass = rs.getString(3);
				if(userN.equals(user) && passN.equals(pass)) {
					correcto = true;
				}
			}
			rs.close();
			stmt.close();
		}catch(Exception e) {
		}
		return correcto;
	}
	
	/**
	 * Encripta la constrasenya pasada per parametre
	 * @param pass Contrasenya del usuari
	 * @return contrasenya encriptada en hash MD5
	 * @throws Exception
	 */
    public static String Encrypt(String pass) throws Exception {
    	String plaintext = pass;
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.reset();
		m.update(plaintext.getBytes());
		byte[] digest = m.digest();
		BigInteger bigInt = new BigInteger(1,digest);
		String hashtext = bigInt.toString(16);
		while(hashtext.length() < 32 ){
		  hashtext = "0"+hashtext;
	
		}                          
        return hashtext;
  }
   
   /**
    * Calcula el número de columnes de la taula elegida
    * @param tabla nom de la taula de la base de dades
    * @return numero de columnes de la taula
    */
   public static int numColumnas(String tabla) {
	   int cols = 0;
	   try {
			Statement stmt = conexion.createStatement();
			ResultSet nt = stmt.executeQuery("SELECT COUNT(*) FROM information_schema.columns WHERE TABLE_NAME='" + tabla + "'");
			while(nt.next()) {
				cols = nt.getInt(1);
			}
	   }catch(Exception e) {
	   }
	   return cols;
   }
   
   /**
    * Guarda el nom de les taules de la base de dades seleccionada
    * @param base de dades seleccionada
    * @return nom de les taules concatenades
    */
   public static String nomTablas(String base) {
	   String bases="";
	   int index = base.lastIndexOf('/');
		if (index > 0) {
			String extension = base.substring(index + 1);
			bases=extension;
		}

	   String nom = "";
	   try {
		   Statement stmt = conexion.createStatement();
		   ResultSet nt = stmt.executeQuery("SELECT TABLE_NAME from Information_Schema.Tables where table_schema = '" + bases + "'");
		   while(nt.next()) {
			   nom += nt.getString(1) + " ";
		   }
			nt.close();
			stmt.close();
	   }catch(Exception e) {
	   }
	   return nom;
   }
    
   /**
    * Guarda tot el contingut d'una taula
    * @param tabla nom de la taula
    * @param columnas número de columnes
    * @return informació de la taula concatenada
    */
   public static String mostrarContingut(String tabla, int columnas) {
	   String infoTabla = "";
	   try {
			Statement stmt = conexion.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + tabla);

			while(rs.next()) {
				for(int i = 1; i <= columnas; i++) {
					infoTabla += rs.getString(i) + " ";
					if(i == columnas) {
						infoTabla += "\n";
					}	
				}
			}
			rs.close();
			stmt.close();
	   }catch(Exception e) {
	   }
	   return infoTabla;
   }
   
   /** 
    * Recogis el string de la consulta y retorna el nom de la taul
    * @param consulta consulta a base de dades
    * @return retorna el nom de la taula dins del string
    * */
   public String buscarTablas(String consulta) {
	   String cons=consulta;
	   //Aqui comprobamos que tipo de operacion sql esta haciendo
	   String palabra_clave="";
	   if(cons.contains("SELECT")) {
		   palabra_clave ="FROM";
	   }else if(cons.contains("INSERT")) {
		   palabra_clave="INTO";
	   }else if(cons.contains("DELETE")) {
		   palabra_clave="FROM";
	   }else if(cons.contains("UPDATE")) {
		   palabra_clave="UPDATE";
	   }
	   String tabla;
	   //Convertimos la consulta en un array y dependiendo del tipo de operacion devuelve un indice u otra;
	   String[] palabras=cons.split(" ");
	   int indice=0;
	   for(int i=0;i<palabras.length;i++) {
		   if(palabras[i].equals(palabra_clave)) {
			   indice=i;
		   }
	   }
	   //Buscamos el nombre de la tabla con el indice obtenido;
	   tabla=palabras[indice+1];
	   if(tabla.contains(";")) {
		   int indice2=tabla.indexOf(";");
		   tabla=tabla.substring(0,indice2);
	   }
	   if(tabla.contains(".")) {
		   int indice3=tabla.indexOf(".");
		   tabla=tabla.substring(0,indice3);
	   }
	   return tabla;
   }
   
   
   /**
    * Replena les taules amb les consultes que se li introduixquen
    * @param consulta consulta a base de dades
    * @return retorna un tablemodel
    * */
   public DefaultTableModel rellenarTabla(String consulta) throws SQLException {
	   DefaultTableModel model = new DefaultTableModel();
	   Statement stmt = conexion.createStatement();
		ResultSet rs = stmt.executeQuery(consulta);
		ResultSetMetaData metaData = rs.getMetaData();
		int columns = metaData.getColumnCount();
		String[] etiquetas = new String[columns];
		for (int i = 0; i < columns; i++)
		{
		   etiquetas[i] = metaData.getColumnLabel(i + 1);
		}
		model.setColumnIdentifiers(etiquetas);
		String[] data= new String[columns];
		while (rs.next()) {
			for (int j = 0; j < columns; j++) {
				data[j] = rs.getString(j + 1);
			}
			model.addRow(data);
		}
		rs.close();
		stmt.close();
		return model;
   }
   
   
   /**
    * Extraix el camp de la contrasenya en una consulta de INSERT INTO USERS
    * @param consulta a base de dades
    * @return retorna la contrasenya
    */
   public String extraerContrasena(String consulta) {
	   String cons=consulta;
		String[] array=cons.split(",");
		if(array.length>1) {
			cons=array[2];
			if(cons.contains("'")) {
				String[]array2=cons.split("'");
				cons=array2[1];
			}
			if(cons.contains("\"")) {
				String[]array2=cons.split("\"");
				cons=array2[1];
			}
			try {
				cons=Encrypt(cons);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return cons;
   }
   
   /**
    * Sustituix la contrasenya d'una consulta per la mateixa o altra pero encryptada
    * @param consulta consulta a base de dades
    * @param password contrasenya a cambiar
    * @return retorna la consulta completa amb la nova contrasenya
    */
   public String sustituirEncrypt(String consulta,String password) {
	   String pass=password;
		String cons=consulta;
		String[] array=cons.split(",");
		if(array.length>1) {
			if(cons.contains("'")) {
				cons=array[0]+","+array[1]+","+"'"+pass+"');";
			}
			if(cons.contains("\"")) {
				cons=array[0]+","+array[1]+","+"\""+pass+"\");";
			}
		}
		return cons;
   }
   
   /**
    * Tanca la sesió amb la base de dades
    */
   public static void cierraConexion() {
	   if (conexion != null) {
		    try {
		        conexion.close();
		        JOptionPane.showMessageDialog(null, "Conexió tancada");
		    } catch (SQLException e) {
		    	e.printStackTrace();
		    }
		}
	}
	
	
}
