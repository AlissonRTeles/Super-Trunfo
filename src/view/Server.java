package view;
import controller.LeitorArquivos;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.IOException;


public class Server {
	public void Init(){
		LeitorArquivos l = new LeitorArquivos("Baralho.txt");
		l.leArquivo();
	}
	
	public static void main(String[] args) throws IOException{
		Server s = new Server();
		System.out.println("Teste");
		s.Init();
		
		ServerSocket servidor = new ServerSocket(23456);
		//aguarda conexões
		//fazer parametro para pedir a quantidade de conexões
		while (true){			
			try {
				
				System.out.println("Aguardando Cliente...");
				Socket cliente = servidor.accept();	
				System.out.println("Conectado...");
				Multithread thread = new Multithread(cliente);
				
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
}
