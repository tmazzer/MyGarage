/**
 * 
 */
package opet.mygarage.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import opet.mygarage.model.AmigosModel;
import opet.mygarage.model.CarroModel;
import opet.mygarage.model.TimelineModel;
import opet.mygarage.model.UsuarioModel;
import opet.mygarage.util.SessaoSistema;
import opet.mygarage.vo.Carro;
import opet.mygarage.vo.Relacionamento;
import opet.mygarage.vo.Timeline;
import opet.mygarage.vo.TimelineAcao;
import opet.mygarage.vo.Usuario;

@Named("timelineBean")
@SessionScoped
/**
 * Backing Bean para dar suporte às páginas Timeline/JSF
 * 
 * @author Juliano Zapelini Batista / Tiago Mazzer
 * 
 * @since 26/04/2016
 * 
 * @version 1.0
 * 
 */
public class TimelineBean implements Serializable {

	/*
	 * Variáveis de instância
	 */

	private String msgRetorno;
	private String msgWelcome;
	private Integer idUsuarioLogado;
	private String msgTimeline;
	private String descTimelineAcao;
	private String isFriend;

	private List<Timeline> timelineList;	
	private List<Carro> carrosList;
	private List<Usuario> amigosList;
	private List<TimelineAcao> timelineAcaoList;
	
	private Usuario usuario;
	private Timeline timeline;
	private TimelineAcao timelineAcao;	

	private Relacionamento relacionamento;
	private TimelineModel timelineModel;	
	private UsuarioModel usuarioModel;	
	private CarroModel carroModel;

	private static final long serialVersionUID = -911596375118728823L;

	/*
	 * Função construtora da classe
	 */
	/**
	 * Construtor TimelineBean()
	 */
	public TimelineBean() {

		timelineModel = new TimelineModel();

		relacionamento = new Relacionamento();
		
		usuarioModel = new UsuarioModel();
		
		carroModel = new CarroModel();
	}

	/*
	 * Métodos de acesso
	 */
	/**
	 * Monta a tela inicial timelineView.xhtml
	 */
	public void inicializaPagina() {
		
		timeline = new Timeline();
		timelineList = new ArrayList<>();
		timelineModel = new TimelineModel();

		timelineAcao = new TimelineAcao();
		timelineAcaoList = new ArrayList<>();


		// Recupera Timeline/Feed
		timelineList = timelineModel.listaTimelineModel();
		if (timelineList == null) {
			msgTimeline = "Timeline vazia";
		}
		
		//monta detalhes do usuario
		usuario = new Usuario();
		usuario.setIdUsuario(SessaoSistema.getIdUsuarioLogado());	
		usuario = usuarioModel.consultaUsuarioModel(usuario);
		msgWelcome = "Seja bem vindo  " + SessaoSistema.getNomeUsuarioLogado();
		idUsuarioLogado = SessaoSistema.getIdUsuarioLogado();
		
		//Lista ate 4 amigos
		amigosList = new ArrayList<>();
		AmigosModel amigosModel = new AmigosModel();
		amigosList = amigosModel.listaUltimosAmigosModel();	
		
		//Lista ate 4 carros	
		carrosList = new ArrayList<>();
		carrosList = carroModel.listaCarrosModel(SessaoSistema.getIdUsuarioLogado());	
	}

	/**
	 * Faz logout do sistema
	 */
	public String logOut() {
		msgRetorno = "";
		timelineList = null;
		SessaoSistema.setCodigodMensagem(0);
		SessaoSistema.setDescMensagem("");
		SessaoSistema.setIdUsuarioLogado(0);
		SessaoSistema.setNomeUsuarioLogado("");
		return "/paginas/loginView";
	}

	/**
	 * Consulta os detalhes do Usuario selecionado na tela Timeline
	 * 
	 */
	public String consultaUsuarioController(Integer idUsuario) {

		System.out.println("TimelineBean::consultaUsuarioController");

		SessaoSistema.setCodigodMensagem(0);
		
		usuario = new Usuario();

		usuario.setIdUsuario(idUsuario);

		// Consulta os dados do Usuario selecionado
		usuario = usuarioModel.consultaUsuarioModel(usuario);

		if (SessaoSistema.getCodigodMensagem() == 0) {

			// Consulta se é amigo

			AmigosModel amigosModel = new AmigosModel();

			relacionamento = amigosModel.validaAmigo(idUsuario);

			if (relacionamento != null) {
				isFriend = "amigo";
			} else {
				isFriend = "";
			}

			// Busca timeline do Usuario Selecionado
			timelineList = timelineModel.listaTimelineUsuarioModel(idUsuario);

			return "/paginas/timeline/usuarioTimelineView";

		} else {
			msgRetorno = "Usuário não encontrado. Favor verificar mais tarde.";
			SessaoSistema.setCodigodMensagem(0);
			SessaoSistema.setDescMensagem("");
			return msgRetorno;
		}

	}

