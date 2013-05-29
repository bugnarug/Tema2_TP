import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

//Realizeaza reflectionul asupra fiecarei clase
public class Reflection extends JFrame implements ActionListener
{

	private static final long serialVersionUID = 1L;
	private JTextArea log = new JTextArea();
	private JScrollPane jsp=new JScrollPane(log);
	private JPanel pan=new JPanel();
	private JButton client=new JButton("Client");
	private JButton coada=new JButton("Coada");
	private JButton repartizator=new JButton("Repartizator");
	private JButton ism=new JButton("ISM");
	private JButton clock=new JButton("Clock");
	
	public Reflection()
	{
		pan.setBounds(0, 0, 750, 800);
		pan.setLayout(null);
		log.setEditable(false);
		jsp.setBounds(116, 0, 430, 767);
	 	pan.add(jsp);
		jsp.setVisible(true);
		client.setBounds(0,50,110,20);
	 	client.addActionListener(this);
	 	pan.add(client);
	 	coada.setBounds(0,80,110,20);
	 	coada.addActionListener(this);
	 	pan.add(coada);
	 	repartizator.setBounds(0,110,110,20);
	 	repartizator.addActionListener(this);
	 	pan.add(repartizator);
	 	ism.setBounds(0,140,110,20);
	 	ism.addActionListener(this);
	 	pan.add(ism);
	 	clock.setBounds(0,170,110,20);
	 	clock.addActionListener(this);
	 	pan.add(clock);
		this.add(pan);
	 	this.setVisible(true);
	 	this.setSize(550, 800);
	 	this.setTitle("Reflection");
	 	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	/*este definita in interfata ActionListener si detecteaza daca a fost apasat vreunul dintre butoane, 
	in caz afirmativ se apealeaza metoda din clasa Operatii corespunzatoare butonului apasat*/
	public void actionPerformed(ActionEvent e) 
	{
		Object ev;
		ev=e.getSource();
		if (ev==client) this.client();
		else if (ev==coada) this.coada();
		else if (ev==repartizator) this.repartizator();
		else if (ev==ism) this.interfataSimulatorCozi();
		else if (ev==clock) this.clock();
	}
	
	//afiseaza in cadrul logului Reflectionul clasei Client
	public void client() 
	{	
		Class<?> client= null;
		try {
			client = Class.forName("Client");
			} 
		catch (ClassNotFoundException e1) 
			{
			e1.printStackTrace();
			};
		String clasaNume=client.getName();
		Constructor<?> c[]=client.getConstructors();
		Method[] m=client.getMethods();
		Field[] f = client.getFields();
		log.replaceRange("",0,log.getSelectionEnd());
		log.append("Numele clasei: "+clasaNume+"\n");
		log.append("\nConstuctor:\n");
		for(int i=0;i<c.length;i++)
			log.append(String.valueOf(c[i]+"\n"));
		log.append("\nMetode:\n");
		for(int i=0;i<m.length;i++)
			log.append(String.valueOf(m[i])+"\n");
		log.append("\nAtributele:\n");
		for(int i=0;i<f.length;i++)
			log.append(String.valueOf(f[i])+"\n");
	}
	//afiseaza in cadrul logului Reflectionul clasei Coada.
	public void coada() 
	{
		Class<?> coada = null;
		try {
			coada = Class.forName("Coada");
			} 
		catch (ClassNotFoundException e)
		{
		e.printStackTrace();
		}
		String n=coada.getName();
		Constructor<?> c[]=coada.getConstructors();
		Method m[]=coada.getMethods();
		Field[] f = coada.getFields();	
		log.replaceRange("",0,log.getSelectionEnd());
		log.append("Numele clasei: "+n+"\n");
		log.append("\nConstuctor:\n");
		for(int i=0;i<c.length;i++)
			log.append(String.valueOf(c[i])+"\n");
		log.append("\nMetode:\n");
		for(int i=0;i<m.length;i++)
			log.append(String.valueOf(m[i])+"\n");
		log.append("\nAtribute:\n");
		for(int i=0;i<f.length;i++)
			log.append(String.valueOf(f[i])+"\n");
	}
	//afiseaza in cadrul logului Reflectionul clasei Clock.
	public void repartizator() 
	{
		Class<?> repartizator = null;
		try {
			repartizator = Class.forName("Repartizator");
			} 
		catch (ClassNotFoundException e) 
		{
		e.printStackTrace();
		}
		String n=repartizator.getName();
		Constructor<?>[] c=repartizator.getConstructors();
		Method[] m=repartizator.getMethods();
		Field[] f = repartizator.getFields();	
		log.replaceRange("",0,log.getSelectionEnd());
		log.append("Numele clasei: " +n+"\n");
		log.append("\nConstuctor:\n");
		for(int i=0;i<c.length;i++)
			log.append(String.valueOf(c[i])+"\n");
		log.append("\nMetode:\n");
		for(int i=0;i<m.length;i++)
			log.append(String.valueOf(m[i])+"\n");	
		log.append("\nAtribute:\n");
		for(int i=0;i<f.length;i++)
			log.append(String.valueOf(f[i])+"\n");
	}
	//afiseaza in cadrul logului Reflectionul clasei Reparizator.
	public void clock() 
	{	
		Class<?> clock= null;
		try {
			clock = Class.forName("Clock");
			} 
		catch (ClassNotFoundException e1) 
			{
			e1.printStackTrace();
			};
		String clasaNume=clock.getName();
		Constructor<?> c[]=clock.getConstructors();
		Method[] m=clock.getMethods();
		Field[] f = clock.getFields();
		log.replaceRange("",0,log.getSelectionEnd());
		log.append("Numele clasei: "+clasaNume+"\n");
		log.append("\nConstuctor:\n");
		for(int i=0;i<c.length;i++)
			log.append(String.valueOf(c[i]+"\n"));
		log.append("\nMetode:\n");
		for(int i=0;i<m.length;i++)
			log.append(String.valueOf(m[i])+"\n");
		log.append("\nAtributele:\n");
		for(int i=0;i<f.length;i++)
			log.append(String.valueOf(f[i])+"\n");
	}
	//afiseaza in cadrul logului Reflectionul clasei interfataSimulatorCozi
	public void interfataSimulatorCozi() 
	{
		Class<?> interfataSimulatorCozi = null;
		try {
			interfataSimulatorCozi = Class.forName("InterfataSimulatorCozi");
			} 
		catch (ClassNotFoundException e)
		{
		e.printStackTrace();
		}
		String n =interfataSimulatorCozi.getName();
		Constructor<?>[] c=interfataSimulatorCozi.getConstructors();
		Method[] m=interfataSimulatorCozi.getMethods();
		Class<?>[] inter=interfataSimulatorCozi.getInterfaces();
		int sModifiers=interfataSimulatorCozi.getModifiers();
		Field[] f = interfataSimulatorCozi.getFields();
		log.replaceRange("",0,log.getSelectionEnd());
		log.append("Numele clasei: "+n+"\n");
		log.append("\nConstructor:\n");
		for(int i=0;i<c.length;i++)
			log.append(String.valueOf(c[i])+"\n");
		log.append("\nMetode\n:");
		for(int i=0;i<m.length;i++)
			log.append(String.valueOf(m[i])+"\n");
		log.append("\nInterfetele implementate:\n");
		for(int i=0;i<inter.length;i++)
			log.append(String.valueOf(inter[i])+"\n");
		log.append("\nAtribute:\n");
		for(int i=0;i<f.length;i++)
			log.append(String.valueOf(f[i])+"\n");
	}
}

