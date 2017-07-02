package view;
import java.net.Socket;
import java.util.Scanner;

import org.omg.CORBA.portable.InputStream;

import controller.ProtocoloEnviaBaralho;

import java.net.ServerSocket;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;



public class Client{
	public Socket cliente;
	final   Integer nPorta     = 23456;
	private String host = "127.0.0.1";
	public Scanner scanner = new Scanner(System.in);
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	
	 

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

