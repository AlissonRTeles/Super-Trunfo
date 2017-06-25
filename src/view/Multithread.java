package view;
import java.net.Socket;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Multithread extends Thread{


	/************************************************************************************/
    private PrintWriter out;
	private Socket cliente;

	/************************************************************************************/
	public Multithread (Socket cliente) throws IOException{
		this.cliente = cliente;
        out = new PrintWriter(new BufferedWriter( new OutputStreamWriter(this.cliente.getOutputStream())),true);
		start();
	}

	/************************************************************************************/
	public void run(){
	
		try{
			//enviando mensagem para cliente
			out.println("Você foi conectado.");
			
			//Recebendo mensagem do cliente
			BufferedReader in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			String str = in.readLine();
			System.out.println("Jogador conectado: "+str);
			
			cliente.close();

		}catch(IOException e) { 
			e.printStackTrace();
		}	
	}
	/************************************************************************************/
}
