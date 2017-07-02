package protocols;

import java.io.Serializable;

public class ProtocoloInicioJogada implements Serializable {
	private static final long serialVersionUID = 1L;
	private int nJogada;
	private Boolean lMensagem;
	public  String  mensagem;
	
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public int getnJogada() {
		return nJogada;
	}
	public void setnJogada(int nJogada) {
		this.nJogada = nJogada;
	}
	public Boolean getlMensagem() {
		return lMensagem;
	}
	public void setlMensagem(Boolean lMensagem) {
		this.lMensagem = lMensagem;
	}
	
	
}