	/**
	 * Publica o Post que o Usuario logado informou
	 * 
	 */
	public String publicaPostController(Timeline timeline) {

		System.out.println("TimelineBean::publicaPostController");

		if (timeline.getDescricao().equals("")) {
			return msgRetorno = "Preencher campo Post para cadastrar";
		}

		if (timelineModel.cadastraPostModel(timeline.getDescricao())) {
			inicializaPagina();

			return msgRetorno = "Post publicado com sucesso";
		} else {
			return msgRetorno = "Erro ao publicar o Post. Tente mais tarde";
		}

	}

	/**
	 * Consulta os detalhes do Post selecionado
	 * 
	 */
	public String consultaDetalhesPostController(Timeline timeline) {

		this.timeline = timeline;
		// Consulta os detalhes do Usuario selecionado
		timelineAcaoList = timelineModel.listaTimeLineAcao(timeline.getIdTimeline());

		return "/paginas/timeline/detalheTimelineView";
	}

	/**
	 * Curtir o Post selecionado na tela Timeline
	 * 
	 */
	public String curtirPostTimelineController(Timeline timeline) {

		System.out.println("TimelineBean::curtirPostController");
		timelineAcao = new TimelineAcao();
		timelineAcao.setIdTimeline(timeline.getIdTimeline());

		if (timeline.getLike() == null) {

			timelineAcao.setLike("S");

			if (timelineModel.cadastraTimelineAcaoModel(timelineAcao)) {

				msgRetorno = "Like com sucesso!";
				inicializaPagina();
			} else {
				msgRetorno = "Erro ao cadastrar Like!";
			}
		} else {
			timelineAcao.setLike(null);
			
			if (timelineModel.excluiLikeTimelineAcaoModel(timelineAcao)) {

				msgRetorno = "UnLike com sucesso!";
				inicializaPagina();
			} else {
				msgRetorno = "Erro ao excluir Like!";
			}
		}
		return msgRetorno;
	}

	/**
	 * Curtir o Post selecionado na tela detalhe do Timeline
	 * 
	 */
	public String curtirPostDetalheController(Timeline timeline) {
		System.out.println("TimelineBean::curtirPostController");

		timelineAcao = new TimelineAcao();
		
		
		timelineAcao.setIdTimeline(timeline.getIdTimeline());

		if (timeline.getLike() == null) {
			
			timeline.setLike("S");
			
			timelineAcao.setLike("S");

			if (timelineModel.cadastraTimelineAcaoModel(timelineAcao)) {

				msgRetorno = "Like com sucesso!";
//				Timeline timelinePost = new Timeline();
//				timelinePost.setIdTimeline(timeline.getIdTimeline());
				
				
//				consultaDetalhesPostController(timelinePost);
				consultaDetalhesPostController(timeline);
			} else {
				msgRetorno = "Erro ao cadastrar Like!";
			}
		}else{
			timeline.setLike(null);
			
			if (timelineModel.excluiLikeTimelineAcaoModel(timelineAcao)) {
			
				msgRetorno = "Like excluído com sucesso!";

//				Timeline timelinePost = new Timeline();
//				timelinePost.setIdTimeline(timeline.getIdTimeline());
//				consultaDetalhesPostController(timelinePost);
				consultaDetalhesPostController(timeline);
			} else {
				msgRetorno = "Erro ao excluir Like!";
			}
		}
		return msgRetorno;
	}

	/**
	 * Publica o Comentario no Post selecionado na Timeline
	 * 
	 */
	public String publicaComentarioPostController(Timeline timeline, String comentarioPost) {
		System.out.println("TimelineBean::publicaComentarioPostController");
		
		if (comentarioPost.equals("")) {
			return msgRetorno = "O campo comentário deve estar preenchido para publicar";
		}
		
		timelineAcao = new TimelineAcao();
		timelineAcao.setIdTimeline(timeline.getIdTimeline());
		timelineAcao.setComentario(comentarioPost);

		if (timelineModel.cadastraTimelineAcaoModel(timelineAcao)) {

			msgRetorno = "Comentario cadastrado com sucesso!";
			
			descTimelineAcao = "";
			consultaDetalhesPostController(timeline);
		} else {
			msgRetorno = "Erro ao cadastrar comentario!";
		}

		return msgRetorno;
	}

	/**
	 * Exclui o Post que o Usuario logado informou
	 * 
	 */
	public String excluiPostController(Integer idTimeline) {

		System.out.println("TimelineBean::excluiPostController");

		if (timelineModel.excluiPostTimelineModel(idTimeline)) {
			inicializaPagina();

			return msgRetorno = "Post excluído com sucesso";
		} else {
			return msgRetorno = "Erro ao excluir o Post. Tente mais tarde";
		}

	}
	

