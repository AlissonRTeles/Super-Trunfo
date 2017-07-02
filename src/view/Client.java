package view;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import controller.Baralho;
import protocols.ProtocoloEnviaBaralho;



public class Client{
	public Socket cliente;
	final   Integer nPorta     = 23456;
	private String host = "127.0.0.1";
	public Scanner scanner = new Scanner(System.in);
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private Baralho baralho;
	
	 

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
		
		
	}
	
	public void createSocket() throws IOException{
		this.cliente = new Socket(this.host,this.nPorta);
	}
	
	
	public void conexaoServer() throws ClassNotFoundException{
		try{
			ois =  new ObjectInputStream(this.cliente.getInputStream());
			//Recebe
			ProtocoloEnviaBaralho protocol = (ProtocoloEnviaBaralho)returnsomething(ois);
			System.out.println(protocol.getcMensagem());
			baralho = protocol.getBaralho();
			System.out.println("Primeira Carta:"+baralho.getCartas().get(0).getcNome());
			
			protocol.setcMensagem("Aguardando inicio do jogo.");
			
			oos = new ObjectOutputStream(this.cliente.getOutputStream());
			//Envia
			sendsomething(oos,protocol);
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	/************************************************************************************/

}

