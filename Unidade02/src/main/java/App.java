// 853355 Davi Puddo

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

// Gerenciar elementos do banco de dados
public class App {
	
	// Esperar acao do usuario
	public static void WaitInput()
	{
		System.out.println ("\nPressione <ENTER> para continuar");
		try
		{
			System.in.read();		
		}
		catch (Exception e)
		{}
	}
	
	// Ler ID
	public static int ReadID()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println ("ID: ");
		return (sc.nextInt());
	}
	
	// Ler idade
	public static int ReadAge()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println ("Idade: ");
		return (sc.nextInt());
	}
	
	// Ler login
	public static String ReadLogin()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println ("Login: ");
		return (sc.nextLine());
	}
	
	// Ler senha
	public static String ReadPassword()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println ("Senha: ");
		return (sc.nextLine());
	}
	
	// Ler todos os dados do usuario
	public static User ReadUser()
	{
		User res = null;
		User tmp = new User(ReadID(), ReadAge(), ReadLogin(), ReadPassword());
		if (tmp != null)
		{
			res = tmp;
		}
		return (res);
	}
	
	// Inserir um novo usuario
	public static void Insert (UserDAO UDAO)
	{
		System.out.println ("Forneca os seguintes valores para a insercao: ");
		
		// Criar novo usuario
		User user = ReadUser();
		
		int ID = user.getID();
		
		// Verificar existenci de usuario com o mesmo ID
		while (UDAO.get(ID) != null)
		{
			System.err.println ("O ID: " + ID + " ja existe tente novamente");
			ID = ReadID();
		}
		user.setID(ID);		// Alterar ID
		
		// Tentar inserir usuario no Banco de dados
		if (UDAO.insertUser(user) == true)
		{
			System.out.println ("Usuario inserido com sucesso");
		}
		WaitInput();
	}
	
	// Mostrar usuario [n]
	public static void Get (UserDAO UDAO)
	{
		int ID = ReadID();
		User user = UDAO.get(ID);
		if (user == null)
		{
			System.err.println ("Usuario nao encontrado");
		}
		else
		{
			System.out.println (user);				
		}
		WaitInput();
	}
	
	// Atualizar um usuario
	public static void Update (UserDAO UDAO)
	{
		System.out.println ("Forneca os seguintes valores para fazer a alteracao: ");
		
		User user = ReadUser();
		if (UDAO.update(user) == true)
		{
			System.out.println ("User successfully altered");
		}
		WaitInput();
	}
	
	// Deletar um usuario
	public static void Delete (UserDAO UDAO)
	{
		Scanner sc = new Scanner (System.in);
		
		System.out.println ("Forneca o ID para que sera apagado: ");
		int ID = ReadID();
		if (UDAO.get(ID) == null)
		{
			System.err.println ("Usuario nao encontrado. ID = " + ID);
		}
		else
		{
			// Confirmar escolha
			System.out.println ("O usuario sera apagado permanentemente deseja prosseguir? \n[S/N]");
			String buffer = sc.next();
			
			// Testar dados
			while (buffer.length() > 1)
			{
				System.err.println ("Entrada de dados invalida tente novamente");
				System.out.println ("[S/N]");
				buffer = sc.next();
			}
			
			if (buffer.charAt(0) == 'S' || buffer.charAt(0) == 's')
			{
				// Apagar usuario
				if (UDAO.delete(ID) == true)
				{
					System.out.println ("Usuario deletado com succeso");
				}
			}
			else
			{
				// Abortar operacao
				System.out.println ("Operacao abortada");
			}
		}
		sc.close();		// Deletar objeto de leitura
		WaitInput(); 
	}
	
	// Listar todos os usuarios
	public static void List (UserDAO UDAO)
	{
		List<User> user = UDAO.getOrderByID();
		for (int i = 0; i < user.size(); i++)
		{
			System.out.println(user.get(i));			
		}
		WaitInput();
	}
	
	// Escolher opcoes
	public static void main (String[] args)
	{	
		// Criar objetos
		XDAO XDAO = new XDAO();					// Conexao ao banco de dados
		UserDAO UDAO = new UserDAO();			// Gerenciador de usuarios do banco de dados
		Scanner sc = new Scanner(System.in);	// Leitor de dados
		
		// Tentar conexao
		XDAO.conectar();
		
		int opt = 0;
		
		do 
		{
			// Mostar e ler opcoes
			System.out.println ("\n1 - Insert \n2 - Get\n3 - Update\n4 - Delete");
			System.out.println ("5 - List\n0 - Sair");
			opt = sc.nextInt();
			
			if (opt == 1)			// Inserir
			{
				Insert (UDAO);		// Levar objeto de usuarios ao metodo
			}
			else if (opt == 2)		// Ler
			{
				Get (UDAO);
			}
			else if (opt == 3)		// Alterar
			{
				Update (UDAO);
			}
			else if (opt == 4)		// Deletar
			{
				Delete (UDAO);
			}
			else if (opt == 5)		// Listar todos		
			{
				List (UDAO);
			}
			else if (opt == 0)		// Sair
			{
				System.out.println ("Fechando aplicacao");
			}
			else
			{
				System.err.println ("Opcao invalida");
			}
			
		} while (opt != 0);
		
		sc.close();			// Deletar scanner
		UDAO.finalize();	// Finalizar conexoes	
		XDAO.close();
	}
}
