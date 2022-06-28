package vend;

public class CanPurchase {

	public static void main(String[] args) {
		//仮条件設定
		//投入金額、商品名、価格、在庫数、ボタン番号
		int sum = 100;
		String[] name   = {"Cola", "Cola", "GreenTea", "Water", "Coffe", "EnergyDrink", "Yagult10000"};
		int[]    VALUE  = { 130,    130,    110,        100,     110,     120,           150};
		int[]    STOCK  = { 10,     0,     15,          10,      0,       5,             0};
		int[]    BUTTON = { 1,      2,     3,           4,       5,       6,             7};

		//購入可能商品の表示
		can(sum,name,VALUE,STOCK,BUTTON);
		
	}

	public static void can(int tounyu, String namae[], int[] nedan, int[] zaiko, int[] botan) {
		System.out.println("投入金額:" +tounyu+ "円");
		System.out.println("ボタン        商品 値段 在庫");
		//商品の数だけ繰り返し
		for(int i = 0; i <= namae.length-1; i++) {
			//商品金額が投入金額以下のものを表示
			if(tounyu >= nedan[i]) {
				//商品情報の表示
				System.out.printf("%6d%12s%5d  ",botan[i], namae[i], nedan[i]);
				//売切の表示
				if(zaiko[i] == 0)
					System.out.println("×");
				else
					System.out.println("〇");
				}
			
		}
		
	}
}