package protocols;


import java.io.Serializable;

import controller.Baralho;

public class ProtocoloEncerraTurno implements Serializable{
	private static final long serialVersionUID = 1L;
	public Boolean perdi;
	public String  mensagem;
	
		
	public ProtocoloEncerraTurno(Boolean perdi) {
		super();
		this.perdi = perdi;
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
	
	

}
