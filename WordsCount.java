import java.util.*;

public class WordsCount {
	
	public static void main(String[] args) {
		
		HashMap<String, Integer> w = new HashMap<String, Integer>();
		
		String str = "java C++ Java c++ java python";
		StringTokenizer st = new StringTokenizer(str.toLowerCase());
		
		while(st.hasMoreTokens()) {
			
			//tok으로 주어진 단어가 리스트에 존재하는지 확인.
			String tok = st.nextToken();
			
			//tok를 해시맵에 던지면 단어의 수가 리턴된다.
			Integer curr = w.get(tok);
			
			
			if(curr == null) {
				w.put(tok, 1);
			}else {
				w.put(tok, curr+1);
			}
		}
		
		
		for(String word : w.keySet()) {
			System.out.println(word + " : " + w.get(word));
		}
		
	}
}
