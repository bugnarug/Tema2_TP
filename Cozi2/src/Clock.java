import javax.swing.Timer;
import java.awt.event.*;

public class Clock {
    private int count;
    private Timer t;

   /**
     * Creaza un obiect de tip Timer.
     */
     public Clock(){
        t=new Timer(1000,new ClockListener());
     }
     /**
      * Seteaza ceasul de la cat sa porneasca.
      * @param numar numarul de secunde de la cat sa numere in jos;(de exemplu:
      * pentru 1 minut si 30 de secunde se va trimte ca parametru o variabila
      * cu valoare egala cu 90)
      */
    
     public void setClock(int time){
         count=time;
     }
    /** Porneste cronometrul
     * 
     */
     public void startCounter(){
         t.start();
     }  
    /**Opreste cronometrul
     * 
     */
     public void stopCounter(){
        t.stop();
    }
     /**
     * Reda numarul de minute ramase.
     * @return Reda numarul de minute.
     */
    public short getMinute(){
        return (short)(count/60);
    }
    /**
     * Reda numarul de secunde ramase.
     * @return Reda numarul de secunde.
     */
    public short getSecunde(){
        return (short)(count%60);
    }
    /**
     * Stabileste daca s-a terminat timpul.
     * @return Daca ceasul a ajuns la valoarea 0 atunci returneaza true, in zac contrar false.
     */
    public boolean timeOut(){
        if (count==0)
            return true;
        return false;
    }
     /**
     * Returneaza numarul de minute si secunde ramase sub forma unui string in formatul "Minute:Secunde".
     * @return Returneaza numarul de minute si secunde ramase sub forma unui string in formatul "Minute:Secunde".
     */
    public String getTime(){
        String timp="";
        if (getMinute()>=10)
            if (getSecunde()>=10)
                timp=getMinute()+":"+getSecunde();
            else
                timp=getMinute()+":0"+getSecunde();
        else
            if (getSecunde()>=10)
                timp="0"+getMinute()+":"+getSecunde();
            else
                timp="0"+getMinute()+":0"+getSecunde();
        return timp;
    }

    class ClockListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            count--;
            if (count==0)
               stopCounter();
        }
    }
}