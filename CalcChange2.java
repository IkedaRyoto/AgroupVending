package vend;

public class CalcChange {

	public static void main(String[] args) {
		//条件設定
		//投入金額
		int sum = 1000;
		//自動販売機内の各金額の枚数
		int NUM[] = {100, 1, 2, 2, 100};
		//商品金額
		int VALUE[] = {100,110,120,130,140,150,160};
		//ボタン番号
		int BUTTON = 2;
		
		hyouji(NUM);
		
		System.out.println("投入金額:"+sum+ "円");
		System.out.println("購入商品:" +VALUE[BUTTON]+ "円");
		Change(sum,NUM,VALUE,BUTTON);
		
		hyouji(NUM);
	}

	public static int[] Change(int sum, int NUM[], int VALUE[], int BUTTON) {
		//おつり総額の計算
		sum -= VALUE[BUTTON-1];
		System.out.println("おつりは"+sum+ "円");
		
		//おつりの内訳評価
		//1000円札のおつりが出せるか
		if((sum/1000) >= 1 && NUM[0] >= (sum/1000)) {
			System.out.println("1000円札:" +sum/1000+"枚");
			NUM[0] -= (sum/1000);
			sum %= 1000;
		}
		
		//500円玉のおつりが出せるか
		if((sum/500) >= 1 && NUM[1] >= (sum/500)) {
			System.out.println(" 500円玉:" +sum/500+"枚");
			NUM[1] -= (sum/500);
			sum %= 500;
		}else if((sum/500) >= 1 && NUM[1] < (sum/500) && NUM[1] != 0) {
			System.out.println(" 500円玉:" +NUM[1]+"枚");
			sum -= NUM[1]*500;
			NUM[1] -= NUM[1];
		}
				
		//100円玉のおつりが出せるか
		if((sum/100) >= 1 && NUM[2] >= (sum/100)) {
			System.out.println(" 100円玉:" +sum/100+"枚");
			NUM[2] -= (sum/100);
			sum %= 100;
		}else if((sum/100) >= 1 && NUM[2] < (sum/100) && NUM[2] != 0) {
			System.out.println(" 100円玉:" +NUM[2]+"枚");
			sum -= NUM[2]*100;
			NUM[2] -= NUM[2];
		}
		
		//50円玉のおつりが出せるか
		if((sum/50) >= 1 && NUM[3] >= (sum/50)) {
			System.out.println("  50円玉:" +sum/50+"枚");
			NUM[3] -= (sum/50);
			sum %= 50;
		}else if((sum/50) >= 1 && NUM[3] < (sum/50) && NUM[3] != 0) {
			System.out.println("  50円玉:" +NUM[3]+"枚");
			sum -= NUM[3]*50;
			NUM[3] -= NUM[3];
		}
			
		//10円玉のおつりが出せるか
		if((sum/10) >= 1 && NUM[4] >= (sum/10)) {
			System.out.println("  10円玉:" +sum/10+"枚");
			NUM[4] -= (sum/10);
			sum %= 10;
			}
	return NUM;
	}
	
	//テスト表示用
	public static void hyouji(int NUM[]) {
		int kin[] = {1000,500,100,50,10};
		System.out.printf("\n-------------------\n");
		System.out.println("自動販売機内のお金の枚数を表示します");
		for(int i = 0; i <= NUM.length-1; i++) {
			System.out.printf("%4d円:%3d枚\n", kin[i], NUM[i]);
		}
		System.out.printf("-------------------\n");
	}
}
