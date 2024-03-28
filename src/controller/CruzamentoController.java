package controller;

import java.util.concurrent.Semaphore;


public class CruzamentoController extends Thread{

		private int idCarro;
		private String sentido[];
		private Semaphore semaforo1;
		int i = (int)((Math.random() *4.1 ) + 0);
		
	public CruzamentoController(int idCarro, String sentido[], Semaphore semaforo1) {
		this.idCarro = idCarro;
		this.sentido = sentido;
		this.semaforo1 = semaforo1;
	}
	
	public void run() {
		chegando();
		esperando();
	}
	
	private void chegando() {
		System.out.println("O carro #" + idCarro + " chegou no cruzamento pelo sentido " + sentido[i]);
	
	
	}
	
	private void esperando() {
		try {
			semaforo1.acquire();
			cruzando();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			semaforo1.release();
			System.out.println("Carro "+ idCarro + " passou.");
		}
		
	}
	private void cruzando() {
		int tempo = (int) ((Math.random()* 2001) + 2000);
		System.out.println("O carro #" + idCarro + " está passando pelo cruzamento na direcao " + sentido[i]);
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
