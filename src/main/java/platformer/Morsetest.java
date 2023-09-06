package platformer;
public class Morsetest {
  
  public static void main(String[] args) {
    MorseDecoder decoder = new MorseDecoder(".. _. .._. ___ ._. __ ._ _ .. _._ ");
    decoder.decode();
    decoder = new MorseDecoder("__. .._ _ __. . __ ._ _._. .... _ ");
    decoder.decode();
	
  }  
}
