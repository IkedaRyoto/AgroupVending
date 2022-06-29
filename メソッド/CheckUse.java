
public class CheckUse {
	public boolean checkuseMethod(int money){
		
		boolean use_check = false;
		
		//使用可能な貨幣か確認
		use_check = (money == 0 || money % 10 == 0 || money % 50 == 0 || money % 100 == 0 || money % 500 == 0 || money % 1000 == 0);
		
		if (use_check) {
			//trueを返す
			return use_check;
		} else {
			//falseを返す
			return use_check;
		}
	}
}
