package controller;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import models.Carta;

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
	
	public ArrayList<String[]> leArquivo(){
		ArrayList<String[]> linhas = new ArrayList<String[]>();
		
		try {
			FileReader arq = new FileReader(this.nome);
			BufferedReader lerArq = new BufferedReader(arq);

			String linha = lerArq.readLine(); 
			
			while (linha != null) {
				linha = lerArq.readLine();
				
				if (linha != null){
					String[] entradas = linha.split(this.delimitador);
					linhas.add(entradas);
				}

			}
				lerArq.close();
				arq.close();
			} catch (IOException e) {
				e.getMessage();
			}
		
		return linhas;
	}

	public String getNome() {
		return nome;
	}

		
	
}
