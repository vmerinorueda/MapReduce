import java.util.ArrayList;

/*
 * La classe MapReduce es la clase principal de l'algorisme conecgut com a MapReduce.
 * En aquest cas, el nostra MapReduce s'encarrega de comptar les vegades que apareixen 
 * les diverses paraules dels diferents arxius proporcionats.
 */
public class MapReduce {

	private ArrayList<Pair> lReducing;
	private ArrayList<Pair> lMapping;
	private ArrayList<ArrayList<Pair>> lShufling;
	private String[] lLines;
	
	public MapReduce(int numThreads,String fileName){
		
		lReducing = new ArrayList<Pair>();
		lShufling = new ArrayList<ArrayList<Pair>>();
		lMapping = new ArrayList<Pair>();
		
		doMapReduce(numThreads, fileName);			
	}
	
	/*
	 * El seguent metodes s'encarrega de dur a terme les diferents parts del MapReduce.
	 * 1.-Splitting
	 * 2.-Mapping
	 * 3.-Shuffling
	 * 4.-Reducing
	 * Executant en paral·lel la part 2 y 4.
	 */
	private void doMapReduce(int numThreads,String fileName) {
		Map[] tMapping = new Map[numThreads];
		Reduce[] tReducing = new Reduce[numThreads];
	
		try {			
			Split splitting = new Split(numThreads);
			lLines = splitting.splitting(fileName);				
			
			for(int i=0; i<tMapping.length;i++) {
				tMapping[i] = new Map (Integer.toString(i), numThreads, lLines[i]);
				tMapping[i].start();
				tMapping[i].join();
			}
			
			lMapping = Map.getLMapping();
	
			shuffling();	
			
			for(int i=0; i<numThreads;i++) {
				
				tReducing[i] = new Reduce (i,numThreads, lShufling);
											
				tReducing[i].start();
				tReducing[i].join();
			}
					
			lReducing = Reduce.getLReducing();
						
			printFinalResult();
			
		}catch(InterruptedException ie){
			ie.printStackTrace();
		}
	}
		
	private void shuffling() {
		Boolean find = false;

		for(Pair pair : lMapping) {
			find = false;
			
			if(!lShufling.isEmpty()) {
				for(ArrayList<Pair> lKey : lShufling) {
					//Busca si ja existeix la paraula a lShufling
					if ((lKey.get(0).getKey()).equals(pair.getKey())) {
						lKey.add(pair);
						find = true;
					}
				}
			}
			if(!find) {
				//No s'ha trobat la paraula a la llista, es crea una paraula nova
				ArrayList<Pair> lFind = new ArrayList<Pair>();
				lFind.add(pair);
				lShufling.add(lFind);
			}
		}
	}
			
	private void printFinalResult() {
		for(Pair lRed : lReducing) {
			System.out.println(lRed.getKey() + ": " + lRed.getValue());	
		}
	}
}
