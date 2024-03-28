package controller;

import java.text.DecimalFormat;
import java.util.concurrent.Semaphore;

import javax.swing.JOptionPane;

public class BancoController extends Thread{

	private int idConta;
	private Semaphore semaforoSaque;
	private Semaphore semaforoDeposito;
	private int codConta = 0;
	private int saldo = 0;
	private int valorTransacao = 0;
	private int tipoTransacao = 0;
	
	public BancoController(int idConta, Semaphore semaforoSaque, Semaphore semaforoDeposito) {
		this.idConta = idConta;
		this.semaforoSaque = semaforoSaque;
		this.semaforoDeposito = semaforoDeposito;
	}
	
	public void run() {
		Transacao();
	}
	
	private void Transacao() {
		codConta =(int) ((Math.random()*2000)+1000);
	    saldo = (int) ((Math.random()*40000)+2000);
		valorTransacao =(int) ((Math.random()*2000)+100);
		tipoTransacao = (int) ((Math.random()*1.1)+1.1);
		if(tipoTransacao == 1) {
			 try {
					semaforoSaque.acquire();
					Saque();	
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally {
					semaforoSaque.release();
				}
		}else {			
			try {
				semaforoDeposito.acquire();
				Deposito();	
			} catch (InterruptedException e) {
					e.printStackTrace();
			}finally {
				semaforoDeposito.release();
			}
		}
					
	}
	private void Saque() {
		int saldoNovo;
		saldoNovo = saldo - valorTransacao;
		DecimalFormat f = new DecimalFormat("#,##0.00");
		System.out.println("A conta #" +idConta + " realizou um saque, portanto o novo saldo da sua conta é R$ " + f.format(saldoNovo));
	}
	private void Deposito() {
		int saldoNovo;
		saldoNovo = saldo + valorTransacao;
		DecimalFormat f = new DecimalFormat("#,##0.00");
		System.out.println("A conta #" +idConta + " realizou um deposito, portanto o novo saldo da sua conta é R$ " + f.format(saldoNovo));
	}

}
