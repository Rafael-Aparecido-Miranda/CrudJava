package br.com.estoque.calypto;

import java.util.Date;
import java.util.Scanner;

import br.com.estoque.dao.EstoqueDao;
import br.com.estoque.model.Estoque;
import br.com.estoque.telas.TelaCadastro;

public class Principal {

	public static void main(String[] args) throws Exception {

		Scanner digite = new Scanner(System.in);
		Estoque estoque = new Estoque();
		Estoque estoque1 = new Estoque();
		EstoqueDao estoqueDao = new EstoqueDao();

		TelaCadastro inicio = new TelaCadastro();

		inicio.iniciar();

	}
}
