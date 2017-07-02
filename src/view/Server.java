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
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import controller.Baralho;
import controller.LeitorArquivos;
import models.Carta;
import protocols.ProtocoloEnviaBaralho;
import protocols.ProtocoloFimJogada;
import protocols.ProtocoloInicioJogada;
import protocols.ProtocoloJogadaEscolhida;


public class Server {
	final   Integer nPorta     = 23456;
	private Integer nJogadores = 0;
	private ServerSocket servidor;
	public  Scanner in = new Scanner(System.in);
	
	private List<Socket> SockList = new ArrayList<Socket>();
	private List<ObjectOutputStream> SendObjList = new ArrayList<ObjectOutputStream>();
	private List<ObjectInputStream> RecvObjList = new ArrayList<ObjectInputStream>();
	private List<Baralho> BaralhoList           = new ArrayList<Baralho>();
	
	
	private Boolean[] players;
	//Boolean[] array = new Boolean[size];
	//Arrays.fill(array, Boolean.FALSE);
	
	private Integer nJogada = 0;
	private Integer jogadorDaRodada = 0;
	private Integer nRodada = 1;
	
	
	Random random = new Random();
	
	ProtocoloEnviaBaralho protocoloenviabaralho;
	ProtocoloInicioJogada protocoloiniciojogada;
	ProtocoloFimJogada protocolofimjogada;
	
	private Socket cliente;
	
	public void Init(){		
	}
	
	public void createSocket() throws IOException{
		this.servidor = new ServerSocket(this.nPorta);		
	}
	
	public void aguardaJogadoresSk() throws IOException, ClassNotFoundException{
		
		int nCount = 1;
		
		while (nCount <= this.nJogadores){			
			try {
				Scanner scanner1 = new Scanner(System.in);
				System.out.println("Aguardando Cliente:"+nCount);
				
				Socket cliente = servidor.accept();
				
				protocoloenviabaralho = new ProtocoloEnviaBaralho("Voce e o jogador "+nCount, BaralhoList.get(nCount-1));

				ObjectOutputStream  oos = new ObjectOutputStream(cliente.getOutputStream());
				sendsomething(oos,protocoloenviabaralho);
				
				ObjectInputStream ois =  new ObjectInputStream(cliente.getInputStream());
				
				ProtocoloEnviaBaralho protocolo = (ProtocoloEnviaBaralho)returnsomething(ois);	
				System.out.println("Mensagem: "+protocolo.getcMensagem());

				System.out.println("Cliente conectado do IP "+cliente.getInetAddress().getHostAddress());

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
		// -- carrega as cartas do jogo 
		Init();		
		
		System.out.println("Informe o numero de jogadores:");	
		
		this.nJogadores = in.nextInt();
		this.players = new Boolean[nJogadores];
		Arrays.fill(players, Boolean.TRUE);
		this.jogadorDaRodada = random.nextInt(nJogadores);
		System.out.println("Jogador da Rodada:" +(jogadorDaRodada+1));
		createSocket();
		separaCartas();
		aguardaJogadoresSk();
		inicioJogada();
		jogada();
		
		
			
		
	}
	
	public void inicioJogada() throws IOException{
		for (int i = 0; i < nJogadores; i++) {
			//se não perdeu
			if(players[i]){
				if(i == jogadorDaRodada){
					//Jogador que escolhe o atributo
					protocoloiniciojogada = new ProtocoloInicioJogada(this.nJogada,Boolean.TRUE);
					sendsomething(SendObjList.get(i),protocoloiniciojogada);
									
				}else{
					//Jogador que espera
					protocoloiniciojogada = new ProtocoloInicioJogada(this.nJogada,Boolean.FALSE);
					sendsomething(SendObjList.get(i),protocoloiniciojogada);
				}
			}
		}
	}
	
	public void jogada() throws ClassNotFoundException, IOException{
		List<Carta> cartas = new ArrayList<Carta>();	
		Integer atributoDaRodada = 0;
		
		System.out.println("Jogador da Rodada "+(jogadorDaRodada+1));
		
		for (int i = 0; i < (nJogadores); i++) {
			if(i != jogadorDaRodada){
				System.out.println("Aguardando Jogador "+(i+1));
				ProtocoloJogadaEscolhida protocolo = (ProtocoloJogadaEscolhida)returnsomething(RecvObjList.get(i));
				System.out.println("Recebi do "+(i+1)+"\n");
				cartas.add(protocolo.getCartaEscolhida());
			}		

			
		}
		
		System.out.println("Jogador da rodada:"+(jogadorDaRodada+1));
		ProtocoloJogadaEscolhida protocolo = (ProtocoloJogadaEscolhida)returnsomething(RecvObjList.get(jogadorDaRodada));
		System.out.println("Recebi do "+(jogadorDaRodada+1));
		
		cartas.add(protocolo.getCartaEscolhida());
		atributoDaRodada = protocolo.getAtributoEscolhido();
		
		Integer auxJogador = 0;
		Float bestValue = 0F;
		
		for (int i = 0; i < nJogadores; i++) {
			if (pegaValorDoAtributo(cartas.get(i),atributoDaRodada) > bestValue){
				bestValue = pegaValorDoAtributo(cartas.get(i),atributoDaRodada);
				auxJogador = i;
			}
		}
		
		for (int i = 0; i < nJogadores; i++) {
			Baralho baralhoaux = new Baralho();
			baralhoaux.setCartas(cartas);			
			if(i != auxJogador){						
				protocolofimjogada = new ProtocoloFimJogada(false, "Voce perdeu",baralhoaux);
			}else{
				protocolofimjogada = new ProtocoloFimJogada(true, "Voce perdeu",baralhoaux);
			}
		}
			
		
		
		
		
	}
	
	public void separaCartas() {
		int nCountJgr = 0;
		Baralho auxBaralho = new Baralho();
		auxBaralho.Init();
		
		List<Carta> cartas = auxBaralho.getCartas();
		
		for (int i = 0; i < nJogadores; i++) {
			BaralhoList.add(new Baralho());
		}
		
		for (Carta carta : cartas) {
			
			if (nCountJgr==this.nJogadores) {
				nCountJgr = 0;
			}
			
			BaralhoList.get(nCountJgr).getCartas().add(carta);
			
			nCountJgr++;
			
		}
		
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
	
	/*
	public void returnsomething (ObjectInputStream ois) throws IOException, ClassNotFoundException{
		ProtocoloEnviaBaralho protocoloteste = (ProtocoloEnviaBaralho)ois.readObject();			
		System.out.println("Mensagem: "+protocoloteste.getcMensagem());
	}
	*/
	
	public float pegaValorDoAtributo (Carta carta, Integer atributo){
		switch (atributo) {
		case 1:
			return(carta.getnPeso());
			
		case 2:
			return(carta.getnVelocidade());
		
		case 3:
			return(carta.getnAltitude());
		
		case 4:
			return(carta.getnComprimento());
			
		case 5:
			return(carta.getnAltura());
		default:
		
			break;
		}
		return(666);
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException{
		Server s = new Server();
		s.rodaJogo();
	}
}
