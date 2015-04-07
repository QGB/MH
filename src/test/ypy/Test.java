import qgb.T;

public class Test {
	public static void main(String[] args) {
		boolean ba;
		//T.print(ba );
		ba=false;
	
		
		if(ba==true){
			T.print("ba1:"+(ba=false));
		}else{
			T.print("ba2:"+(ba=false));
		}
		
		if(true)return;
		try {
			System.out.println(args[0]);
			System.out.println("I'm nomal");
			if(true)
				return;
		} catch (Exception ex) {
			System.out.println("I'm exception");
			if(true)
				return;
		} finally {
			System.out.println("I'm finally.");
		}
		
                System.out.println("Out of try.");
	}
}