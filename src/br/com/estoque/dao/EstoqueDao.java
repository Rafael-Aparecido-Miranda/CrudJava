package br.com.estoque.dao;

import br.com.estoque.BindParameter;
import br.com.estoque.BindResolver;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.estoque.factory.ConnectionFactory;
import br.com.estoque.model.Estoque;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

/*
CLASSE REFATORADA POR RAFAEL OLIVEIRA E ADAM RICHARD
*/

public class EstoqueDao {
	/*
	 * CRUD
	 * C = CREATE - OK
	 * R = READ - OK
	 * U = UPDATE - OK
	 * D = DELETE - OK
	 */

	public void save(Estoque estoque) {
		String sql = """
				INSERT INTO ESTOQUEREGISTRO.ESTOQUE(
					NOME,
					MODELO,
					DESCRICAO,
					COD_FABRICANTE,
					QUANT_ATUAL,
					QUANT_MIN,
					MARCA,
					DATE_REGISTER,
					POSICAO_PRATELEIRA,
					SETOR
				) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
				""";

		Connection conn = null;

		PreparedStatement pstm = null;

		try {
			// Cria uma conex�o com o banco de dados
			conn = ConnectionFactory.createConnectionToMySQL();

			// Criado um PreparedStatement, para executar uma Query

			pstm = (PreparedStatement) conn.prepareStatement(sql);

			new BindResolver(pstm,
					new BindParameter(estoque.getNome()),
					new BindParameter(estoque.getModelo()),
					new BindParameter(estoque.getDescricao()),
					new BindParameter(estoque.getCod_fabricante()),
					new BindParameter(estoque.getQuant_atual()),
					new BindParameter(estoque.getQuant_min()),
					new BindParameter(estoque.getMarca()),
					new BindParameter(new Date(estoque.getDate_register().getTime())),
					new BindParameter(estoque.getPosicao_prateleira()),
					new BindParameter(estoque.getSetor()));

			// executar a query
			pstm.execute();

			JOptionPane.showMessageDialog(null, "Adicionado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

		} catch (Exception error) {
			JOptionPane.showMessageDialog(null, "Erro : \n" + error, "Erro", JOptionPane.INFORMATION_MESSAGE);
		} finally {
			// Fechar as conex�es
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception error) {
				error.printStackTrace();
			}
		}
	}

	public List<Estoque> getEstoqueRegistro() {
		String sql = "SELECT * FROM ESTOQUE";

		List<Estoque> estoqueList = new ArrayList<Estoque>();

		Connection conn = null;
		PreparedStatement pstm = null;

		// Classe que vai recuperar os dados do banco.
		ResultSet rset = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();

			pstm = (PreparedStatement) conn.prepareStatement(sql);
			rset = pstm.executeQuery();

			// enquanto o rset tiver dados para percorrer executa
			while (rset.next()) {

				Estoque estoque = this.createEstoqueFromResultSet(rset);
				/*
				 * Pega as informa��es que est�o no banco separa
				 * para poder alimentar a lista com os itens do estoque
				 */

				estoqueList.add(estoque);
			}
		} catch (Exception error) {
			error.printStackTrace();
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception error) {
				error.printStackTrace();
			}
		}
		return estoqueList;
	}

	public void update(Estoque estoque) {
		String sql = """
				UPDATE ESTOQUE SET
					NOME = ?,
					MODELO = ?,
					DESCRICAO = ?,
					QUANT_ATUAL = ?,
					QUANT_MIN = ?,
					MARCA = ?,
					DATE_REGISTER = ?,
					POSICAO_PRATELEIRA = ?
				WHERE ID_ESTOQUE = ?
				""";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Criar conex�o com o banco
			conn = ConnectionFactory.createConnectionToMySQL();

			// Criar a classe para executar a query
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			/*
			 * BindParameter pega os valores setados e o BindResolver identifica o tipo
			 * de parametro que e vai alocando conforme a ordem estabelecida
			 * Depois e instanciado no update possibilitando reutilizaçao do mesmo
			 */
			new BindResolver(pstm,
					new BindParameter(estoque.getNome()),
					new BindParameter(estoque.getModelo()),
					new BindParameter(estoque.getDescricao()),
					new BindParameter(estoque.getQuant_atual()),
					new BindParameter(estoque.getQuant_min()),
					new BindParameter(estoque.getMarca()),
					new BindParameter(new Date(estoque.getDate_register().getTime())),
					new BindParameter(estoque.getPosicao_prateleira()));

			pstm.execute();
		} catch (Exception error) {
			error.printStackTrace();
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException error) {
				error.printStackTrace();
			}
		}
	}

	public void deleteByID(Estoque estoque) {
		String sql = """
				DELETE FROM ESTOQUEREGISTRO.ESTOQUE
				WHERE ID_ESTOQUE = ?
				""";

		Connection conn = null;

		PreparedStatement pstm = null;

		try {
			// String date = new
			// SimpleDateFormat("yyyy-MM-dd").format(estoque.getDate_register());
			conn = ConnectionFactory.createConnectionToMySQL();

			pstm = (PreparedStatement) conn.prepareStatement(sql);

			// VALIDO PARA O COMENTARIO NA LINHA 157 A 159

			new BindResolver(pstm, new BindParameter(estoque.getId_estoque()));

			pstm.execute();
			JOptionPane.showMessageDialog(null, "Removido com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

		} catch (Exception error) {
			JOptionPane.showMessageDialog(null, "Erro ao remover: \n" + error, "Erro", JOptionPane.INFORMATION_MESSAGE);
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException error) {
				JOptionPane.showMessageDialog(null, "Erro ao desconectar: " + error, "Erro",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	public Estoque update(int index) {
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

	public Estoque createEstoqueFromResultSet(ResultSet rset) throws Exception {
		Estoque estoque = new Estoque();

		/*
		 * Adicionado throws Exception para cair no try catch pai
		 */

		estoque.setId_estoque(rset.getInt("ID_ESTOQUE"));
		estoque.setNome(rset.getString("NOME"));
		estoque.setModelo(rset.getString("MODELO"));
		estoque.setMarca(rset.getString("MARCA"));
		estoque.setSetor(rset.getString("SETOR"));
		estoque.setPosicao_prateleira(rset.getString("POSICAO_PRATELEIRA"));
		estoque.setQuant_atual(rset.getInt("QUANT_ATUAL"));
		estoque.setQuant_min(rset.getInt("QUANT_MIN"));
		estoque.setCod_fabricante(rset.getString("COD_FABRICANTE"));
		estoque.setDate_register(rset.getDate("DATE_REGISTER"));
		estoque.setDescricao(rset.getString("DESCRICAO"));
		// estoque.setFoto(rset.getString("FOTO"));

		return estoque;
	}
}
