package view;
import java.net.Socket;
import java.util.Scanner;
import java.net.ServerSocket;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;



public class Client{
	
	 

	/************************************************************************************/
	public static void main(String [] args) throws IOException{
		
		Scanner scanner = new Scanner(System.in);
		String nomeJogador;
		
		try{			
			Socket cliente = new Socket("127.0.0.1",23456);
			PrintWriter out;
			
			//Recebendo mensagem do servidor
			BufferedReader in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			String str = in.readLine();
			System.out.println(str);
			
			
			System.out.println("Informe seu Nome:");			
			nomeJogador = scanner.next();
			//Enviando mensagem para o Servidor
			 out = new PrintWriter(new BufferedWriter( new OutputStreamWriter(cliente.getOutputStream())),true);
			 out.println(nomeJogador);
				
			cliente.close();

		}catch(IOException e){
			e.printStackTrace();
		}
		
		
	}
	/************************************************************************************/

}

