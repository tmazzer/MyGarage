package opet.mygarage.util;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {
	
	/*
	 * Vari�veis de inst�ncia
	 */

	/*
	 * Fun��o construtora da classe
	 */
	public ReadProperties(){
	}
	
	/*
	 * M�todos de acesso
	 */
	public static Properties getProp() throws IOException {
		Properties props=new Properties();
		props.load(Thread.currentThread().getContextClassLoader().getResource("dados.properties").openStream());
		return props;
	}	

}
