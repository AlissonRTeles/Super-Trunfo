package view;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.LeitorArquivos;
import controller.ProtocoloEnviaBaralho;
import models.Carta;


public class Server {
	final   Integer nPorta     = 23456;
	private Integer nJogadores = 0;
	private ServerSocket servidor;
	public  Scanner in = new Scanner(System.in);
	private List<Socket> SockList = new ArrayList<Socket>();
	private List<ObjectOutputStream> SendObjList = new ArrayList<ObjectOutputStream>();
	private List<ObjectInputStream> RecvObjList = new ArrayList<ObjectInputStream>();
	
	ProtocoloEnviaBaralho protocolo1;
	

	private Socket cliente;
	
	
	public void Init(){
		this.nJogadores = 2;
	}
	
	public void createSocket() throws IOException{
		this.servidor = new ServerSocket(this.nPorta);
	}
	
	public void aguardaJogadoresSk() throws IOException, ClassNotFoundException{
		//aguarda conexoes
		//fazer parametro para pedir a quantidade de conexï¿½es
		int nCount = 1;
		
		while (nCount <= this.nJogadores){			
			try {
				Scanner scanner1 = new Scanner(System.in);
				System.out.println("Aguardando Cliente:"+nCount);
				
				Socket cliente = servidor.accept();
				
				protocolo1 = new ProtocoloEnviaBaralho("Voce é o jogador "+nCount);

				ObjectOutputStream  oos = new ObjectOutputStream(cliente.getOutputStream());
				sendsomething(oos,protocolo1);
				
				ObjectInputStream ois =  new ObjectInputStream(cliente.getInputStream());
				returnsomething(ois);

				

				
				System.out.println("Cliente conectado do IP "+cliente.getInetAddress().getHostAddress());
							
				protocolo1 = new ProtocoloEnviaBaralho("BLABLABLA");

				System.out.println("Conectado "+nCount+" de "+nJogadores);
				
				SockList.add(cliente);
				SendObjList.add(oos);
				RecvObjList.add(ois);

				
				nCount ++;
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		
		/*
		
		for (int i = 0; i < nJogadores; i++) {

			sendsomething(SendObjList.get(i),protocolo1);
			returnsomething(RecvObjList.get(i));
			sendsomething(SendObjList.get(i),protocolo1);
	
		}
		
		sendsomething(SendObjList.get(0),protocolo1);
		sendsomething(SendObjList.get(1),protocolo1);

		
		returnsomething(RecvObjList.get(0));
		returnsomething(RecvObjList.get(1));

		*/
	}
	
	public void rodaJogo() throws IOException, ClassNotFoundException {	
		Init();		
		System.out.println("Informe o numero de jogadores:");		
		this.nJogadores = in.nextInt();

		createSocket();
		
		aguardaJogadoresSk();
		
		
		
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException{
		Server s = new Server();
		s.rodaJogo();
	}

	
	public void sendsomething (ObjectOutputStream oos, Object protocol) throws IOException{
		oos.writeObject(protocol);
		oos.flush();
		oos.reset();
	}
	
	
	public void returnsomething (ObjectInputStream ois) throws IOException, ClassNotFoundException{
		ProtocoloEnviaBaralho protocoloteste = (ProtocoloEnviaBaralho)ois.readObject();			
		System.out.println("Mensagem: "+protocoloteste.getcMensagem());
	}
	
}
