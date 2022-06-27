package vendingmachine;

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
		Scanner sc = new Scanner(System.in);
		System.out.println("お金を投入してください");
		int input = sc.nextInt();
		
		return input;
	}
	
	private static int InputButton() {//ボタンの入力
		int inputbutton;
		while(true) {//条件を満たすまでループ
			Scanner sc = new Scanner(System.in);
			System.out.println("ボタンを入力してください");
			inputbutton = sc.nextInt();
			if(inputbutton == -1 || (inputbutton >= 1 && inputbutton <= 7)) {
				break;
			}
		}
		
		return inputbutton;
	}
	
}