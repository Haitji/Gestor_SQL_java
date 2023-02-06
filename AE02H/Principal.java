package ejT2.AE02H;

public class Principal {

	public static void main(String[] args) {
		Model model = new Model();
		Vista vista = new Vista();

		Controlador controlador = new Controlador(vista, model);

	}

}
