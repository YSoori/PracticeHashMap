import java.util.*;

public class WordsCount {
	
	public static void main(String[] args) {
		
		HashMap<String, Integer> w = new HashMap<String, Integer>();
		
		String str = "java C++ Java c++ java python";
		StringTokenizer st = new StringTokenizer(str.toLowerCase());
		
		while(st.hasMoreTokens()) {
			
			//tok���� �־��� �ܾ ����Ʈ�� �����ϴ��� Ȯ��.
			String tok = st.nextToken();
			
			//tok�� �ؽøʿ� ������ �ܾ��� ���� ���ϵȴ�.
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
