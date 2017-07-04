package protocols;
import java.io.Serializable;

import controller.Baralho;

public class ProtocoloEnviaBaralho  implements Serializable {
	private static final long serialVersionUID = 1L;
	private String cMensagem;
	private Baralho baralho;
	private Integer nrJogador;
	private Integer qtdCartasBaralho;
	
	public ProtocoloEnviaBaralho(String mensagem, Baralho baralho, Integer nrJogador, Integer qtdCartasBaralho) {
		this.cMensagem = mensagem;
		this.baralho = baralho;
		this.nrJogador = nrJogador;
		this.qtdCartasBaralho = qtdCartasBaralho;
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

	public Integer getNrJogador() {
		return nrJogador;
	}

	public void setNrJogador(Integer nrJogador) {
		this.nrJogador = nrJogador;
	}

	public Integer getQtdCartasBaralho() {
		return qtdCartasBaralho;
	}

	public void setQtdCartasBaralho(Integer qtdCartasBaralho) {
		this.qtdCartasBaralho = qtdCartasBaralho;
	}
	
	
	
}
