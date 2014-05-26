/**
 *
 *  @author Tomkowicz Małgorzata S12281
 *
 */

/*
 * 
 */
/*
 * 
 */
package zad31;

import javax.swing.SwingUtilities;


/*
 Zadanie 1 (max. 4 p.) 
 Napisać program, w którym uruchamiane zadania pokazywane są na liście. 
 Zadania z listy możemy odpytywac o ich stan, anulować, pokazywac ich wyniki, 
 gdy są gotowe itp.
 */

public class Main {

  public static void main(String[] args) {
	  SwingUtilities.invokeLater(new MainFrame());
  }
}