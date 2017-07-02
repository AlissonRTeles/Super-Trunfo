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
	
	 

	/**
	 * @throws ClassNotFoundException **********************************************************************************/
	public static void main(String [] args) throws IOException, ClassNotFoundException{
		
		Scanner scanner = new Scanner(System.in);
		String nomeJogador;
		
		
		try{			
			Socket cliente = new Socket("127.0.0.1",23456);
			
			ObjectInputStream ois =  new ObjectInputStream(cliente.getInputStream());
			//Recebe
			ProtocoloEnviaBaralho protocoloteste = (ProtocoloEnviaBaralho)ois.readObject();			
			System.out.println("Mensagem: "+protocoloteste.getcMensagem());
						
			protocoloteste.setcMensagem("Estou aguardando o inicio do jogo");
			
			ObjectOutputStream  oos = new ObjectOutputStream(cliente.getOutputStream());
			//Envia
			oos.writeObject(protocoloteste);
			oos.flush();
			oos.reset();			
				
			//Aguardar recebimento do baralho.
			
			//Aguarda pra saber se é o jogador do turno 
			 
			cliente.close();

		}catch(IOException e){
			e.printStackTrace();
		}
		
		
	}
	/************************************************************************************/

}

