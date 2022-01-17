package br.com.estoque.calypto;

import java.util.Date;
import java.util.Scanner;

import br.com.estoque.dao.EstoqueDao;
import br.com.estoque.model.Estoque;

public class Principal {

	public static void main(String[] args) {
		
		Scanner digite = new Scanner(System.in);
		Estoque estoque = new Estoque();
		Estoque estoque1 = new Estoque();
		EstoqueDao estoqueDao = new EstoqueDao();
		
		/*
		System.out.println("Digite o nome");
		estoque.setNome(digite.next());
		System.out.println("Digite o modelo");
		estoque.setModelo(digite.next());
		System.out.println("Digite a quantidade atual");
		estoque.setQuant_atual(digite.nextInt());
		System.out.println("Digite a quantidade mínima");
		estoque.setQuant_min(digite.nextInt());
		System.out.println("Digite o Posição na prateleira");
		estoque.setPosicao_prateleira(digite.next());
		System.out.println("Digite o setor");
		estoque.setSetor(digite.next());
		estoque.setDate_register(new Date());
		
		//salvando informações no banco
		estoqueDao.save(estoque); 
		
		//Atualizar o estoque
		System.out.println("Digite a quantidade atual");
		estoque1.setQuant_atual(digite.nextInt());
		System.out.println("Digite o Posição na prateleira");
		estoque1.setPosicao_prateleira(digite.next());
		System.out.println("Digite o modelo");
		estoque1.setModelo(digite.next());
		System.out.println("Digite o id");
		estoque1.setId_estoque(digite.nextInt());
		*/
		
		estoqueDao.deleteByID(digite.nextInt());
		//lendo os itens do banco
		for(Estoque e: estoqueDao.getEstoqueRegistro()) {
			System.out.println("Estoque: " + e.getNome() + e.getMarca());
		}
	}

}
