package br.com.estoque.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.estoque.factory.ConnectionFactory;
import br.com.estoque.model.Estoque;

public class EstoqueDao 
{
	/*
	 * CRUD
	 * C = CREATE - OK
	 * R = READ - OK
	 * U = UPDATE - OK
	 * D = DELETE - OK
	 */
	
	public void save(Estoque estoque) 
	{
		String sql = "INSERT INTO ESTOQUEREGISTRO.ESTOQUE(NOME, MODELO, DESCRICAO, COD_FABRICANTE, QUANT_ATUAL, QUANT_MIN, MARCA, DATE_REGISTER, POSICAO_PRATELEIRA, SETOR) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		Connection conn = null;
		
		PreparedStatement pstm = null;
		
		try 
		{
			//Cria uma conex�o com o banco de dados
			conn = ConnectionFactory.createConnectionToMySQL();
			
			//Criado um PreparedStatement, para executar uma Query
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, estoque.getNome());
			pstm.setString(2, estoque.getModelo());
			pstm.setString(3, estoque.getDescricao());
			pstm.setString(4, estoque.getCod_fabricante());
			pstm.setInt(5, estoque.getQuant_atual());
			pstm.setInt(6, estoque.getQuant_min());
			pstm.setString(7, estoque.getMarca());
			pstm.setDate(8, new Date(estoque.getDate_register().getTime()));
			pstm.setString(9, estoque.getPosicao_prateleira());
			pstm.setString(10, estoque.getSetor());
			
			//executar a query
			pstm.execute();	
			
			JOptionPane.showMessageDialog(null, "Adicionado com sucesso","Sucesso", JOptionPane.INFORMATION_MESSAGE);
			
		}
		catch (Exception error) 
		{
			error.printStackTrace();
		}
		finally 
		{
			//Fechar as conex�es
			try 
			{
				if(pstm != null) 
				{
					pstm.close();
				}
				if(conn !=null)
				{
					conn.close();
				}
			}catch ( Exception error) 
			{
				error.printStackTrace();
			}
		}
	}
	
	public List<Estoque> getEstoqueRegistro()
{
		String sql = "SELECT * FROM ESTOQUE";
		
		List<Estoque> estoqueList = new ArrayList<Estoque>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		//Classe que vai recuperar os dados do banco.
		ResultSet rset = null;
		
		try 
		{
			conn = ConnectionFactory.createConnectionToMySQL();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			rset = pstm.executeQuery();
			
			//enquanto o rset tiver dados para percorrer executa
			while(rset.next()) 
			{
				Estoque estoque = new Estoque();
				
				/*
				 * Pega as informa��es que est�o no banco separa 
				 * para poder alimentar a lista com os itens do estoque
				 */
				
				estoque.setId_estoque(rset.getInt("ID_ESTOQUE"));
				estoque.setNome(rset.getString("NOME"));
				estoque.setModelo(rset.getString("MODELO"));
				estoque.setFoto(rset.getString("FOTO"));
				estoque.setDescricao(rset.getString("DESCRICAO"));
				estoque.setCod_fabricante(rset.getString("COD_FABRICANTE"));
				estoque.setQuant_atual(rset.getInt("QUANT_ATUAL"));
				estoque.setQuant_min(rset.getInt("QUANT_MIN"));
				estoque.setMarca(rset.getString("MARCA"));
				estoque.setDate_register(rset.getDate("DATE_REGISTER"));
				estoque.setPosicao_prateleira(rset.getString("POSICAO_PRATELEIRA"));
				estoque.setSetor(rset.getString("SETOR"));
				
				estoqueList.add(estoque);
			}
		}
			catch(Exception error) 
			{	
				error.printStackTrace();
			}
		finally
		{	
                    try 
                    {
                        if(rset != null) 
                        {
                            rset.close();
                        }
                        if(pstm != null) 
                        {
                            pstm.close();
                        }
                        if(conn != null) 
                        {
                            conn.close();
                        }
                    }
                    catch(Exception error) 
                    {
                        error.printStackTrace();
                    }
		}
		return estoqueList;
	}
	
	public void update(Estoque estoque) 
	{
		String sql = "UPDATE ESTOQUE SET NOME = ?, MODELO = ?, DESCRICAO = ?, QUANT_ATUAL = ?, QUANT_MIN = ?, MARCA = ?, DATE_REGISTER = ?, POSICAO_PRATELEIRA = ? " + "WHERE ID_ESTOQUE = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try 
		{
			//Criar conex�o com o banco
			conn = ConnectionFactory.createConnectionToMySQL();
			
			//Criar a classe para executar a query
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			pstm.setString(1, estoque.getNome());
			pstm.setString(2, estoque.getModelo());
			pstm.setString(3, estoque.getDescricao());
			pstm.setInt(4, estoque.getQuant_atual());
			pstm.setInt(5, estoque.getQuant_min());
			pstm.setString(6, estoque.getMarca());
			pstm.setDate(7, new Date(estoque.getDate_register().getTime()));
			pstm.setString(8, estoque.getPosicao_prateleira());
			
			pstm.setInt(9, estoque.getId_estoque());
			
			pstm.execute();
		}
		catch(Exception error)
		{
			error.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(pstm != null) 
				{
					pstm.close();
				}
				if(conn != null) 
				{
					conn.close();
				}
			}
			catch(Exception error) 
			{
				error.printStackTrace();
			}
		}
	}
	
	public void deleteByID(int id) 
	{
		String sql = "DELETE FROM ESTOQUE WHERE ID_ESTOQUE = ?";
		
		Connection conn = null;
		
		PreparedStatement pstm = null;
		
		try 
		{
			conn = ConnectionFactory.createConnectionToMySQL();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			pstm.setInt(1, id);
			
			pstm.execute();
			
		}
		catch(Exception error)
		{
			error.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(pstm != null) 
				{
					pstm.close();
				}
				if(conn != null) 
				{
					conn.close();
				}
			}
			catch(Exception error) 
			{
				error.printStackTrace();
			}
		}
	}
}
