class Nodo { 

 private char valor; 
 private Nodo izq, derecha; 

 Nodo(char valor) { 
     this.valor = valor; 
     this.izq = this.derecha = null; 
 }
 public char getValor() {
	 return valor;
 }
 public Nodo getIzq() {
	 return izq;
 }
 public Nodo getDer() {
	 return derecha;
 }
 public void setIq(Nodo izq){
	 this.izq=izq;
 }
 public void setDer(Nodo derecha){
	 this.derecha=derecha;
 } 

}