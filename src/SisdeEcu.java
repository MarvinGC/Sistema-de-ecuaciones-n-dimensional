

public class SisdeEcu {
	//M_1 inverza, M_2 2X2
	
	public static void main(String args[]){
		/**
		 * Quitar los arreglos de la clase Matriz para que sea mas dinamico
		 * 
		 * */
		Matriz coeficientes = new Matriz();
		Matriz termIndepend = new Matriz();	
		
		System.out.println("\tCalculador de Sistemas de Ecuaciones \n"
				+ "\tpor el metodo de la Matriz Inversa\n");
		
		/*Se pide al usuario los valores para asignarlos a las matrices*/
		System.out.println("\nMatriz de coeficientes\n");
		coeficientes.asigValConsola();
		
		System.out.println("\nMatriz de terminos Independientes\n");
		termIndepend.asignaDimValores(coeficientes.filas,1);
		///
		
		/*Se muestran las matrices ingresadas*/
		System.out.print("\n\nMatriz de coeficientes:");
		coeficientes.mostrarEnConsola();
		System.out.print("\n\nMatriz de terminos Independientes:");
		termIndepend.mostrarEnConsola();
		//
		
		//Se calcula el determinante y se muestra 
		System.out.print("\n\nDeterminante de Matriz de Coeficientes: ");
		System.out.println(coeficientes.determinate());
		
		//Se calcula la inversa de coeficientes
		Matriz inversa = new Matriz();
		inversa.calculaInvernxn(coeficientes);
		//Se muestra en consola
		System.out.print("\n\nMatriz Inversa de Coeficientes:");
		inversa.mostEnConsolaFloat();
		System.out.println();
		
		//Se calcula y se muestra en consola la solucion 
		Matriz solucion = new Matriz();
		solucion.mMultiplica(inversa,termIndepend);
		System.out.print("\n\nMatriz Solución:");
		solucion.mostEnConsolaFloat();
		System.out.println();
	}
}
