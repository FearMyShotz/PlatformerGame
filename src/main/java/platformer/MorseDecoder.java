package platformer;
/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 01.09.2021
 * @author 
 */

public class MorseDecoder {
  
  // Anfang Attribute
  private String eingabe;
  
  public BinaryTree<Character> morsebaum, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z, ae, oe, ue, ch;


  public void inorder(BinaryTree<Character> bt) {
    
    if (bt.getLeftTree() != null) {
      inorder(bt.getLeftTree());
    } // end of if
    if (!bt.isEmpty()) {
      System.out.print(bt.getContent() + " ");
    } // end of if
     if (bt.getRightTree() != null) {
      inorder(bt.getRightTree());
    } // end of if
  }
  
  
  // Ende Attribute
  
  public MorseDecoder(String nachricht) {
    eingabe = nachricht;
    System.out.println("Erzeuge den Morsebaum");
    // der Konstruktor setzt das Attribut node auf null, deswegen wird von mir ein content gesetzt, damit der Baum nicht leer bleibt.
    morsebaum = new BinaryTree<Character>(); morsebaum.setContent('^');
    a = new BinaryTree<Character>(); a.setContent('a');
    b = new BinaryTree<Character>(); b.setContent('b');
    c = new BinaryTree<Character>(); c.setContent('c');
    d = new BinaryTree<Character>(); d.setContent('d');
    e = new BinaryTree<Character>(); e.setContent('e');
    f = new BinaryTree<Character>(); f.setContent('f');
    g = new BinaryTree<Character>(); g.setContent('g');
    h = new BinaryTree<Character>(); h.setContent('h');
    i = new BinaryTree<Character>(); i.setContent('i');
    j = new BinaryTree<Character>(); j.setContent('j');
    k = new BinaryTree<Character>(); k.setContent('k');
    l = new BinaryTree<Character>(); l.setContent('l');
    m = new BinaryTree<Character>(); m.setContent('m');
    n = new BinaryTree<Character>(); n.setContent('n');
    o = new BinaryTree<Character>(); o.setContent('o');
    p = new BinaryTree<Character>(); p.setContent('p');
    q = new BinaryTree<Character>(); q.setContent('q');
    r = new BinaryTree<Character>(); r.setContent('r');
    s = new BinaryTree<Character>(); s.setContent('s');
    t = new BinaryTree<Character>(); t.setContent('t');
    u = new BinaryTree<Character>(); u.setContent('u');
    v = new BinaryTree<Character>(); v.setContent('v');
    w = new BinaryTree<Character>(); w.setContent('w');
    x = new BinaryTree<Character>(); x.setContent('x');
    y = new BinaryTree<Character>(); y.setContent('y');
    z = new BinaryTree<Character>(); z.setContent('z');
    z = new BinaryTree<Character>(); z.setContent('z');
    ae = new BinaryTree<Character>(); ae.setContent('�');
    oe = new BinaryTree<Character>(); oe.setContent('�');
    ue = new BinaryTree<Character>(); ue.setContent('�');
    
    
    // Ebene 1
    morsebaum.setLeftTree(e);
    morsebaum.setRightTree(t);
    // Ebene 2
    e.setLeftTree(i);
    e.setRightTree(a);
    t.setLeftTree(n);
    t.setRightTree(m);
    // Ebene 3
    i.setLeftTree(s);
    i.setRightTree(u);
    a.setLeftTree(r);
    a.setRightTree(w);
    n.setLeftTree(d);
    n.setRightTree(k);
    m.setLeftTree(g);
    m.setRightTree(o);
    // Ebene 4
    s.setLeftTree(h);
    s.setRightTree(v);
    u.setLeftTree(f);
    u.setRightTree(ue);
    r.setLeftTree(l);
    r.setRightTree(ae);
    w.setLeftTree(p);
    w.setRightTree(j);
    d.setLeftTree(b);
    d.setRightTree(x);
    k.setLeftTree(c);
    k.setRightTree(y);
    g.setLeftTree(z);
    g.setRightTree(q);
    o.setLeftTree(oe);
    // "ch" weggelassen wegen Unvertr�glichkeit mit char
    
    //Ebene 5 w�ren Zahlen und Sonderzeichen...
    
    //inorder(morsebaum);
    System.out.println();
  }
  
  // Anfang Methoden
  public String getEingabe() {
    return eingabe;
  }

  public void setEingabe(String eingabeNeu) {
    eingabe = eingabeNeu;
  }
  
  public void decode() {
    System.out.println("Dekodiere: " + eingabe);
    // TODO: String in Array aus chars umwandeln (wahrscheinlich Recherche n�tig...)
     char[] morsezeichen = eingabe.toCharArray();
       
    // der Teilbaum ist bei einem gelesenen . der linke Teilbaum, sonst der rechte
    BinaryTree<Character> teilbaum;
    // zu Beginn entspricht der Teilbaum dem vollst�ndigen Baum
    teilbaum = morsebaum;
  
    for (int i = 0; i < morsezeichen.length; i++) {
      if (morsezeichen[i] == ' ') {
        // der Morsecode f�r einen Buchstaben ist vollst�ndig 
        // TODO: Das aktuelle Zeichen des Teilbaums ausgeben
        System.out.println("Aktuelles Zeichen: " + morsezeichen[i]);

        // TODO: um wieder den ganzen Baum durchlaufen zu k�nnen, 
        //       muss teilbaum wieder auf den vollst�ndigen Morsebaum verweisen (s.o.)
        teilbaum = morsebaum;
      } else {
        if (morsezeichen[i] == '.') {
          // TODO: teilbaum verweist nun auf den linken Teil VON SICH SELBST!
          teilbaum = morsebaum.getLeftTree();
        } else {
          // TODO: teilbaum verweist nun auf den rechten Teil VON SICH SELBST!
          teilbaum = morsebaum.getRightTree();
        } // end of if-else  
      } // end of if-else
      
    }
  }
  
  /**
  * TODO implementieren und testen :)
  */
  public static void besuchePreorder(BinaryTree<Character> bt) {
    bearbeite(bt.getContent());

    if (bt.getLeftTree() != null) {
      besuchePreorder(bt.getLeftTree());
    } else if (bt.getRightTree() != null) {
      besuchePreorder(bt.getRightTree());
    }
  }
  
  private static void bearbeite(Object content) { System.out.println(content.toString()); }

  public static void main(String[] args) {
    MorseDecoder md = new MorseDecoder("... --- ...");
    md.decode();

    besuchePreorder(morsebaum);

  } // end of main

  // Ende Methoden
} // end of MorseDecoder

