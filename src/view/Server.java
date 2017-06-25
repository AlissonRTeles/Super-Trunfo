package view;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import controller.LeitorArquivos;


public class Server {
	final   Integer nPorta     = 23456;
	private Integer nJogadores = 0;
	private ServerSocket servidor;
	public  Scanner in = new Scanner(System.in);
	
	
	public void Init(){
		this.nJogadores = 2;
	}
	
	public void createSocket() throws IOException{
		this.servidor = new ServerSocket(this.nPorta);
	}
	
	public void aguardaJogadoresSk() throws IOException{
		//aguarda conexoes
		//fazer parametro para pedir a quantidade de conex�es
		int nCount = 1;
		
		while (nCount <= this.nJogadores){			
			try {
				
				System.out.println("Aguardando Cliente:"+nCount);
				Socket cliente = servidor.accept();				
				System.out.println("Conectado...");
				Multithread thread = new Multithread(cliente);
				nCount ++;
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		
	}
	
	public void rodaJogo() throws IOException {	
		Init();		
		System.out.println("Informe o numero de jogadores:");
		
		this.nJogadores = in.nextInt();

		createSocket();
		
		aguardaJogadoresSk();
		
	}
	
	public static void main(String[] args) throws IOException{
		Server s = new Server();
		s.rodaJogo();
	}
}
