import java.util.*;
 
//Contine toate atributele clientului care sunt necesarii simularii sistemului de cozi.

public class Client {
	private Random generator=new Random();
	private int tsosire;
	private int tservire;
	private int nr;
	private int tasteptare;
	private int ncl;
	
	public Client(int nrClienti, int timpSosireMaxim, int timpSosireMinim, int timpServireMaxim, int timpServireMinim, int tsosire )
	{	
		int g=(timpSosireMaxim-timpSosireMinim)/nrClienti;
		if (g==0) g=1;
		this.tsosire=generator.nextInt(g)+tsosire+1;
		if (timpServireMaxim-timpServireMinim==0) this.tservire=timpServireMinim;
		else this.tservire=generator.nextInt(timpServireMaxim-timpServireMinim)+timpServireMinim;
	}
	
	/**
     * Initilizeaza numarul de ordine al clientului.
     */
	public void setNrCoada(int i)
	{
		this.nr=i;
	}
	/**
     * Returneaza numarul cozii in care clientul a fost pus.
     */
	public int getNrCoada()
	{
		return nr;
	}
	/**
     * Returneaza timpul de sosire al clientului
     */
	public int getTimpSosire()
	{
		return tsosire;
	}
	
	/**
     * Returneaza timpul de servire al clientului.
     */
	public int getTimpServire()
	{
		return tservire;
	}
	/**
     * Initializeaza timpul de asteptare al clientlui.
     */
	public void setTimpAsteptare(int i) 
	{
		tasteptare=i;	
	}
	/**
     * Returneaza timpul de asteptare al clientlui.
     */
	public int getTimpAsteptare()
	{
		return tasteptare;
	}
	/**
     * Initializeaza numarul de ordine al clientului.
     */
	public void setNrClient(int i)
	{
		ncl=i;
	}
	
	/**
     * Returneaza numarul de ordine al clientului
     */public int getNrClient()
	{
		return ncl;
	}

	
}
