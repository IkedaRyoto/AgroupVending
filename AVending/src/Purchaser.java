import java.util.Scanner;

public class Purchaser {
	//金額入力メソッド
	public static int InputMethod() {//お金の投入
		String inputtext = "";
		int input = 0;
		while(true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("お金を投入してください。金額投入終了は0を押してください。");
			inputtext = sc.next();
			
			try{
				input = Integer.parseInt(inputtext);
				break;
			}catch(java.lang.NumberFormatException e) {
				System.out.println("不正な金額入力です。");
			}
		}
		return input;
	}
	//ボタン入力メソッド
	public static int InputButtonMethod(boolean judgeproduct[],boolean canchange[]) {//ボタンの入力
		String inputtext = "";
		int inputbutton;
		do {
			Scanner sc = new Scanner(System.in);
			System.out.println("ボタンを入力してください。返金は-1を押してください。"+ "");
			inputtext = sc.next();
			try{
				inputbutton = Integer.parseInt(inputtext);
				if(inputbutton == -1 || (inputbutton >= 1 && inputbutton <= 7 && 
					judgeproduct[inputbutton - 1] == false && canchange[inputbutton - 1] == true)) {
					
					break;
				}
			}catch(java.lang.NumberFormatException e) {
				//System.out.println("正しいボタンを入力してください");
			}
		}while(true);//条件を満たすまでループ
			
		
		return inputbutton;
	}
	
}
