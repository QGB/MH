package qgb.algorithm;

import qgb.T;
/**Flexible Pattern Matching in Strings ISBN 978-7-121-03858-7**/
public class EvolutionaryDistances {
	static int[][] yyi;
	public static void main(String[] args){	
		//char[] x="annealing".toCharArray(),y="annual".toCharArray();
		char[] x="Evolutionary".toCharArray(),y="Distances".toCharArray();
		int i=x.length,j=y.length;
		EvoDisFrame.main(i+1,j+1);
		yyi=new int[i+1][j+1];
		//T.print("i=%d,j=%d",i,j);
		//T.sleep(5000);
		T.print(disShow(x, y, i-1, j-1));
		
		//EvoDisFrame.show(yyi);
	}
	public static int disShow(char[] x,char[] y,int i,int j) {
		if(i==-1){show(i, j, j+1); return j+1;}
		if(j==-1){show(i, j, i+1); return i+1;}

		if(x[i]==y[j]){
			show(i, j, dis(x, y, i-1, j-1));
			return disShow(x, y, i-1, j-1);
		}else {
			show(i, j, 1+ T.min(dis(x, y, i-1, j-1), 
					dis(x, y, i-1, j  ),
					dis(x, y, i, j-1)));
			return 1+ T.min(disShow(x, y, i-1, j-1), 
					disShow(x, y, i-1, j  ),
					disShow(x, y, i, j-1));
		}
	}
	
	
	static int gin=0;
	public static int dis(char[] x,char[] y,int i,int j) {
		if(i==-1) return j+1;
		if(j==-1)return i+1;

		if(x[i]==y[j]){
			return dis(x, y, i-1, j-1);
		}else {
			return 1+ T.min(dis(x, y, i-1, j-1), 
							dis(x, y, i-1, j  ),
							dis(x, y, i, j-1));
		}
	}
	public static void show(int ix,int iy,int ai) {
		yyi[ix+1][iy+1]++;
		//T.print(++gin);
		EvoDisFrame.EDframe.setCoordinate(ix+1,iy+1, ai);
		//T.sleep(5);
	}
	
}
