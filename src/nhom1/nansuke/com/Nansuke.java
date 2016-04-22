package nhom1.nansuke.com;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import nhom1.nansuke.com.Cell;

public class Nansuke {
	private Matrix mMatrix;
	private String[] mListNumber;
	private  ArrayList<Integer> category=new ArrayList<Integer>();
	private HashMap<Integer, ArrayList<int []>> listTypeNumber;
	private HashMap<Integer, ArrayList<Cell[]>> listTypeCell;
	private int soBien;
	private int soMenhDe;
	public Nansuke(Matrix mMatrix,String[] mListNumner){
		this.mMatrix=mMatrix;
		this.mListNumber=mListNumner;
		listTypeNumber=getTypeNumber();
		listTypeCell=getTypeCell();
	}
	private HashMap<Integer, ArrayList<int []>> getTypeNumber(){
		HashMap<Integer, ArrayList<int []>> mHashMap=new HashMap<Integer,ArrayList<int []>>();
		for(int i=0;i<mListNumber.length;i++){
			String s=mListNumber[i];
			Integer length=s.length();
			int arr[]=new int[length];
			for(int j=0;j<length;j++){
				int num=Integer.valueOf(s.substring(j, j+1));
				arr[j]=num;
			}
			if(mHashMap.containsKey(length)){
				ArrayList<int[]> mList=mHashMap.get(length);
				mList.add(arr);
			}else{
				ArrayList<int []> mList=new ArrayList<int[]>();
				mList.add(arr);
				mHashMap.put(length, mList);
				category.add(length);
				
			}
			
		}
		Collections.sort(category);
		
		return mHashMap;
	}
	private HashMap<Integer, ArrayList<Cell[]>>   getTypeCell(){
		
		HashMap<Integer, ArrayList<Cell[]>> mHashMap=new HashMap<Integer, ArrayList<Cell[]>>();
		for(int i=0;i<category.size();i++){
			mHashMap.put(category.get(i), getListOfType(category.get(i)));
		}
		//printTypeCell();
		
		return mHashMap;
		
	}
	private ArrayList<Cell[]> getListOfType(int type){
		ArrayList<Cell[]> mList=new ArrayList<Cell[]>();
		for(int i=0;i<mMatrix.getRow();i++){
			int count=0;
			for(int j=0;j<mMatrix.getColumn();j++){
				if(count+1==type&&mMatrix.getColumn()-1==j&&mMatrix.get(i, j)==1){
					Cell [] arrCelliable=new Cell[type];
					int digit=0;
					int flag=type;
					int k=j;
					while(flag!=0){
						Cell mVariable=new Cell(i, k);
						arrCelliable[digit]=mVariable;
						digit++;
						k--;
						flag--;
					}
					mList.add(sortArrayCell(arrCelliable));
				}else{
					if((mMatrix.get(i, j)==0&&count==type)){
						count=0;
						
						Cell [] arrCelliable=new Cell[type];
						int digit=0;
						int flag=type;
						int k=j-1;
						while(flag!=0){
							Cell mVariable=new Cell(i, k);
							arrCelliable[digit]=mVariable;
							digit++;
							k--;
							flag--;
						}
						mList.add(sortArrayCell(arrCelliable));
					
					}else{
						if(mMatrix.get(i, j)==0){
							count=0;
						}else{
							count++;
						}
					}
				}
			}
		}
		for(int i=0;i<mMatrix.getColumn();i++){
			int count=0;
			for(int j=0;j<mMatrix.getRow();j++){
				if(count+1==type&&mMatrix.getRow()-1==j&&mMatrix.get(j, i)==1){
					Cell [] arrCelliable=new Cell[type];
					int digit=0;
					int flag=type;
					int k=j;
					while(flag!=0){
						Cell mVariable=new Cell(k, i);
						arrCelliable[digit]=mVariable;
						digit++;
						k--;
						flag--;
						System.out.println("gia tri cua k="+k+" "+i);
					}
					mList.add(sortArrayCell(arrCelliable));
				}else{
					if((mMatrix.get(j, i)==0&&count==type)){
						count=0;
						
						Cell [] arrCelliable=new Cell[type];
						int digit=0;
						int flag=type;
						int k=j-1;
						while(flag!=0){
							Cell mVariable=new Cell(k, i);
							arrCelliable[digit]=mVariable;
							digit++;
							k--;
							flag--;
							System.out.println("gia tri theo doc cua k="+k+" "+i);
						}
						mList.add(sortArrayCell(arrCelliable));
			
					}else{
						if(mMatrix.get(j, i)==0){
							count=0;
						}else{
							count++;
						}
					}
				}
				
			}
		}
		
		return mList;
	}
	public Matrix getMatrix(){
		return this.mMatrix;
	}
	public String[] getListNumber(){
		return this.mListNumber;
	}
	public ArrayList<Integer> getCategory(){
		return category;
	}
	public HashMap<Integer, ArrayList<int []>> getListTypeNumber(){
		return this.listTypeNumber;
	}
	public void printTypeCell(){
		for(int i=0;i<category.size();i++){
			ArrayList<Cell[]> listVariable=listTypeCell.get(category.get(i));
			System.out.println("so o loai "+category.get(i));
			for(int j=0;j<listVariable.size();j++){
				Cell[] mVariables=listVariable.get(j);
				String s="";
				for(int k=0;k<mVariables.length;k++){
					s=s+mVariables[k].toString();
				}
				System.out.println(s);
			}
		}
	}
	@SuppressWarnings("unused")
	public void makeCNF(){
		soBien=0;
		soMenhDe=0;
		String stringCNF="";
		for(int i=0;i<category.size();i++){
			
			int typeNumber=category.get(i);
			System.out.println("category: "+typeNumber);
			ArrayList<int[]> listNumber=listTypeNumber.get(typeNumber);
			ArrayList<Cell[]> listCell=listTypeCell.get(typeNumber);
			
			for(int j=0;j<listCell.size();j++){
				Cell[] arrCell=listCell.get(j);
				int[] arrNumber=listNumber.get(j);
				//duyet moi vi tri nhan gia tri tu 1->9
				for(int l=0;l<arrCell.length;l++){
					Cell cell=arrCell[l];
					
					for(int k=1;k<10;k++){
						//stringCNF=stringCNF+""+encode(arrCell[l],k)+" ";
						soBien++;
					}
				//	stringCNF=stringCNF+"0"+"\n";
					
				}
				
			}
			ArrayList<Integer> valuesPossible=new ArrayList<Integer>();
			for(int j=1;j<10;j++){
				boolean flag1=false;
				for(int k=0;k<listNumber.size();k++){
					boolean flag2=false;
					int[] arrNum=listNumber.get(k);
					for(int l=0;l<arrNum.length;l++){
						if(j==arrNum[l]){
							flag1=true;
							flag2=true;
							break;
						}
					}
					if(flag2){
						break;
					}
				}
				if(flag1){
					for(int k2=0;k2<valuesPossible.size();k2++){
						if(j==valuesPossible.get(k2)){
							flag1=false;
							break;
						}
					}
					if(flag1){
						valuesPossible.add(j);
					}
				}
			}
			// gia tri ma 1 o co the nhan hoac khong 
			for(int j=0;j<listCell.size();j++){
				Cell[] arrCell=listCell.get(j);
				for(int k=0;k<arrCell.length;k++){
					for(int l=0;l<valuesPossible.size();l++){
						stringCNF=stringCNF+encode(arrCell[k], valuesPossible.get(l))+" ";
						
					}
					stringCNF=stringCNF+"0"+"\n";
					// moi o chi nhan 1 gia tri
					for(int l=0;l<valuesPossible.size();l++){
						
						for(int n=l+1;n<valuesPossible.size();n++){
							stringCNF=stringCNF+"-"+encode(arrCell[k], valuesPossible.get(l))+" -"+encode(arrCell[k], valuesPossible.get(n))+" 0"+"\n";
						}
					}
					for(int l=1;l<10;l++){
						boolean flag=true;
						for(int n=0;n<valuesPossible.size();n++){
							if(l==valuesPossible.get(n)){
								flag=false;
							}
						}
						if(flag){
							stringCNF=stringCNF+"-"+encode(arrCell[k], l)+" 0"+"\n";
						}
					}
					
					
				}
			}
			//keo theo 
			ArrayList<int []> arrayOverlapNum=new ArrayList<int[]>();
			
			ArrayList<int []> arrayNoOverlapNum=new ArrayList<int[]>();
			for(int j=0;j<listNumber.size();j++){
				int [] arrNum=listNumber.get(j);
				boolean flag=true;
				for(int k1=j-1;k1>=0;k1--){
					if(arrNum[0]==listNumber.get(k1)[0]){
						flag=false;
					}
				}
				for(int k2=j+1;k2<listNumber.size();k2++){
					if(arrNum[0]==listNumber.get(k2)[0]){
						flag=false;
					}
				}
				
				if(!flag){
					arrayOverlapNum.add(arrNum);
				}else{
					arrayNoOverlapNum.add(arrNum);
				}
			}
			/// no overlap
			for(int j=0;j<listCell.size();j++){
				Cell[] arrCell=listCell.get(j);
				for(int k=0;k<arrayNoOverlapNum.size();k++){
					int [] arrNum=arrayNoOverlapNum.get(k);
					for(int l=1;l<arrNum.length;l++){
						stringCNF=stringCNF+"-"+encode(arrCell[0], arrNum[0])+" "+encode(arrCell[l],arrNum[l])+" 0"+'\n';
					}
				
				}
				
				for(int k=0;k<arrCell.length;k++){
					for(int l=0;l<listNumber.size();l++){
						int arrNum[]=listNumber.get(l);
						stringCNF=stringCNF+encode(arrCell[k], arrNum[k])+" ";
					}
					stringCNF=stringCNF+"0"+"\n";
				}
			}
			//////overlap
			
			ArrayList<Integer> arrValuePossible=new ArrayList<Integer>();
			for(int j=0;j<arrayOverlapNum.size();j++){
				int arrNum[]=arrayOverlapNum.get(j);
				int v=arrNum[0];
				boolean flag=true;
				for(int k=0;k<arrValuePossible.size();k++){
					int num=arrValuePossible.get(k);
					if(v==num){
						flag=false;
						break;
					}
				}
				if(flag){
					arrValuePossible.add(v);
				}
			}
			for(int j=0;j<listCell.size();j++){
				Cell[] arrCell=listCell.get(j);
				for(int k=0;k<arrValuePossible.size();k++){
					
					for(int n=1;n<arrCell.length;n++){
						stringCNF=stringCNF+"-"+encode(arrCell[0], arrValuePossible.get(k))+" ";
						ArrayList<Integer> arrValuePossible2=new ArrayList<Integer>();
						for(int l=0;l<arrayOverlapNum.size();l++){
							int valueFirst=arrayOverlapNum.get(l)[0];
							int v=arrayOverlapNum.get(l)[n];
							boolean flag=true;
							for (int m = 0; m < arrValuePossible2.size(); m++) {
								int v1 = arrValuePossible2.get(m);
								if(v==v1){
									flag = false;
									break;
								}
							}
							if(valueFirst!=arrValuePossible.get(k))
								flag = false;
							if(flag)
								arrValuePossible2.add(v);
						}
						for (int l = 0; l < arrValuePossible2.size(); l++) {
							stringCNF=stringCNF+encode(arrCell[n], arrValuePossible2.get(l))+" ";
						}
						stringCNF=stringCNF+"0"+"\n";
					
					}
				}
			}
			/*for(int j=0;j<listCell.size();j++){
				Cell arrCell[]=listCell.get(j);
				for(int k=0;k<listNumber.size();k++){
					int arrNum[]=listNumber.get(k);
					for(int l=0;l<arrNum.length-1;l++){
						stringCNF=stringCNF+"-"+encode(arrCell[l], arrNum[l])+" ";
					}
					stringCNF=stringCNF+encode(arrCell[arrNum.length-1],arrNum[arrNum.length-1])+" 0"+"\n";
				}
			}*/
			
			
			//loai tru .
			for(int j=0;j<listNumber.size();j++){
				int arrNum[]=listNumber.get(j);
				for(int k=0;k<listCell.size();k++){
					Cell arrCell1[]=listCell.get(k);
					for(int l=k+1;l<listCell.size();l++){
						Cell arrCell2[]=listCell.get(l);
						for(int m=0;m<arrCell1.length;m++){
							stringCNF=stringCNF+"-"+encode(arrCell1[m],arrNum[m])+" ";
						}
						for(int m=0;m<arrCell2.length;m++){
							stringCNF=stringCNF+"-"+encode(arrCell2[m], arrNum[m])+" ";
						}
						stringCNF=stringCNF+"0"+"\n";
					}
				}
			}
						
		}
		//dem so menh de va bien max.
		int clauseNum=0;
		int index = 0;
		while( index < stringCNF.length()) 
		{
			if(stringCNF.charAt(index) == '\n') clauseNum ++;
			index ++;
		}
		
		int x =mMatrix.getRow()-1;
		int y = mMatrix.getColumn()-1;
		boolean flag=false;
		for (;x >=0; x--) {
			for (; y >=0; y--) {
				if(mMatrix.get(x, y)!=0){
					flag=true;
					break;
				}
			}
			if(flag){
				break;
			}
			
		}
		soMenhDe=clauseNum;
		stringCNF = "p cnf " +encode(new Cell(x, y), 9)+ " " + clauseNum + "\n" + stringCNF;
		//System.out.println(stringCNF);
		FileUtil.clearFile();
		FileUtil.writerFile(stringCNF);
		System.out.println("xong file cnf.");
		printTypeCell();
	}
	public String encode(Cell var, int number){
		int sizecol=mMatrix.getColumn();
		String s="";
		int i=var.getRow()*sizecol+var.getCol()+1;
		s=i+""+number;
			return s;
		
	}
	public Cell decode(String s){
		int sizeCol=mMatrix.getColumn();
		Cell cell;
		if(s.contains("-")){
	
			char ch=s.charAt(s.length()-1);
			int number=Integer.parseInt(String.valueOf(ch));
			String sub=s.substring(0, s.length()-1);
			int position=Math.abs(Integer.parseInt(sub))-1;
			int row =position/sizeCol;
			int col=position%sizeCol;
			cell=new Cell(row,col,number);
			cell.setTrue(false);
		}else{
		
			char ch=s.charAt(s.length()-1);
			int number=Integer.parseInt(String.valueOf(ch));
			String sub=s.substring(0, s.length()-1);
			int position=Integer.parseInt(sub)-1;
			int row =position/sizeCol;
			int col=position%sizeCol;
			cell=new Cell(row,col,number);
			cell.setTrue(true);
		}
		return cell;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return mMatrix.getRow()+ " x "+mMatrix.getColumn();
	}
	
	public Cell[] sortArrayCell(Cell[] arrCell){
		Cell[] arr=new Cell[arrCell.length];
		if(arrCell[0].getRow()==arrCell[1].getRow()){
			if(arrCell[0].getCol()>arrCell[1].getCol()){
				int length=arr.length;
				for(int i=0;i<arr.length;i++){
					arr[length-1]=arrCell[i];
					length--;
				}
			}
		}else{
			if(arrCell[0].getRow()>arrCell[1].getRow()){
				int length=arr.length;
				for(int i=0;i<arr.length;i++){
					arr[length-1]=arrCell[i];
					length--;
				}
			}
		}
		return arr;
	}
	public int getSoBien() {
		return soBien;
	}
	public void setSoBien(int soBien) {
		this.soBien = soBien;
	}
	public int getSoMenhDe() {
		return soMenhDe;
	}
	public void setSoMenhDe(int soMenhDe) {
		this.soMenhDe = soMenhDe;
	}
}
