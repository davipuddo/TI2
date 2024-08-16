// 853355 Davi Puddo

public class User 
{	
	private int ID;
	private int age;
	private String login;
	private String password;
	
	// Construtor padrao
	User ()
	{
		ID = -1;
		age = -1;
		login = null;
		password = null;
	}
	
	// Construtor com dados
	public User (int ID, int age, String login, String senha)
	{
		this.ID = ID;
		this.age = age;
		this.login = login;
		this.password = senha;
	}
	
	// Retornar ID
	public int getID ()
	{
		return (this.ID);
	}
	
	// Definir ID
	protected void setID (int ID)
	{
		this.ID = ID;
	}
	
	// Retornar login
	public String getLogin ()
	{
		return (this.login);
	}
	
	// Definir login
	protected void setLogin (String login)
	{
		if (login != null)
		{
			this.login = login;			
		}
	}
	
	// Retornar senha
	public String getPassword ()
	{
		return (this.password);
	}
	
	// Definir senha
	protected void setPassword (String password)
	{
		if (password != null)
		{
			this.password = password;
		}
	}
	
	// Retornar idade
	public int getAge ()
	{
		return (this.age);	
	}
	
	// Definir idade
	protected void setAge (int age)
	{
		this.age = age;
	}
	
	// Concatenar valores em uma string
	public String toString()
	{
		return "User [ ID = " + ID + " Login = " + login + " Password = " + password + " Age = " + age + " ]";
	}
}
