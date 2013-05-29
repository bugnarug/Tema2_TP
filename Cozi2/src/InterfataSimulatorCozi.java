
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;





public class InterfataSimulatorCozi extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private int startsant=0;
	private static final long serialVersionUID = 1L;
	private Timer timer = new Timer();
	private JButton start=new JButton("Start");
	private JButton reflection=new JButton("Reflection");
	private JLabel nrCozi=new JLabel ("Cozi");
	private JLabel nrClienti=new JLabel ("Clienti");
	private JLabel tsmax=new JLabel ("Timp de sosire maxim");
	private JLabel tsmin=new JLabel ("Timp de sosire minim");
	private JLabel tsermax=new JLabel ("Timp de servire maxim");
	private JLabel tsermin=new JLabel ("Timp de servire minim");
	private JLabel ds=new JLabel ("Durata simularii");
	private JTextField ncozi=new JTextField("");
	private JTextField tsmaxt=new JTextField("");
	private JTextField tsermaxt=new JTextField("");
	private JTextField tsmint=new JTextField("");
	private JTextField tsermint=new JTextField("");
	private JTextField nclienti=new JTextField("");	
	private JLabel timpramas=new JLabel ("Timp ramas:");
	private JLabel cozi[];
	private JLabel ceas=new JLabel("");
	private Clock clock=new Clock();
	private int tsmx;
	private int tsm;
	private int tsermx;
	private int tserm;
	private int ncl;
	private int nc;
	private Timer t;
	private int sim;
	private int nrc=0;
	private int ncl2=0;
	private int sant=0;
	private JTextArea log = new JTextArea();
	private JScrollPane jsp=new JScrollPane(log);
	private String a[];
	private JTextField cozit[]; //pentru fiecare coada face cate un Textfield
	private JTextField dst=new JTextField("");	
	private JPanel pan=new JPanel();
	
	public InterfataSimulatorCozi()
	{	
		
		pan.setBounds(0, 0, 1080, 800);
		pan.setLayout(null);
	 	nrCozi.setBounds(50,60,120,20);
	 	pan.add(nrCozi);
	 	nrClienti.setBounds(50,80,120,20);
	 	pan.add(nrClienti);
	 	ncozi.setBounds(180,60,30,20);
	 	pan.add(ncozi);	
	 	timpramas.setBounds(280,60,80,20);
	 	pan.add(timpramas);	
	 	ceas.setBounds(360,60,100,20);
	 	pan.add(ceas);
	 	start.setBounds(50,200,80,20);
	 	start.addActionListener(this);
	 	pan.add(start);
		reflection.setBounds(300,200,100,20);
	 	reflection.addActionListener(this);
	 	pan.add(reflection);
	 	nclienti.setBounds(180,80,30,20);
	 	pan.add(nclienti);
	 	tsmax.setBounds(50,100,130,20);
	 	pan.add(tsmax);
	 	tsmaxt.setBounds(180,100,30,20);
	 	pan.add(tsmaxt);
	 	log.setEditable(false);
		jsp.setBounds(642, 0, 430, 767);
	 	pan.add(jsp);
		jsp.setVisible(true);
	 	tsmin.setBounds(50,120,130,20);
	 	pan.add(tsmin);
	 	tsmint.setBounds(180,120,30,20);
	 	pan.add(tsmint);
	 	tsermax.setBounds(50,140,130,20);
	 	pan.add(tsermax);
	 	tsermaxt.setBounds(180,140,30,20);
	 	pan.add(tsermaxt);
	 	tsermin.setBounds(50,160,130,20);
	 	pan.add(tsermin);
	 	tsermint.setBounds(180,160,30,20);
	 	pan.add(tsermint);
	 	ds.setBounds(50,180,130,20);
	 	pan.add(ds);
	 	dst.setBounds(180,180,30,20);
	 	pan.add(dst);
	 	this.add(pan);
	 	this.setVisible(true);
	 	this.setSize(1080, 800);
	 	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/*definita in interfata ActionListener si detecteaza daca a fost apasat vreunul dintre butoane, 
	in caz afirmativ se apealeaza metoda din clasa Operatii corespunzatoare butonului apasat.*/
	
	public void actionPerformed(ActionEvent e) 
	{
		Object ev;
		ev=e.getSource();

		if (ev==start) {
						
						if (startsant==1)
						{
						for(int i=1;i<nc+1;i++)
							{
								pan.remove(cozit[i]);
								pan.remove(cozi[i-1]);
								}
						pan.remove(cozi[nc]);
						}
						startsant=1;
						//culege informatiile introduse de utilizator
						tsmx=Integer.parseInt(tsmaxt.getText());
						tsm=Integer.parseInt(tsmint.getText());
						tsermx=Integer.parseInt(tsermaxt.getText());
						tserm=Integer.parseInt(tsermint.getText());
						ncl=Integer.parseInt(nclienti.getText());
						nc=Integer.parseInt(ncozi.getText());
						sim=Integer.parseInt(dst.getText());	
						
						ncl2=ncl;
						clock.setClock(sim);
						clock.startCounter();
						paintCozi(nc);
						t=new Timer();
						t.scheduleAtFixedRate(new Task(), 1000, 1000);//din secunda in secunda apelam run din clasa Task
						
					  }
		if (ev==reflection){ 
			//construieste un obiect de tip Reflection
			Reflection r=new Reflection();}
		
	
	}
	
	
	private class Task extends TimerTask
	{
		Repartizator repartizator=new Repartizator(tsmx,tsm,tsermx,tserm,ncl,nc,sim);
		//executa metoda start din clasa repartizator pana simularea va ajunge la final 
		//( expira timpul, sau nu mai sunt clienti care sa intre sau sa iasa din cozi).
		public void run() 
		{	
			
			a=new String[Integer.parseInt(ncozi.getText())+3]; //continutul unei cozi la momentul respectiv
			if (sant<=sim)
			{
				ceas.setText(clock.getTime());
				a=repartizator.start(sant,nrc,ncl2);
				setCozi(nc,a);
				if (a[nc+2]!="")
					log.append(a[nc+2]+"\n"); //sare la urmatoarea linie in log
				if (a[nc+3]!="")
					log.append(a[nc+3]+"\n");
				nrc=Integer.parseInt(a[nc]);
				ncl2=Integer.parseInt(a[nc+1]);
				sant++;  //contor pentru timp
			}
			else 
				{
				t.cancel();  //simularea se opreste daca timpul a depasit timpul simularii
				log.setText("");
				nrc=0;
				sant=0;
				}
		}
	}
	
	/*adauga panoului un numar de labeluri si textfielduri corespunzator numarului de 
	cozi introdus de utilizator, unde vor fi afisati clientii care intra si care ies din fiecare coada in parte.*/
	public void paintCozi(int a)
	{	
		cozi=new JLabel[a+1];
		cozit = new JTextField[a+1];
		cozi[0]=new JLabel("Nr. Clienti");
		cozi[0].setBounds(50, 220, 110, 30);
		cozit[0]=new JTextField("");
		cozit[0].setBounds(110, 220, 30, 20);
		cozit[0].setEditable(false); //utlizatorul nu poate schimba continutul
		pan.add(cozit[0]); //adauga textfield pentru cozi
		pan.add(cozi[0]);
		for(int i=1;i<=a;i++)
			{
			
			cozi[i]=new JLabel("Coada "+(i));
			cozi[i].setBounds(50, 180+40*(i+1), 110, 30);
			cozit[i]=new JTextField("");
			cozit[i].setBounds(110, 180+40*(i+1), 300, 20);
			cozit[i].setEditable(false);
			pan.add(cozit[i]);
			pan.add(cozi[i]);
			}
		repaint();
	}
	
	/*afiseaza in cadrul textfieldurilor corespunzatoare cozilor clientii care se afla in cozile respective
	si afiseaza in cadrul logului ceea ce se petrece ( clientii care intra si ies din coada);*/
	public void setCozi(int c,String[] a)
	{
		cozit[0].setText(a[nc+1]);
		for(int i=1;i<c+1;i++)
		{
			cozit[i].setText(a[i-1]);
			pan.add(cozit[i]);
		}
	}
	
	//instantiaza un obiect al clasei , pentru rularea aplicatiei
	public static void main(String args[])
	{
		new InterfataSimulatorCozi();
	}
}
