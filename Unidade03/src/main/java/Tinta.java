// 853355 Davi Puddo

public class Tinta 
{
	private int id;
	private int tamanho;
	private String cor;
	private String base;
	
	Tinta (int i, int t, String c, String b)
	{
		id = 0;
		tamanho = 0;
		cor = null;
		base = null;
		
		if (i > 0 && t > 0 && c.length() > 0 && b.length() > 0)
		{
			id = i;
			tamanho = t;
			cor = c;
			base = b;
		}
	}
	
	Tinta ()
	{
		this (0, 0, null, null);
	}
	
	int getID ()
	{
		return (this.id);
	}
	
	int getSize ()
	{
		return (this.tamanho);
	}
	
	String getColor ()
	{
		return (this.cor);
	}
	
	String getBase ()
	{
		return (this.base);
	}
	
	String getAll ()
	{
		String buffer = "ID: " + this.id + "\nTamanho: " + this.tamanho + "\nCor: " + this.cor + "\nBase: " + this.base;
		return (buffer);
	}
}
