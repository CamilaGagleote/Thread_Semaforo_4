package view;

import java.util.concurrent.Semaphore;

import controller.PortaController;
import controller.BancoController;
import controller.Formula1Controller;
import controller.CruzamentoController;

public class Principal {

	public static void main(String[] args) {
		//exercício 1 - cruzamento carro
		String sentido[] = {"Norte", "Sul", "Leste", "Oeste"};
		int permissao1 = 1;
		Semaphore semaforo1 = new Semaphore(permissao1);
		for(int idCarro = 0; idCarro < 4; idCarro++) {
			Thread tcruza = new CruzamentoController(idCarro, sentido, semaforo1);
			tcruza.start();
		}
			
		//exercício 2 - corrida
		int permissao2 = 1;
		Semaphore semaforo2 = new Semaphore(permissao2);
		for(int idPessoa = 1; idPessoa < 5; idPessoa ++) {
			Thread tpessoa = new PortaController(idPessoa, semaforo2);
			tpessoa.start();
		}
		
		//exercício 3 - transações banco
		int permissaoS = 1;
		Semaphore semaforoSaque = new Semaphore(permissaoS);
		int permissaoD = 1;
		Semaphore semaforoDeposito = new Semaphore(permissaoD);
		for(int idConta = 1; idConta < 21; idConta ++) {
			Thread tbanco = new BancoController(idConta, semaforoSaque, semaforoDeposito);
			tbanco.start();
		}

		
		//exercício 4 - treino fórmula 1
//		for(int idMotorista = 1; idMotorista < 15;idMotorista++){
//			Thread tcorrida = new Formula1Controller();
//			
//		}
	}
}

