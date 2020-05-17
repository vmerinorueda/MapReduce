/*
 * La classe Pair proporciona objectes de parelles clau-valor, on la clau es 
 * de tipus string i el valor de tipus int
 */
public class Pair{
 
	private int value;
	private String key;
	
	public Pair(String key, int value){
		this.value=value;
		this.key=key;	
	}
	
	public String getKey(){
		return key;
	}
	
	public int getValue() {
		return value;
	}	
	
}
