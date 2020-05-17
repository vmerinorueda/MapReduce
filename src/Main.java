
/*
 * La classe Main es la conte el metode main el qual rep com a parametres d'execucio
 *  el numero de threads amb que es vol executar l'algorisme MapReduce i la ruta dels
 *  fitxers a analitzar. 
 *  P.e: 6 File1.txt
 */
public class Main {	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();;		
		
		Integer numArgs = args.length;
		if(numArgs>1) {
			int numThreads = Integer.parseInt(args[0]);
			
			for(int i = 1; i < numArgs; i++) {
				System.out.println(args[i]+":");
				new MapReduce(numThreads, args[i]);
				
				if(numArgs > 2 && i != numArgs-1) {
					System.out.print("\n\n");
				}
			}
		   
		}else {
			System.out.println("Introduzca argumentos válidos [numero de threads, "
					+ "lineas por thread, nombre de ficheros]");
		}
		
		long end = System.currentTimeMillis();;
		System.out.println((end - start) + " ms");
   }
}
