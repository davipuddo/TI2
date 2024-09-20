// 853355 Davi Puddo

import java.util.List;
import spark.Request;
import spark.Response;

public class TintaService {
	
	private DAO dao;
	private TintaDAO TDAO;
	
	TintaService ()
	{
		dao = new DAO();
		dao.conectar();
		
		TDAO = new TintaDAO();
	}
	
	Tinta fromRequest (Request rq)
	{
		Tinta tin = null;
		int id = 0;
		int vol = 0;
		String color = "";
		String base = "";
		String buffer = "";
		
		buffer = rq.queryParams("ID");
		id = Integer.parseInt(buffer);
		
		buffer = rq.queryParams("Volume");
		vol = Integer.parseInt(buffer);
		
		color = rq.queryParams("Cor");
		
		base = rq.queryParams("Base");
		
		tin = new Tinta(id, vol, color, base);
		
		return (tin);
	}
	
	String insert (Request rq, Response rs)
	{
		Tinta tin = null;
		String res = "";
		boolean status = false;
		
		tin = this.fromRequest(rq);
		
		try
		{
			status = TDAO.Insert(tin);
			if (status)
			{
				res = "Elemento de ID: [" + tin.getID() + "] foi adicionado ao banco de dados";
			}
			rs.status(201);
		} 
		catch (RuntimeException e)
		{
				res = "O elemento de ID: [" + tin.getID() + "] nao pode ser adicionado ao banco de dados";
				res += "<br>Codigo de erro: " + e;
				rs.status(501);
		}
		return (res);
	}
	
	// Atualizar tinta
	String update (Request rq, Response rs)
	{
		Tinta tin = null;
		String res = "";
		boolean status = false;
		
		tin = this.fromRequest(rq);				
		
		try
		{
			status = TDAO.Update(tin);
			
			if (status)
			{
				res = "Elemento de ID: [" + tin.getID() + "] foi alterado com sucesso.";
				rs.status(201);
			}
		} 
		catch(RuntimeException e)
		{
			res = "Elemento de ID: [" + tin.getID() + "] nao pode ser alterado.";
			res += "<br>Codigo de erro: " + e;
			rs.status(501);
		}
		
		return (res);
	}
	
	// Deletar tinta
	String delete (Request rq, Response rs)
	{
		int ID = -1;
		String res = "";
		boolean status = false;
		
		String buffer = rq.queryParams("ID"); 
		ID = Integer.parseInt(buffer);
		
		try
		{
			status = TDAO.Delete(ID);
			if (status)
			{
				res = "Elemento de ID: [" + ID + "] foi apagado com sucesso.";
				rs.status(201);
			}
		}
		catch (RuntimeException e)
		{
			res = "Elemento de ID: [" + ID + "] nao pode ser apagado.";
			res += "<br>Codigo de erro: " + e;
			rs.status(501);
		}
		
		return (res);
	}
	
	String getAll (Request rq, Response rs)
	{
		String res = "";
		List<Tinta> tintas = null;
		
		try
		{
			tintas = TDAO.get();
			if (tintas != null)
			{
				res = "Tintas:<br>";
				for (int i = 0; i < tintas.size(); i++)
				{
					res += tintas.get(i).getAll() + "<br>";
				}
			}
			rs.status(201);
		}
		catch (RuntimeException e)
		{
			res = "As tintas nao poderam ser encontradas.";
			res += "<br>Codigo de erro: " + e;
			rs.status(500);
		}
		
		return (res);
	}
}
