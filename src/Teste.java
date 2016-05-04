import opet.mygarage.model.persistencia.UsuarioDAO;
import opet.mygarage.vo.Usuario;

public class Teste {

	public static void main(String[] args) {

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = new Usuario();
		usuario.setIdUsuario(6);
		usuarioDAO.consultaUsuarioDAO(usuario);
		
		

	}

}
