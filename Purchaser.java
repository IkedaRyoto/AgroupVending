import java.util.Scanner;

public class Purchaser {
	
	public static int Input() {//お金の投入
		String inputtext = "";
		int input = 0;
		while(true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("お金を投入してください");
			inputtext = sc.next();
			
			try{
				input = Integer.parseInt(inputtext);
				break;
			}catch(java.lang.NumberFormatException e) {
				System.out.println("金額を入力してください");
			}
		}
		return input;
	}
	
	public static int InputButton(boolean judgeproduct[]) {//ボタンの入力
		String inputtext = "";
		int inputbutton;
		do {
			Scanner sc = new Scanner(System.in);
			System.out.println("ボタンを入力してください");
			inputtext = sc.next();
			try{
				inputbutton = Integer.parseInt(inputtext);
				if(inputbutton == -1 || (inputbutton >= 1 && inputbutton <= 7 && judgeproduct[inputbutton - 1] == false)) {
					break;
				}
			}catch(java.lang.NumberFormatException e) {
				//System.out.println("正しいボタンを入力してください");
			}
		}while(true);//条件を満たすまでループ
			
		
		return inputbutton;
	}
	
}
