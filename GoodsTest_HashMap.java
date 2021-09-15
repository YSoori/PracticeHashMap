import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class GoodsTest_HashMap {
	
	static void line() {
		System.out.println("\n==============================================\n");
	}
	
	static boolean readFile(String path) {
		LineNumberReader reader = null;
		try {
			reader = new LineNumberReader(new FileReader(path));
			while(true) {
				String str = reader.readLine();
				if(str==null) {
					break;
				}
				String[] result = str.split(" ");
				goodsMap.put(result[0], Integer.parseInt(result[1]));
			}
			
		} catch (FileNotFoundException fnfe) {
			System.out.println("������ �������� �ʽ��ϴ�.");
		} catch(IOException ioe){
			System.out.println("������ ���� �� �����ϴ�.");
		}finally {
		
			try {
				reader.close();
				return true;
			} catch (Exception e) {
				return false;
			}
		}
	}
	
	static boolean writeFile(String path) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(path);
			
			for(String a : goodsMap.keySet()) {
				writer.write(a+" "+goodsMap.get(a)+"\n");
			}
			
		} catch (FileNotFoundException fnfe) {
			System.out.println("������ ����� �� �����ϴ�.");
		} finally {
			try {
				writer.close();
				return true;
			} catch (Exception e) {
				return false;
			}
		}
	}
	static HashMap<String, Integer> goodsMap = new HashMap<String, Integer>();
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		String code; // ��ǰ��
		int stocks, select=0; //����, ���α׷� ���� ��ư
		
		
		
		System.out.println("��� ���� ���α׷��� �����մϴ�.\n");
		readFile("stocks.txt");
		
		
		//�ݺ��� ����Ͽ� �ݺ����� ����� ������ ��� ����.
		while(select!=5) {
			
			//���ۿ� ���� ��ȣ���� ���� ���
			System.out.print("1.�԰� 2.��� 3.���� 4.��Ȳ 5.���� :");
			
			//������ ��ȣ�� ��ĳ�ʸ� ���� �Է¹޴´�.
			select = sc.nextInt();
			System.out.println();
			
			
			//�Է¹��� ��ȣ�� ����ġ���� ����ؼ� �����Ѵ�.
			switch (select) {
			//1�� �Է½� �԰����
			case 1:
				System.out.print("��ǰ�� : "); code = sc.next(); // ��ǰ���� ���� �Է¹���.
				System.out.print("���� : "); stocks = sc.nextInt(); // �ش� ��ǰ�� ������ �Է¹���.
				
				Integer cnt = goodsMap.get(code);
				
				if(cnt == null) { // code���̺��� ��������� stocks�� �ִ´�.
					goodsMap.put(code, stocks);
				}else { // code ������ ���ڰ� ������ stocks ��ŭ �߰��Ѵ�.
					goodsMap.put(code, cnt+stocks);
				}
				writeFile("stocks.txt");
				line();
				
				break;
				
			//2�� �Է½� ��� ����	
			case 2:
				
				//�������� cnt�� 0�̸� ��ϵ� ��� �ϳ��� �����Ƿ� ��ϵ� ��ǰ�� ������ ���, 0�� �ƴϸ� ��ϵ� ��� �����Ƿ� ����� ��ǰ���� �Է¹���.
				if(goodsMap.isEmpty()) {
					System.out.println("��ϵ� ��ǰ�� �����ϴ�.\n");
				}else {
					
					//����� ��ǰ���� �Է¹޾��� �� �ش� ��ǰ���� ���迭�� �����ϴ��� Ȯ�� �� ������ �ش� ��ǰ�� ������ ���
					System.out.print("��ǰ�� : "); code = sc.next();
					cnt = goodsMap.get(code);
					
					if(cnt==null) {
						System.out.println("�ش��ϴ� ��� �����ϴ�.\n");
					}else {
						System.out.print("���� : "); stocks = sc.nextInt();
						if(cnt<stocks) {
							System.out.println("��� �����մϴ�.\n");
						}else {
							goodsMap.put(code, cnt-stocks);
						}
					}
					
				}
				writeFile("stocks.txt");
				line();
				break;
				
			//3�� �Է½� ���� ����	
			case 3:
				
				//2���� ���� �������� cnt�� 0�̸� ��ϵ� ��� �ϳ��� �����Ƿ� ��ϵ� ��ǰ�� ������ ���, 0�� �ƴϸ� ��ϵ� ��� �����Ƿ� ����� ��ǰ���� �Է¹���.
				if(goodsMap.isEmpty()) {
					System.out.println("��ϵ� ��ǰ�� �����ϴ�.\n");
				}else {
					
					//����� ��ǰ���� �Է¹޾��� �� �ش� ��ǰ���� ���迭�� �����ϴ��� Ȯ�� �� ������ �ش� ��ǰ�� ������ ���
					System.out.print("��ǰ�� : "); code = sc.next();
					cnt = goodsMap.get(code);
					if(cnt==null) {
						System.out.println("��ϵ� ��ǰ�� �����ϴ�.\n");
					}else {
						goodsMap.remove(code);
					}
					//�ش��ϴ� ��� �����ϸ� delGoods(g, delCnt)�޼ҵ带 ����. 
				}
				writeFile("stocks.txt");
				line();
				break;
				
			//4�� �Է½� ��ϵ� ����� �̸��� ������ ��� ������.	
			case 4:
				System.out.println("��� ��Ȳ�� ����մϴ�.\n");
				
				for(String goods : goodsMap.keySet()) {
					System.out.println(goods + " : "+ goodsMap.get(goods));
				}
				line();
				
				break;
				
			//5�� �Է½� ���α׷��� �����ϰ� �ݺ����� ��������.	
			case 5:
				System.out.println("���α׷��� �����մϴ�.");
				break;

			default:
				System.out.println("�ùٸ� ��ȣ�� �Է����ּ���.\n");
				line();
				break;
			}
			
		}


	}

}
