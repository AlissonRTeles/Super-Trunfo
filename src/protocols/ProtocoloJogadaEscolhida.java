package protocols;

import java.io.Serializable;

import models.Carta;

public class ProtocoloJogadaEscolhida implements Serializable{
	private static final long serialVersionUID = 1L;
	private Carta cartaEscolhida;
	private int atributoEscolhido;
	public  String  mensagem;
	
	public Carta getCartaEscolhida() {
		return cartaEscolhida;
	}
	public void setCartaEscolhida(Carta cartaEscolhida) {
		this.cartaEscolhida = cartaEscolhida;
	}
	public int getAtributoEscolhido() {
		return atributoEscolhido;
	}
	public void setAtributoEscolhido(int atributoEscolhido) {
		this.atributoEscolhido = atributoEscolhido;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
}
