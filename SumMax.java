
public class SumMax {

	public boolean SumMaxMethod(int sum){
		
		System.out.println("投入金額合計");
		System.out.println(sum);
		// boolean型で配列を宣言
		boolean summax ;
		
		//投入金額が上限金額を越していないかの判定
		if(sum <= 1990) { //  or  sum >= 2000
			//1990円以下（2000円未満）ならfalse
			summax = false;
		}else {
			//それ以外（1990円を超えた）ならtrue
			summax = true;
		}
		
	return summax;	
	}

}
