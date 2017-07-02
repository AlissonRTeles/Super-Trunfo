package protocols;

import java.io.Serializable;

import controller.Baralho;

public class ProtocoloFimJogada implements Serializable{
	private static final long serialVersionUID = 1L;
	public Boolean isGanhador;
	public String  mensagem;
	public Baralho cartasRodada;
	
	public Boolean getIsGanhador() {
		return isGanhador;
	}
	public void setIsGanhador(Boolean isGanhador) {
		this.isGanhador = isGanhador;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public Baralho getCartasRodada() {
		return cartasRodada;
	}
	public void setCartasRodada(Baralho cartasRodada) {
		this.cartasRodada = cartasRodada;
	}
	
	
}
