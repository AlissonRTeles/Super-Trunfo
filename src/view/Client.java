package view;
import java.awt.List;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import controller.Baralho;
import models.Carta;
import protocols.ProtocoloEnviaBaralho;
import protocols.ProtocoloFimJogada;
import protocols.ProtocoloInicioJogada;
import protocols.ProtocoloJogadaEscolhida;



public class Client{
	public Socket cliente;
	final   Integer nPorta     = 23456;
	private String host = "127.0.0.1";
	public Scanner scanner = new Scanner(System.in);
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private Baralho baralho;
	ProtocoloJogadaEscolhida protocolojogadaescolhida;
	ProtocoloFimJogada protocolofimjogada;
	
	 

	/**
	 * @throws ClassNotFoundException **********************************************************************************/
	public static void main(String [] args) throws IOException, ClassNotFoundException{
		Client c = new Client();
		c.rodaJogo();
	}
	
	public void sendsomething (ObjectOutputStream oos, Object protocol) throws IOException{
		oos.writeObject(protocol);
		oos.flush();
		oos.reset();
	}
	
	
	public Object returnsomething (ObjectInputStream ois) throws IOException, ClassNotFoundException{
		Object protocol = ois.readObject();			
		return(protocol);
	}
	
	public void rodaJogo() throws IOException, ClassNotFoundException {	
		createSocket();
		conexaoServer();	
		inicioJogada();
		fimJogada();	
		
	}
	
	public void fimJogada() throws ClassNotFoundException, IOException{
		ProtocoloFimJogada protocol = (ProtocoloFimJogada)returnsomething(ois);
		
		System.out.println(protocol.getMensagem());
		
		java.util.List<Carta> auxCartas = protocol.getCartasRodada().getCartas();
		
		for (Carta carta : auxCartas) {
			System.out.println(carta.toString());
			
			if(protocol.getIsGanhador()){
				this.baralho.getCartas().add(this.baralho.getCartas().size()-1,carta);
			}
		}
		
		
		//if(protocol.getIsGanhador()){
			
		//}
		
	}
	
	public void inicioJogada() throws ClassNotFoundException, IOException{
		ProtocoloInicioJogada protocol = (ProtocoloInicioJogada)returnsomething(ois);
		if(protocol.getlMensagem()){
			System.out.println("Rodada:"+protocol.getnJogada()+ ", Voce é o jogador da rodada.");
			
			System.out.println("\nCarta\n");
			System.out.println("Nome: "+baralho.getCartas().get(0).getcNome());
			System.out.println("Codigo: "+baralho.getCartas().get(0).getcCod());
			System.out.println("Tipo: "+baralho.getCartas().get(0).getcTipo());
			System.out.println("1. Peso:"+baralho.getCartas().get(0).getnPeso());
			System.out.println("2. Velocidade: "+baralho.getCartas().get(0).getnVelocidade());
			System.out.println("3. Altitude: "+baralho.getCartas().get(0).getnAltitude());
			System.out.println("4. Comprimento: "+baralho.getCartas().get(0).getnComprimento());
			System.out.println("5. Altura: "+baralho.getCartas().get(0).getnAltura());
			
			if(baralho.getCartas().get(0).getIsSTrunfo()){
				System.out.println("6. Voce e o Supertrunfo!!");
			}else{
				System.out.println("Você nao e o Supertrunfo :(");
			}
			System.out.println("\nEscolha o Atribudo da sua carta...");
			
			Integer atributoEscolhido = scanner.nextInt();
			protocolojogadaescolhida = new ProtocoloJogadaEscolhida(baralho.getCartas().get(0), atributoEscolhido);
			
			sendsomething(oos,protocolojogadaescolhida);
			baralho.getCartas().remove(0);
						
		}else{
			System.out.println("Rodada:"+protocol.getnJogada()+ ", Aguarde o jogador da rodada.");
			
			System.out.println("\nCarta\n");
			System.out.println("Nome: "+baralho.getCartas().get(0).getcNome());
			System.out.println("Codigo: "+baralho.getCartas().get(0).getcCod());
			System.out.println("Tipo: "+baralho.getCartas().get(0).getcTipo());
			System.out.println("Peso:"+baralho.getCartas().get(0).getnPeso());
			System.out.println("Velocidade: "+baralho.getCartas().get(0).getnVelocidade());
			System.out.println("Altitude: "+baralho.getCartas().get(0).getnAltitude());
			System.out.println("Comprimento: "+baralho.getCartas().get(0).getnComprimento());
			System.out.println("Altura: "+baralho.getCartas().get(0).getnAltura());
			if(baralho.getCartas().get(0).getIsSTrunfo()){
				System.out.println("Voce e o Supertrunfo!!");
			}else{
				System.out.println("Você nao e o Supertrunfo :(");
			}
			protocolojogadaescolhida = new ProtocoloJogadaEscolhida(baralho.getCartas().get(0),0);
			System.out.println("\n1 para enviar a carta para o servidor");
			scanner.nextInt();
			sendsomething(oos,protocolojogadaescolhida);
			baralho.getCartas().remove(0);
		}
		
	}
	
	public void createSocket() throws IOException{
		this.cliente = new Socket(this.host,this.nPorta);
	}
	
	
	public void conexaoServer() throws ClassNotFoundException{
		try{
			ois =  new ObjectInputStream(this.cliente.getInputStream());
			//Recebe
			ProtocoloEnviaBaralho protocol = (ProtocoloEnviaBaralho)returnsomething(ois);
			baralho = protocol.getBaralho();
			System.out.println("Primeira Carta"+baralho.getCartas().get(0).getcNome());
			System.out.println("Baralho Recebido. "+protocol.getcMensagem());
			
			protocol.setcMensagem("Aguardando inicio do jogo.");			
			oos = new ObjectOutputStream(this.cliente.getOutputStream());
			
			sendsomething(oos,protocol);
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	/************************************************************************************/

}

