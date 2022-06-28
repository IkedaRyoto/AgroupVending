package purchaser;

import java.util.Scanner;

public class Purchaser {
	public static void main(String[] args) {
		int money = 0;
		int button = 0;
		/*確認用、お金とボタンの表示をする*/
		money = Input();
		button = InputButton();
		System.out.println(money);
		System.out.println(button);
		/**/
	}
	private static int Input() {//お金の投入
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
	
	private static int InputButton() {//ボタンの入力
		String inputtext = "";
		int inputbutton;
		while(true) {//条件を満たすまでループ
			Scanner sc = new Scanner(System.in);
			System.out.println("ボタンを入力してください");
			inputtext = sc.next();
			try{
				inputbutton = Integer.parseInt(inputtext);
				if(inputbutton == -1 || (inputbutton >= 1 && inputbutton <= 7)) {
					break;
				}
			}catch(java.lang.NumberFormatException e) {
				//System.out.println("正しいボタンを入力してください");
			}
		}
		
		return inputbutton;
	}
	
}