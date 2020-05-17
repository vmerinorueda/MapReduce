import java.util.ArrayList;
/*
 * La classe Map s'encarrega de fer la part del Mapping de la linia 
 * que se li passa al seu constructor. Es una classe que extend de 
 * Thread degut a que es dividira la part del Mapping total
 * entre els diferents numeros de threads que se li ha assignat 
 * al principi de l'execucio del programa.
 */
public class Map extends Thread{
	
	private static ArrayList<Pair> lMapping = new ArrayList<Pair>();	
	private String line;
	
	public Map(String threadId, int numThreads, String line) {
		super(threadId);	
		this.line=line;
	}
	
	@Override
	public void run() {
		mapping();
	}
	
	private  void mapping() {
		String[] paraules = line.split(" ");		
		for( String p : paraules) {
			p = p.replace(".", "").replace(",", "").replace(";", "");
			Pair pair = new Pair(p,1);
			
			lMapping.add(pair);
		}	
	} 
	
	public static ArrayList<Pair> getLMapping() {
		return  lMapping;
	}

}
