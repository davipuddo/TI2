
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TintaDAO extends DAO
{
	// Iniciar conecção
	public TintaDAO ()
	{
		super();
		conectar();
	}
	
	// Finalizar conecção
	public void finalize ()
	{
		close();
	}
	
	// Inserir tinta
	public boolean Insert (Tinta tin)
	{
		boolean status = false;
		try
		{
			Statement st = conexao.createStatement();
			String buffer = ("INSERT INTO tinta (id, volume, cor, base)" 
													+ " VALUES (" + tin.getID() + ","
																  + tin.getSize() + ",'"
																  + tin.getColor() + "', '"
																  + tin.getBase()+ "');");
			st.executeUpdate(buffer);
			st.close();
			status = true;
		}
		catch (SQLException e)
		{
			throw new RuntimeException(e);
		}
		return (status);
	}
	
	// Retornar tinta pelo id
	public Tinta Get (int id)
	{
		Tinta tin = null;
		
		try
		{
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String buffer = "SELECT * FROM tinta WHERE id=" + id;
			
			ResultSet rs = st.executeQuery(buffer);
			
			if (rs.next())
			{
				tin = new Tinta (rs.getInt("id"), rs.getInt("volume"), rs.getString("cor"), rs.getString("base"));
			}
			st.close();
			
		} 
		catch (SQLException e)
		{
			throw new RuntimeException(e);
		}
		
		return (tin);
	}
	
	// Inserir tinta
	public boolean Update (Tinta tin)
	{
		boolean status = false;
		try
		{
			Statement st = conexao.createStatement();
			String buffer = "UPDATE tinta SET volume = '"  
											+ tin.getSize() + "', cor = '"
											+ tin.getColor() + "', base = '"
											+ tin.getBase() + "' WHERE id = '"
											+ tin.getID() + "'";
			st.executeUpdate(buffer);
			st.close();
			status = true;
		}
		catch (SQLException e)
		{
			throw new RuntimeException(e);
		}
		return (status);
	}
	
	// Deletar tinta pelo ID
	public boolean Delete (int ID)
	{
		boolean status = false;
		try
		{
			Statement st = conexao.createStatement();
			String buffer = "DELETE FROM tinta WHERE id =" + ID;
			
			st.executeUpdate(buffer);
			st.close();
			status = true;
		}
		catch (SQLException e)
		{
			throw new RuntimeException(e);
		}
		return (status);
	}
	
	public List<Tinta> get () 
	{	
		String orderBy = "id";
		List<Tinta> Tintas = new ArrayList<Tinta>();
		
		try 
		{
			Statement st = conexao.createStatement();
			String buffer = "SELECT * FROM tinta ORDER BY " + orderBy;
			
			System.out.println(buffer);
			ResultSet rs = st.executeQuery(buffer);
			
	        while(rs.next()) 
	        {	            	
	        	Tinta t = new Tinta(rs.getInt("id"), rs.getInt("volume"), rs.getString("cor"), rs.getString("base"));
	            Tintas.add(t);
	        }
	        st.close();
		}
		catch (Exception e) 
		{
			throw new RuntimeException(e);
		}
		return (Tintas);
	}
}
