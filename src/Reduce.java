import java.util.ArrayList;
/*
 * La classe Reduce s'encarrega de fer el reducing del resultat obtingut 
 * de fer la part del shufling. Al ser una classe que extend de Thread,
 * la funcio reducing te en compta que pot estar executant la part corresponent
 * al reducing de l'arlgorisme MapReduce en paral·lel, pel que calcula a cada
 * thread quina part del lShufling ha de fer el reduce
 */
public class Reduce extends Thread{
	
	private static ArrayList<Pair> lReducing = new ArrayList<Pair>();
	private ArrayList<ArrayList<Pair>> lShufling;
	
	private int threadId;
	private  int numThreads;

	public Reduce(int threadId, int numThreads, ArrayList<ArrayList<Pair>> lShufling) {
		super(Integer.toString(threadId));	
		
		this.numThreads=numThreads;
		this.threadId=threadId;
		this.lShufling=lShufling;
	}
	
	@Override
	public void run() {
		reducing();		
	}
	
	private void reducing() {	
		int lSize = lShufling.size();
		int nPositions = lSize/numThreads;
		
		if(threadId==numThreads-1) {
			nPositions +=lSize%numThreads;
		}		
		
		ArrayList<Pair> lPair;
		
		for(int i=0;i<nPositions;i++) {
			
			//Aqui no fiquem nPositions per a que cuadri la funcio amb l'ultima iteracio			
			lPair = lShufling.get(threadId*(lSize/numThreads)+i);
								
			int sumValue = 0;		
			for(Pair pair : lPair) {
				sumValue+=pair.getValue();
			}
			lReducing.add(new Pair(lPair.get(0).getKey(),sumValue));		
		}
	}
	
	public static ArrayList<Pair> getLReducing() {
		return  lReducing;
	}
}