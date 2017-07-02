package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import models.Carta;

public class Baralho {
	private List<Carta> cartas;
	private LeitorArquivos leitor;
	final   String cFileCard = "Baralho.txt";
	
	public void Init () {
		carregaCartas();
		embaralhaCarta();
		
	}
	public Baralho() {
		this.leitor = new LeitorArquivos(this.cFileCard);
		this.cartas = new LinkedList<Carta>();
	}
		 
	private void embaralhaCarta(){
		long seed = System.nanoTime();
		Collections.shuffle(this.cartas, new Random(seed));
	}

	private void carregaCartas(){
		ArrayList<String[]> retFile = new ArrayList<String[]>();
		Iterator<String[]> it;
	
		retFile = this.leitor.leArquivo();
		it      = retFile.iterator();
		
		// -- carregara informacoes para a lista de cartoes
		while (it.hasNext()) {
			Carta auxCarta = new Carta();
			String[] strings = (String[]) it.next();
			
			auxCarta.setcCod(strings[0]);
			auxCarta.setcTipo(strings[1]);
			auxCarta.setcNome(strings[2]);
			auxCarta.setnPeso(Float.parseFloat(strings[3]));
			auxCarta.setnVelocidade(Float.parseFloat(strings[4]));
			auxCarta.setnAltitude(Float.parseFloat(strings[5]));
			auxCarta.setnComprimento(Float.parseFloat(strings[6]));
			auxCarta.setnAltura(Float.parseFloat(strings[7]));
						
			// --valida se eh uma carta supertrunfo
			auxCarta.setIsSTrunfo(strings[8].equals("1"));
			
			this.cartas.add(auxCarta);
			
		}
		
	}
	
	public List<Carta> getCartas() {
		return cartas;
	}
	public void setCartas(List<Carta> cartas) {
		this.cartas = cartas;
	}
	
	
	
	
}