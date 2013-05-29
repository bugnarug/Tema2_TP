/*Este clasa procesor al programului. Aceasta creaza obiectele de tip client, obiectele de tip coada,
 *  repartizeaza clientii in coada si scoate clientii din coada.
 */

public class Repartizator {
	private int timpSosireMaxim;
	private int timpSosireMinim;
	private int timpServireMaxim;
	private int timpServireMinim;
	private int durataSimulare;
	private Coada coada[];
	private Client client[];
	private int nrCozi;
	private InterfataSimulatorCozi i;
	private int nrClienti;
	private String a[];
	private int tsosire;
	
	
	public Repartizator (int timpSosireMaxim, int timpSosireMinim, int timpServireMaxim, int timpServireMinim
			, int nrClienti, int nrCozi, int durataSimulare)
	{
		this.timpSosireMaxim=timpSosireMaxim;
		this.timpSosireMinim=timpSosireMinim;
		this.timpServireMaxim=timpServireMaxim;
		this.timpServireMinim=timpServireMinim;
		this.nrCozi=nrCozi;
		this.nrClienti=nrClienti;
		this.durataSimulare=durataSimulare;
		Coada coada[]=setCozi();
		Client client[]=setClienti();
		a=new String[nrCozi+4];
		for (int i=0;i<nrCozi+4;i++)
		a[i]="";
	}
	
	//CREEAZA un nr de obiecte de tip coada specicat de utilizator
	public Coada[] setCozi()
	{
		this.coada=new Coada[nrCozi];
		for(int i=0;i<nrCozi;i++)
			this.coada[i]=new Coada();
	return coada;
	}
	
	//creaza un nr de obiecte de tip client specificat de utilizator
	public Client[] setClienti()
	{
		this.client=new Client[nrClienti];
		int tsosire=timpSosireMinim;
		for (int i=0;i<nrClienti;i++)
		{
			this.client[i]=new Client(nrClienti,timpSosireMaxim, timpSosireMinim, timpServireMaxim, timpServireMinim, tsosire);
			client[i].setNrClient(i+1);
			tsosire=client[i].getTimpSosire();
		}
		return client;
	}
	//repartizeaza un client unei cozi in functie de timpul de asteptare minim al cozilor
	public void alegeCoada(Client client)
	{
		int timpMin=durataSimulare;
		int index=0;
		for (int i=0;i<nrCozi;i++)
			if (timpMin>coada[i].getTimpCoada()) 
			{
				timpMin=coada[i].getTimpCoada();
				index=i;
			}
		
		client.setTimpAsteptare(timpMin+client.getTimpServire()+client.getTimpSosire());
		client.setNrCoada(index);
		coada[index].addClient(client);
	}
	/*metoda foloseste metoda public void alegeCoada(Client client) pentru a repartiza un client si 
	 * metoda public void removeClient(Client client) al clasei Coada pentru a scoate un client dintr-o coada, 
	 * in functie de timpul de simulare in care ne aflam.
	 */
	
	public String[] start(int sant,int nrc, int ncl2) 
	{	 
		 {
			 a[nrCozi+3]="";
			 a[nrCozi+2]="";
			 for (int l=0;l<nrCozi;l++)
					coada[l].setTimpCoada();
			 for (int l=0;l<nrc;l++)
				if (sant==client[l].getTimpAsteptare()) 
				{ 
					a[nrCozi+3]="Clientul "+client[l].getNrClient()+" a iesit din coada "+(client[l].getNrCoada()+1);
					coada[client[l].getNrCoada()].removeClient(client[l]);
					int k=0;
					if (client[l].getNrClient()<=10) k=3;
					else if (client[l].getNrCoada()<=100) k=4;
					else k=5;
					a[client[l].getNrCoada()]=a[client[l].getNrCoada()].substring(k);
					
					
				}
			 if (nrc<nrClienti)
			 if (sant==client[nrc].getTimpSosire())
			 { 
				 alegeCoada(client[nrc]);
				 a[nrCozi+2]="Clientul "+client[nrc].getNrClient()+" a intrat in coada "+(client[nrc].getNrCoada()+1);
				 a[client[nrc].getNrCoada()]=a[client[nrc].getNrCoada()]+" c"+nrc;
				 nrc++;
				 ncl2--;
			 }
		 }
			a[nrCozi]=String.valueOf(nrc);
			a[nrCozi+1]=String.valueOf(ncl2);
		return a;
	}
}
