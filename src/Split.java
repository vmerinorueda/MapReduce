import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
/*
 * Classe encarrega de dur a terme la part de l'Split.
 * S'encarrega de dividir en blocs de files els text a inspeccionar,
 * fusionant aquest bloc de files en una unica sola.
 * En el cas de no tenir una divisio sencera, les linias restants s'asignen
 * a l'ultim thread.
 */
public class Split {

	private int numThreads;
	
	public Split(int numThreads){
		this.numThreads = numThreads;
	}
		
	public String[] splitting(String fileName)  {
		Path path;
		long numLines;
		int linesThread;
		String[] lLines = new String[numThreads];
		
		try {
			path = Paths.get("./"+fileName);
			numLines = Files.lines(path).count();
			linesThread = (int) (numLines/numThreads);
			
			lLines = readLines(fileName, linesThread);
			
		} catch (IOException e) {
			e.printStackTrace();
		}				
		
		return lLines;
	}
	 
	private String[] readLines(String fileName, int linesThread)  {
		BufferedReader bf;
		FileReader fr;
		String line, nextLine;
		String[] lLines = new String[numThreads];
		int threadIndex = 0;
		int countLine;
		
		try {
			fr = new FileReader(fileName);		
			bf = new BufferedReader(fr);
		
			//Si han sobrat linias de la divisio (la resta) s'assignan a l'ultim thread
			while((line = bf.readLine())!=null) {	
										
				if(threadIndex < numThreads) {	
					countLine = 1;
					
					while(countLine < linesThread && ((nextLine = bf.readLine())!=null)) {	
						line += " " + nextLine;	
						countLine++;
					}
					lLines[threadIndex] = line;
					threadIndex++;					
					
				}else {
					while(((nextLine = bf.readLine())!=null)) {
						line += " " + nextLine;	
					}					
					lLines[numThreads-1] = lLines[numThreads-1] + " " +line ;
				}	
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return lLines;
	}
	 
}
