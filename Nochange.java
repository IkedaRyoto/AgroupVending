
public class Nochange {

	public static void main(String[] args) {
		//仮条件設定
		//int VALUE[] = {130, 130, 110, 100, 110, 120, 150};
		int NUM [] = {30, 10, 4, 5, 30};
		
		//表示
		boolean nochange[] = NoChangeMethod(NUM);
		
		for(int i = 0;i < nochange.length;i++) {
			System.out.print(nochange[i]+"\t");
		}
		
	}
		
	public static boolean[] NoChangeMethod(int NUM[]){
		
		boolean nochange[] = new boolean[NUM.length];
		//お釣りが４枚以下ならお釣り切れの判定
		for(int i = 0;i < nochange.length;i++) {
			if(NUM[i] <= 4) {
				//お釣りが４枚以下ならtrue
				nochange[i] = true;
			}else {
				//お釣りが５枚以上あるならならfalse
				nochange[i] = false;
			}	
		}
		return nochange;
	}
}
