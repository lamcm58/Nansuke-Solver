package nhom1.nansuke.com;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.sat4j.minisat.SolverFactory;
import org.sat4j.reader.DimacsReader;
import org.sat4j.reader.Reader;
import org.sat4j.specs.IProblem;
import org.sat4j.specs.ISolver;

import nhom1.nansuke.com.Cell;

public class NansukeSolver {
	private String listLabelNansuke[];
	private Nansuke mNansuke;
	private JFrame mFrame;
	private JComboBox mComboBox;
	private JPanel panelMenu;
	private JButton btnSolver;
	private JLabel labelMatrix[][];
	private JPanel panelMatrix;
	private JPanel panelListNum;
	private JTextArea labelListNumber;
	private JLabel labelTime;
	private JLabel labelVarNum;
	private JLabel labelClauseNum;
	private ArrayList<Cell> listCell=new ArrayList<Cell>();
	public NansukeSolver(){
		init();
	}
	private void init(){
		int size=NansukeUtil.arrNansukeQuestions.length;
		listLabelNansuke=new String[size];
		for(int i=0;i<NansukeUtil.arrNansukeQuestions.length;i++){
			listLabelNansuke[i]=NansukeUtil.arrNansukeQuestions[i].toString();
		}
		
		mNansuke=NansukeUtil.arrNansukeQuestions[0];
		mFrame=new JFrame();
		mFrame.setSize(1000,800 );
		mFrame.setLayout(null);
		
		panelMenu=new JPanel();
		panelMenu.setLayout(null);
		panelMenu.setSize(299,800);
		panelMenu.setBackground(Color.LIGHT_GRAY);
		panelMenu.setLocation(701, 0);
		
		JPanel panelLine=new JPanel();
		panelLine.setBackground(Color.GRAY);
		panelLine.setSize(1, 800);
		panelLine.setLocation(700, 0);
		mFrame.add(panelLine);
		
		JLabel labelSize=new JLabel("Size :");
		labelSize.setSize(50, 50);
		labelSize.setLocation(50, 50);
		panelMenu.add(labelSize);
		
		mComboBox=new JComboBox(listLabelNansuke);
		mComboBox.setLocation(150, 50);
		mComboBox.setSize(100, 40);
		mComboBox.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox cb=(JComboBox)e.getSource();
				String itemSelect=(String) cb.getSelectedItem();
				int index=0;
				for(int i=0;i<listLabelNansuke.length;i++){
					if(itemSelect.compareTo(listLabelNansuke[i])==0){
						index=i;
					}
				}
				mNansuke=NansukeUtil.arrNansukeQuestions[index];
				initMatrix();
				listCell.clear();
			}
		});
		panelMenu.add(mComboBox);
		
		btnSolver=new JButton("Solver");
		btnSolver.setSize(200, 50);
		btnSolver.setLocation(50, 200);
		btnSolver.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Solver();
				
				System.out.println("clicked");
				
			}
		});
		panelMenu.add(btnSolver);
		
		
		JLabel labelTitleTime=new JLabel("Time :");
		labelTitleTime.setSize(70, 50);
		labelTitleTime.setLocation(50, 300);
		panelMenu.add(labelTitleTime);
		
		labelTime=new JLabel();
		labelTime.setSize(100, 50);
		labelTime.setLocation(130, 300);
		panelMenu.add(labelTime);
		////////
		JLabel labelTitleVar=new JLabel("Số biến :");
		labelTitleVar.setSize(70, 50);
		labelTitleVar.setLocation(50, 380);
		panelMenu.add(labelTitleVar);
		
		labelVarNum=new JLabel();
		labelVarNum.setSize(100, 50);
		labelVarNum.setLocation(130, 380);
		panelMenu.add(labelVarNum);
		//////
		JLabel labelTitleClause=new JLabel("Số MD :");
		labelTitleClause.setSize(70, 50);
		labelTitleClause.setLocation(50, 450);
		panelMenu.add(labelTitleClause);
		
		labelClauseNum=new JLabel();
		labelClauseNum.setSize(100, 50);
		labelClauseNum.setLocation(130, 450);
		panelMenu.add(labelClauseNum);
		
		panelMatrix=new JPanel();
		panelMatrix.setSize(600,500);
		panelMatrix.setLocation(50, 20);
		panelMatrix.setBackground(Color.black);
		mFrame.add(panelMatrix);
		
		panelListNum=new JPanel();
		panelListNum.setSize(700, 250);
		panelListNum.setLocation(0, 530);
		panelListNum.setBackground(Color.white);
		panelListNum.setLayout(null);
		labelListNumber=new JTextArea();
		labelListNumber.setSize(700, 250);
		labelListNumber.setEditable(false);
		labelListNumber.setLineWrap(true);
		labelListNumber.setWrapStyleWord(true);
		labelListNumber.setAlignmentX(10);
		labelListNumber.setLocation(0, 0);
		
		panelListNum.add(labelListNumber);
		
		mFrame.add(panelListNum);
		
		mFrame.add(panelMenu);
		
		mFrame.setVisible(true);
		initMatrix();
		
	}
	
	private void initMatrix(){
		Matrix mMatrix=mNansuke.getMatrix();
		int row=mMatrix.getRow();
		int col=mMatrix.getColumn();
		panelMatrix.removeAll();
		panelMatrix.setLayout(new GridLayout(row,col,1,1));
		labelMatrix=new JLabel[row][col];
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				if(mMatrix.get(i, j)==0){
					JLabel label=new JLabel();
					label.setHorizontalAlignment(JLabel.CENTER);
					label.setForeground(Color.blue);
					label.setBackground(Color.black);
					label.setOpaque(true); 
					labelMatrix[i][j]=label;
					panelMatrix.add(label);
				}else{
					JLabel label=new JLabel();
					label.setHorizontalAlignment(JLabel.CENTER);
					label.setForeground(Color.blue);
					label.setBackground(Color.white);
					label.setOpaque(true); 
					labelMatrix[i][j]=label;
					panelMatrix.add(label);
				}
			}
		}
	
		String s="\n";
		ArrayList<Integer> category=mNansuke.getCategory();
		HashMap<Integer, ArrayList<int[]>> mMap=mNansuke.getListTypeNumber();
		for(int i=0;i<category.size();i++){
			int itemCategory=category.get(i);
			ArrayList<int [] > listNum=mMap.get(itemCategory);
			for(int j=0;j<listNum.size();j++){
				int [] arr=listNum.get(j);
				String s1="";
				for(int k=0;k<arr.length;k++){
					s1=s1+arr[k];
				}
				s=s+s1+"    ";
			}
			s="  "+s+"\n";
		}
		
		
		labelListNumber.setText(s);
		
		
	}
	
	@SuppressWarnings("deprecation")
	public void Solver()
	{
		mNansuke.makeCNF();
		//Calendar cal=Calendar.getInstance();
		long timeStart=System.currentTimeMillis();
		System.out.println("time start: "+timeStart);
		long timeEnd=0;
        ISolver solver = SolverFactory.newDefault();
        solver.setTimeout(3600); 
        Reader reader = new DimacsReader(solver);
        try {
            IProblem problem = reader.parseInstance("src\\SatSolver\\sat.cnf");
         
          
            if (problem.isSatisfiable()) {
                System.out.println("Satisfiable !");
                //System.out.println(reader.decode(problem.model()));
                String listNum=reader.decode(problem.model());
                System.out.println(listNum);
                String s[]=listNum.split(" ");
                System.out.println(s[s.length-1]);
                for(int i=0;i<s.length-1;i++){
                	Cell cell=mNansuke.decode(s[i]);
                	listCell.add(cell);
                }
                timeEnd=System.currentTimeMillis();
                System.out.println("time: "+timeEnd);
                long time=timeEnd-timeStart;
                labelTime.setText(""+time+" ms");
                labelVarNum.setText(mNansuke.getSoBien()+"");
                labelClauseNum.setText(mNansuke.getSoMenhDe()+"");
                fillCell();
            } else {
                System.out.println("Unsatisfiable !");
            }
        } catch (Exception e) {
            System.out.println("Unsatisfiable");
            e.printStackTrace();
        }
    }
	private void fillCell(){
		for(int i=0;i<listCell.size();i++){
			Cell cell=listCell.get(i);
			if(cell.isTrue()){
				int row=cell.getRow();
				int col=cell.getCol();
				int index=cell.getIndex();
				labelMatrix[row][col].setText(""+index);
			}
		}
	}
}
