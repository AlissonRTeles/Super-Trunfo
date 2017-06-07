

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LeitorArquivos {
	String nome   = "";
	String delimitador =";";
	
	FileReader arq;
	BufferedReader lerArq;
	
	public LeitorArquivos(String nome) {
		this.nome = nome;
	}
	
	public LeitorArquivos() {}
	
	public void fechaFile(){
		try {
			this.arq.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void leArquivo(){
		
		try {
			FileReader arq = new FileReader(this.nome);
			BufferedReader lerArq = new BufferedReader(arq);

			String linha = lerArq.readLine(); 
			
			while (linha != null) {
				linha = lerArq.readLine();
				System.out.println(linha);
				
				if (linha != null){
					String[] entradas = linha.split(this.delimitador);
					for (int i = 0; i < entradas.length-1; i++) {
						
					}
					
				}

			}
				lerArq.close();
				arq.close();
			} catch (IOException e) {
				e.getMessage();
			}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
		
}
