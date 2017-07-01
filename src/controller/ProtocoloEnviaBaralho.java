package controller;
import java.io.Serializable;

public class ProtocoloEnviaBaralho  implements Serializable {
	private static final long serialVersionUID = 1L;
	private String cMensagem;
	private Baralho baralho;
	
	public ProtocoloEnviaBaralho(String mensagem, Baralho baralho) {
		this.cMensagem = mensagem;
		this.baralho = baralho;
	}
	
	public ProtocoloEnviaBaralho(String mensagem) {
		this.cMensagem = mensagem;
	}

	public String getcMensagem() {
		return cMensagem;
	}

	public void setcMensagem(String cMensagem) {
		this.cMensagem = cMensagem;
	}

	public Baralho getBaralho() {
		return baralho;
	}

	public void setBaralho(Baralho baralho) {
		this.baralho = baralho;
	}
	
}
