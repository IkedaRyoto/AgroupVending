
public class SumMax {
	
	public static void main(String[] args){
		//仮条件設定
		int sum = 1000;
		
		boolean summax;
		//表示
		summax = SumMaxMethod(sum);
		
		System.out.println(summax);
	}

	public static boolean SumMaxMethod(int sum){
		
		System.out.println("投入金額合計");
		System.out.println(sum);
		// boolean型で配列を宣言
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

}
