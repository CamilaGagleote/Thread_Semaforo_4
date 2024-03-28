package controller;

import java.util.concurrent.Semaphore;

public class PortaController extends Thread {

	private int idPessoa;
	private Semaphore semaforo2;
	
	public PortaController(int idPessoa, Semaphore semaforo2) {
		this.idPessoa = idPessoa;
		this.semaforo2 = semaforo2;
	}
	
	
	public void run() {
		correndo();
	}
	
	private void correndo() {
		int tamCorredor = 200;
		int distPercorrida = (int)((Math.random()*2.1) + 4);;
		int deslocamento = 0;
		int tempo = (int)((Math.random()*1001) + 0);
		while(deslocamento < tamCorredor) {
			deslocamento+= distPercorrida;
			try {
				sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("A pessoa " + idPessoa+ " ja andou " + deslocamento + "m");
		}
		if(deslocamento == 200) {
			System.out.println("A pessoa " + idPessoa+ " chegou na porta ");
			try {
				semaforo2.acquire();
				porta();	
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally {
				semaforo2.release();
			}
		}
	}
	private void porta() {
		int tempo = (int)((Math.random()*1001) + 1000);
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("A pessoa " + idPessoa + " cruzou a porta");
	}

}
