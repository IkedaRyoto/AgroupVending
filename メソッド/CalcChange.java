package vend;

public class CalcChange {

	public static void main(String[] args) {
		//条件設定
		//投入金額
		int sum = 1200;
		//自動販売機内の各金額の枚数
		int NUM[] = {110, 0, 0, 100, 100};
		//商品金額
		int VALUE[] = {100,110,120,130,140,150,160};
		//ボタン番号
		int BUTTON = 1;
		
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
		if((sum/1000) >= 1 && NUM[4] >= (sum/1000)) {
			System.out.println("1000円札:" +sum/1000+"枚");
			NUM[4] -= (sum/1000);
			sum %= 1000;
		}
		
		//500円玉のおつりが出せるか
		if((sum/500) >= 1 && NUM[3] >= (sum/500)) {
			System.out.println(" 500円玉:" +sum/500+"枚");
			NUM[3] -= (sum/500);
			sum %= 500;
		}
				
		//100円のおつりが出せるか
		if((sum/100) >= 1 && NUM[2] >= (sum/100)) {
			System.out.println(" 100円玉:" +sum/100+"枚");
			NUM[2] -= (sum/100);
			sum %= 100;
		}
		
		//50円のおつりが出せるか
		if((sum/50) >= 1 && NUM[1] >= (sum/50)) {
			System.out.println("  50円玉:" +sum/50+"枚");
			NUM[1] -= (sum/50);
			sum %= 50;
			}
		//10円のおつりが出せるか
				if((sum/10) >= 1 && NUM[0] >= (sum/10)) {
					System.out.println("  10円玉:" +sum/10+"枚");
					NUM[0] -= (sum/10);
					sum %= 10;
				}
	return NUM;
	}
	
	//テスト表示用
	public static void hyouji(int NUM[]) {
		int kin[] = {10,50,100,500,1000};
		System.out.printf("\n-------------------\n");
		System.out.println("自動販売機内のお金の枚数を表示します");
		for(int i = 0; i <= NUM.length-1; i++) {
			System.out.printf("%4d円:%3d枚\n", kin[i], NUM[i]);
		}
		System.out.printf("-------------------\n");
	}
}
