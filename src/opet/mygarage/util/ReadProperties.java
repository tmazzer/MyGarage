package opet.mygarage.util;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {
	
	/*
	 * Variáveis de instância
	 */

	/*
	 * Função construtora da classe
	 */
	public ReadProperties(){
	}
	
	/*
	 * Métodos de acesso
	 */
	public static Properties getProp() throws IOException {
		Properties props=new Properties();
		props.load(Thread.currentThread().getContextClassLoader().getResource("dados.properties").openStream());
		return props;
	}	

}
