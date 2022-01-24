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
import java.sql.SQLException;
import java.text.SimpleDateFormat;

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
			JOptionPane.showMessageDialog(null,"Erro : \n"+error,"Erro",JOptionPane.INFORMATION_MESSAGE);
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
                                estoque.setMarca(rset.getString("MARCA"));
                                estoque.setSetor(rset.getString("SETOR"));
                                estoque.setPosicao_prateleira(rset.getString("POSICAO_PRATELEIRA"));
                                estoque.setQuant_atual(rset.getInt("QUANT_ATUAL"));
				estoque.setQuant_min(rset.getInt("QUANT_MIN"));
                                estoque.setCod_fabricante(rset.getString("COD_FABRICANTE"));
                                estoque.setDate_register(rset.getDate("DATE_REGISTER"));
                                estoque.setDescricao(rset.getString("DESCRICAO"));
				//estoque.setFoto(rset.getString("FOTO"));
				
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
			catch(SQLException error) 
			{
				error.printStackTrace();
			}
		}
	}
	
	public void deleteByID(Estoque estoque) 
	{
		String sql = "DELETE FROM ESTOQUEREGISTRO.ESTOQUE WHERE ID_ESTOQUE = ? AND NOME = ? "
                        + "AND MODELO = ? AND MARCA = ? AND SETOR = ? AND POSICAO_PRATELEIRA "
                        + "AND QUANT_ATUAL = ? AND QUANT_MIN = ? AND COD_FABRICANTE = ? AND "
                        + "DATE_REGISTER = ? AND DESCRICAO = ?";
		
		Connection conn = null;
		
		PreparedStatement pstm = null;
		
		try 
		{
                    //String date = new SimpleDateFormat("yyyy-MM-dd").format(estoque.getDate_register());
			conn = ConnectionFactory.createConnectionToMySQL();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			pstm.setInt(1, estoque.getId_estoque());
                        pstm.setString(2,estoque.getNome());
                        pstm.setString(3, estoque.getModelo());
                        pstm.setString(4, estoque.getMarca());
                        pstm.setString(5, estoque.getSetor());
                        pstm.setString(6,estoque.getPosicao_prateleira());
                        pstm.setInt(7, estoque.getQuant_atual());
                        pstm.setInt(8, estoque.getQuant_min());
                        pstm.setString(9, estoque.getCod_fabricante());
                        pstm.setDate(10, (Date) estoque.getDate_register()) ;
                        //pstm.setString(11, estoque.getDescricao());
                        //pstm.setString(12, estoque.getFoto());
                        			
			pstm.execute();
                        JOptionPane.showMessageDialog(null, "Removido com sucesso", "Sucesso",JOptionPane.INFORMATION_MESSAGE);
			
		}
		catch(Exception error)
		{
			JOptionPane.showMessageDialog(null,"Erro ao remover: \n"+error,"Erro",JOptionPane.INFORMATION_MESSAGE);
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
			catch(SQLException error) 
			{
				JOptionPane.showMessageDialog(null,"Erro ao desconectar: "+error,"Erro",JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

    public Estoque update(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
