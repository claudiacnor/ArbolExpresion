import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;
class Arbol {
	public static void main(String args[]) {
		String ecuacion, postfija;
		Arbol et = new Arbol();
		Scanner Leer = new Scanner(System.in);
		System.out.println("Ingrese la ecuacion: ");
		ecuacion= Leer.nextLine();
		postfija=postfija(ecuacion);
		Nodo raiz = et.arbol(postfija);
		System.out.println("In orden=" + et.inorder(raiz));
		System.out.println("Pre orden=" + et.preorder(raiz));
		System.out.println("Post orden=" + et.postorder(raiz));
	}

	public static String postfija(String infija) {
		Stack<String> pilaOperadores = new Stack<String>();
		String operadores = "(+-*/)"; 
		StringTokenizer token;
		String t, o = "", posfija = "";
		token = new StringTokenizer(infija, operadores, true);
		while (token.hasMoreTokens()) {
			t = token.nextToken();
			if (t.equals("("))
				pilaOperadores.push(t);
			else {
				if (t.equals(")")) {
					while (!pilaOperadores.empty()) {
						o = pilaOperadores.pop();
						if (!o.equals("("))
							posfija += o;
					}
				} else {
					if (t.equals("+") || t.equals("-")) {
						if (pilaOperadores.contains("*") || pilaOperadores.contains("/") || pilaOperadores.contains("-")
								|| pilaOperadores.contains("+")) {
							while (!pilaOperadores.empty())
								posfija += pilaOperadores.pop();
						}
						pilaOperadores.push(t);
					} else {
						if (t.equals("*") || t.equals("/")) {
							while (!pilaOperadores.empty()
									&& (pilaOperadores.peek().equals("*") || pilaOperadores.peek().equals("/"))) {
								posfija += token.nextToken();
								posfija += pilaOperadores.pop();
							}
							pilaOperadores.push(t);
						} else
							posfija += t;

					}
				}

			}
		}
		while (!pilaOperadores.empty())
			posfija += pilaOperadores.pop();
		return posfija;
	}
	public Nodo arbol(String posfija) {
		Stack<Nodo> pilaNodos = new Stack<Nodo>();
		Nodo nodo, nodoDer, nodoIzq;

		for (int i = 0; i < posfija.length(); i++) {
			// si es operando se pone directamente en la pila
			if (!esOperador(posfija.charAt(i))) {
				nodo = new Nodo(posfija.charAt(i));
				pilaNodos.push(nodo);
			} else 
			{
				nodo = new Nodo(posfija.charAt(i));
				// Asignamos sus nodos
				nodoDer = pilaNodos.pop();
				nodoIzq = pilaNodos.pop();
				nodo.setDer(nodoDer);
				nodo.setIq(nodoIzq);
				// añadimos subarbol a la pila
				pilaNodos.push(nodo);
			}
		}

		// nodo final es la raiz
		return pilaNodos.pop();
	}

	boolean esOperador(char c) {
		if (c == '+' || c == '-' || c == '*' || c == '/')
			return true;
		return false;
	}

//Recorridos
	String inO = "";
	String postO = "";
	String preO = "";

	public String inorder(Nodo nodo) {
		if (nodo != null) {
			inorder(nodo.getIzq());
			inO += nodo.getValor();
			inorder(nodo.getDer());
		}
		return inO;
	}

	public String preorder(Nodo nodo) {
		if (nodo != null) {
			preO += nodo.getValor();
			preorder(nodo.getIzq());
			preorder(nodo.getDer());
		}
		return preO;
	}

	public String postorder(Nodo nodo) {
		if (nodo != null) {
			postorder(nodo.getIzq());
			postorder(nodo.getDer());
			postO += nodo.getValor();
		}
		return postO;
	}

}
