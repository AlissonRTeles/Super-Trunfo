package protocols;


import java.io.Serializable;

import controller.Baralho;

public class ProtocoloEncerraTurno implements Serializable{
	private static final long serialVersionUID = 1L;
	public Boolean perdi;
	public Boolean ganhei;
	public String  mensagem;
	
		
	public ProtocoloEncerraTurno(Boolean perdi,Boolean ganhei) {
		super();
		this.perdi = perdi;
		this.ganhei =  ganhei;
	}
	
	public Boolean getPerdi() {
		return perdi;
	}
	public void setPerdi(Boolean perdi) {
		this.perdi = perdi;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Boolean getGanhei() {
		return ganhei;
	}

	public void setGanhei(Boolean ganhei) {
		this.ganhei = ganhei;
	}
	
	
	

}
