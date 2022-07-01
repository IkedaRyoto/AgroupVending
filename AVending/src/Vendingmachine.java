
public class Vendingmachine {

	//checkuseメソッド
	public boolean checkuseMethod(int money){
			
		boolean use_check = false;
			
		//使用可能な貨幣か確認
		use_check = (money == -1 ||  money == 0 || money == 10  || money == 50 || money == 100 || money  == 500 || money == 1000);
		if(use_check == false) {
			System.out.println("不正な金額入力です。");
		}
		return use_check;
	}
	
	//CalcNumメソッド(投入金額の枚数カウント)
	public static int[] CalcNumMethod(int num[], int money) {
		
			
			switch(money) {
			case 10:
				num[0] += 1;
				break;
				
			case 50:
				num[1] += 1;
				break;
				
			case 100:
				num[2] += 1;
				break;
				
			case 500:
				num[3] += 1;
				break;
				
			case 1000:
				num[4] += 1;
				break;
			default:
			}
		return num;
	}
	
	//productjudgeメソッド
	public boolean []  productjudgeMethod(int STOCK[]){
		
		// boolen配列を宣言
		boolean judge_product [] = new boolean[STOCK.length];
		
		// 各商品の在庫が0ならtrueを格納
		for(int i = 0; i< STOCK.length; i++) {
			if(STOCK[i] == 0) {
				judge_product[i] = true;
			}
			else {
				judge_product[i] = false;
			}
		}
		 return judge_product;
	}
	
	//SumMaxメソッド(投入合計金額が上限を超えていないかの判定)
	public static boolean SumMaxMethod(int sum){
		boolean summax ;
		
		//投入金額が上限金額を越していないかの判定
		if(sum <= 1990) { //  or  sum >= 2000
			//1990円以下ならfalse
			summax = false;
		}else {
			//それ以外（1990円を超えた）ならtrue
			summax = true;
		}
	return summax;	
	}
	
	
	
	//CanBuyメソッド
	public static void CanBuyMethod(int tounyu, String namae[], int[] nedan, int[] zaiko, int[] botan, boolean[] canchange) {
		System.out.println("投入金額:" +tounyu+ "円");
		System.out.format("%s: %9s: %5s: %5s","ボタン"," 商品" ,"値段", "購入可能可否");
		System.out.println();
		//商品の数だけ繰り返し
		for(int i = 0; i <= namae.length-1; i++) {
			//商品金額が投入金額以下のものを表示
			if(tounyu >= nedan[i] && canchange[i]) {
				//商品情報の表示
				System.out.printf("%6d:%12s:%8d:  ",botan[i], namae[i], nedan[i]);
				//売切の表示
				if(zaiko[i] == 0) {
					System.out.printf("%5s","×");
					System.out.println();
				}	
				else {
					System.out.printf("%5s","〇");
					System.out.println();
				}
			}
		}
	}
	
	
	
	//NoChangeメソッド
	public static boolean[] NoChangeMethod(int NUM[],int MONEY[]){
			
		boolean nochange[] = new boolean[NUM.length];
		//お釣りが４枚以下ならお釣り切れの判定
		for(int i = 0;i < nochange.length;i++) {
			if(NUM[i] <= 4) {
				//お釣りが４枚以下ならtrue
				nochange[i] = true;
				System.out.println(MONEY[i] +"円切れ");
				
			}else {
				//お釣りが５枚以上あるならならfalse
				nochange[i] = false;
			}	
		}
		return nochange;
	}		
	
	
	//Changeメソッド
	public static int[] ChangeMethod(int sum, int NUM[], int VALUE[], int button) {
		//おつり総額の計算
		if(button == -1) {
			
		}else {
			sum -= VALUE[button-1];
		}
		System.out.println("おつりは"+sum+ "円");
		//おつりの内訳
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
	
	//CanChangeメソッド(投入金額に対して商品購入後、お釣りが返金できるかの判定)
		public static boolean CanChangeMethod(int sum, int NUM[], int VALUE[], int button) {
			boolean canchange = false;
			int[] turi = {0, 0, 0, 0, 0};
			//おつり総額の計算
			sum -= VALUE[button];
			//1000円札のおつりが出せるか
			if((sum/1000) >= 1 && NUM[4] >= (sum/1000)) {
				turi[4] += (sum/1000);
				if(turi[4] <= NUM[4]) {
					sum %= 1000;
				}
			}
			//500円玉のおつりが出せるか
			if((sum/500) >= 1 && NUM[3] >= (sum/500)) {
				turi[3] += (sum/500);
				if(turi[3] <= NUM[3]) {
					sum %= 500;
				}
			}	
			//100円のおつりが出せるか
			if((sum/100) >= 1 && NUM[2] >= (sum/100)) {
				turi[2] += (sum/100);
				if(turi[2] <= NUM[2]) {
					sum %= 100;
				}
			}
			//50円のおつりが出せるか
			if((sum/50) >= 1 && NUM[1] >= (sum/50)) {
				turi[1] += (sum/100);
				if(turi[1] <= NUM[1]) {
					sum %= 50;
				}
			}
			//10円のおつりが出せるか
			if((sum/10) >= 1 && NUM[0] >= (sum/10)) {
				turi[0] += (sum/10);
				if(turi[0] <= NUM[0]) {
					sum %= 10;
				}			}
			if (sum == 0) {//投入金額合計が0になればtrueを入れる
				canchange = true;
			}
			return canchange;
		}
	
	//CalcStockメソッド
	public static int[]  CalcStockMethod(int button, int STOCK[]){
		STOCK[button - 1] --;
		return STOCK;
	}
	
	//SUMBOOTTLEメソッド
	public static int[]  SumBottle(int button, int STOCK[],int SUMBOTTLE[]){
		SUMBOTTLE[button - 1] ++;
		return SUMBOTTLE;
	}
	
	//Sum
	public static int[]  Sum(int button,int SUM[],int VALUE[]){
		SUM[button - 1] += VALUE[button - 1];
	return SUM;
}
	//AllSumメソッド
	public static int AllSumMethod(int allsum, int sum[]) {
		//int allsum = 0;
		for(int i = 0; i< sum.length;i++) {
			allsum = allsum + sum[i];
		}

		return allsum;
	}

}
