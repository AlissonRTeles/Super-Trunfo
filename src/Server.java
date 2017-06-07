
public class Server {
	public void Init(){
		LeitorArquivos l = new LeitorArquivos("Baralho.txt");
		l.leArquivo();
	}
	
	public static void main(String[] args) {
		Server s = new Server();
		System.out.println("Teste");
		s.Init();
		
	}
}
