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
			System.out.println("파일이 존재하지 않습니다.");
		} catch(IOException ioe){
			System.out.println("파일을 읽을 수 없습니다.");
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
			System.out.println("파일을 출력할 수 없습니다.");
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
		
		String code; // 상품명
		int stocks, select=0; //수량, 프로그램 조작 버튼
		
		
		
		System.out.println("재고 관리 프로그램을 실행합니다.\n");
		readFile("stocks.txt");
		
		
		//반복문 사용하여 반복문이 종료될 때까지 계속 실행.
		while(select!=5) {
			
			//조작에 사용될 번호들의 정보 출력
			System.out.print("1.입고 2.출고 3.삭제 4.현황 5.종료 :");
			
			//조작할 번호를 스캐너를 통해 입력받는다.
			select = sc.nextInt();
			System.out.println();
			
			
			//입력받은 번호를 스위치문을 사용해서 조작한다.
			switch (select) {
			//1번 입력시 입고실행
			case 1:
				System.out.print("상품명 : "); code = sc.next(); // 상품명을 먼저 입력받음.
				System.out.print("수량 : "); stocks = sc.nextInt(); // 해당 상품의 수량을 입력받음.
				
				Integer cnt = goodsMap.get(code);
				
				if(cnt == null) { // code테이블이 비어있으면 stocks를 넣는다.
					goodsMap.put(code, stocks);
				}else { // code 서랍에 숫자가 있으면 stocks 만큼 추가한다.
					goodsMap.put(code, cnt+stocks);
				}
				writeFile("stocks.txt");
				line();
				
				break;
				
			//2번 입력시 출고 실행	
			case 2:
				
				//전역변수 cnt가 0이면 등록된 재고가 하나도 없으므로 등록된 물품이 없음을 출력, 0이 아니면 등록된 재고가 있으므로 출고할 상품명을 입력받음.
				if(goodsMap.isEmpty()) {
					System.out.println("등록된 상품이 없습니다.\n");
				}else {
					
					//출고할 상품명을 입력받았을 때 해당 상품명이 재고배열에 존재하는지 확인 후 없으면 해당 물품이 없음을 출력
					System.out.print("상품명 : "); code = sc.next();
					cnt = goodsMap.get(code);
					
					if(cnt==null) {
						System.out.println("해당하는 재고가 없습니다.\n");
					}else {
						System.out.print("수량 : "); stocks = sc.nextInt();
						if(cnt<stocks) {
							System.out.println("재고가 부족합니다.\n");
						}else {
							goodsMap.put(code, cnt-stocks);
						}
					}
					
				}
				writeFile("stocks.txt");
				line();
				break;
				
			//3번 입력시 삭제 실행	
			case 3:
				
				//2번과 같이 전역변수 cnt가 0이면 등록된 재고가 하나도 없으므로 등록된 물품이 없음을 출력, 0이 아니면 등록된 재고가 있으므로 출고할 상품명을 입력받음.
				if(goodsMap.isEmpty()) {
					System.out.println("등록된 상품이 없습니다.\n");
				}else {
					
					//출고할 상품명을 입력받았을 때 해당 상품명이 재고배열에 존재하는지 확인 후 없으면 해당 물품이 없음을 출력
					System.out.print("상품명 : "); code = sc.next();
					cnt = goodsMap.get(code);
					if(cnt==null) {
						System.out.println("등록된 상품이 없습니다.\n");
					}else {
						goodsMap.remove(code);
					}
					//해당하는 재고가 존재하면 delGoods(g, delCnt)메소드를 수행. 
				}
				writeFile("stocks.txt");
				line();
				break;
				
			//4번 입력시 등록된 재고의 이름과 수량을 모두 보여줌.	
			case 4:
				System.out.println("재고 현황을 출력합니다.\n");
				
				for(String goods : goodsMap.keySet()) {
					System.out.println(goods + " : "+ goodsMap.get(goods));
				}
				line();
				
				break;
				
			//5번 입력시 프로그램을 종료하고 반복문을 빠져나감.	
			case 5:
				System.out.println("프로그램을 종료합니다.");
				break;

			default:
				System.out.println("올바른 번호를 입력해주세요.\n");
				line();
				break;
			}
			
		}


	}

}
