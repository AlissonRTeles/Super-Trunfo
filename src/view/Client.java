package view;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;



public class Client{

	/************************************************************************************/
	public static void main(String [] args) throws IOException{
		
		try{			
			Socket cliente = new Socket("127.0.0.1",23456);
			
			//Recebendo mensagem do servidor
			BufferedReader in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

			String str = in.readLine();
			System.out.println(str);
				
			cliente.close();

		}catch(IOException e){
			e.printStackTrace();
		}
		
		
	}
	/************************************************************************************/

}

