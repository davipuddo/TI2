
import static spark.Spark.*;

public class App {
		
		// Chamar classe service
		public static TintaService TS = new TintaService();
	
		public static void main (String[] args)
		{	
			port (1234); // Definir porta
			
			staticFiles.location("/public");
			
			get ("/insert", (request, response) -> TS.insert(request, response));
			
			get ("/update", (request, response) -> TS.update(request, response));
			
			get ("/delete", (request, response) -> TS.delete(request, response));
			
			get ("/getAll", (request, response) -> TS.getAll(request, response));
		}
}
