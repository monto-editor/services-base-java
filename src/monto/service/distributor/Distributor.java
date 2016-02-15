package monto.service.distributor;

import java.util.HashMap;

public class Distributor {

	//JSON-Objekte sollen gespeichert werden
	private static HashMap<Integer, Object> cache = new HashMap<>();
	private static int currentKeyInt = 0;

	public Integer put(Object content){
		Integer generatedKey = currentKeyInt;
		currentKeyInt++;
		cache.put(generatedKey, content);
		return generatedKey;
	}

	public Object get(Integer key) throws InvalidKeyException{
		Object contents = cache.get(key);
		if (contents == null){
			throw new InvalidKeyException("No content could be found with the key: " + key);
		} else {
			return contents;
		}
	}



}