	/**
	 * Exclui o Comentario que o Usuario logado informou em um Post
	 * 
	 */
	public void excluiComentarioController(Integer idTimelineAcao) {

		System.out.println("TimelineBean::excluiComentarioController");

		if (timelineModel.excluiTimelineAcaoModel(idTimelineAcao)) {
			msgRetorno = "Comentário excluído com sucesso";
			consultaDetalhesPostController(timeline);
		} else {
			msgRetorno = "Erro ao excluir o Comentário. Tente mais tarde";
		}
	}
	
	
	/**
	 * @return the msgRetorno
	 */
	public String getMsgRetorno() {
		return msgRetorno;
	}

	/**
	 * @param msgRetorno
	 *            the msgRetorno to set
	 */
	public void setMsgRetorno(String msgRetorno) {
		this.msgRetorno = msgRetorno;
	}

	/**
	 * @return the timelineList
	 */
	public List<Timeline> getTimelineList() {
		return timelineList;
	}

	/**
	 * @param timelineList
	 *            the timelineList to set
	 */
	public void setTimelineList(List<Timeline> timelineList) {
		this.timelineList = timelineList;
	}

	/**
	 * @return the msgTimeline
	 */
	public String getMsgTimeline() {
		return msgTimeline;
	}

	/**
	 * @param msgTimeline
	 *            the msgTimeline to set
	 */
	public void setMsgTimeline(String msgTimeline) {
		this.msgTimeline = msgTimeline;
	}

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the isFriend
	 */
	public String getIsFriend() {
		return isFriend;
	}

	/**
	 * @param isFriend
	 *            the isFriend to set
	 */
	public void setIsFriend(String isFriend) {
		this.isFriend = isFriend;
	}

	/**
	 * @return the relacionamento
	 */
	public Relacionamento getRelacionamento() {
		return relacionamento;
	}

	/**
	 * @param relacionamento
	 *            the relacionamento to set
	 */
	public void setRelacionamento(Relacionamento relacionamento) {
		this.relacionamento = relacionamento;
	}

	/**
	 * @return the timeline
	 */
	public Timeline getTimeline() {
		return timeline;
	}

	/**
	 * @param timeline
	 *            the timeline to set
	 */
	public void setTimeline(Timeline timeline) {
		this.timeline = timeline;
	}

	/**
	 * @return the timelineAcao
	 */
	public TimelineAcao getTimelineAcao() {
		return timelineAcao;
	}

	/**
	 * @param timelineAcao
	 *            the timelineAcao to set
	 */
	public void setTimelineAcao(TimelineAcao timelineAcao) {
		this.timelineAcao = timelineAcao;
	}

	/**
	 * @return the timelineAcaoList
	 */
	public List<TimelineAcao> getTimelineAcaoList() {
		return timelineAcaoList;
	}

	/**
	 * @param timelineAcaoList
	 *            the timelineAcaoList to set
	 */
	public void setTimelineAcaoList(List<TimelineAcao> timelineAcaoList) {
		this.timelineAcaoList = timelineAcaoList;
	}

	/**
	 * @return the msgWelcome
	 */
	public String getMsgWelcome() {
		return msgWelcome;
	}

	/**
	 * @param msgWelcome
	 *            the msgWelcome to set
	 */
	public void setMsgWelcome(String msgWelcome) {
		this.msgWelcome = msgWelcome;
	}

	/**
	 * @return the descTimelineAcao
	 */
	public String getDescTimelineAcao() {
		return descTimelineAcao;
	}

	/**
	 * @param descTimelineAcao
	 *            the descTimelineAcao to set
	 */
	public void setDescTimelineAcao(String descTimelineAcao) {
		this.descTimelineAcao = descTimelineAcao;
	}

	/**
	 * @return the amigosList
	 */
	public List<Usuario> getAmigosList() {
		return amigosList;
	}

	/**
	 * @param amigosList the amigosList to set
	 */
	public void setAmigosList(List<Usuario> amigosList) {
		this.amigosList = amigosList;
	}

	/**
	 * @return the carrosList
	 */
	public List<Carro> getCarrosList() {
		return carrosList;
	}

	/**
	 * @param carrosList the carrosList to set
	 */
	public void setCarrosList(List<Carro> carrosList) {
		this.carrosList = carrosList;
	}

	/**
	 * @return the idUsuarioLogado
	 */
	public Integer getIdUsuarioLogado() {
		return idUsuarioLogado;
	}

	/**
	 * @param idUsuarioLogado the idUsuarioLogado to set
	 */
	public void setIdUsuarioLogado(Integer idUsuarioLogado) {
		this.idUsuarioLogado = idUsuarioLogado;
	}

}
