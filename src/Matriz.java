
import java.util.Scanner;

public class Matriz {
	
	int[][] Matriz;
	float[][] MatrizF;
	int[] MatrizU;
	float[] MatrizFU;
	float detM;
	public int columnas, filas;
	private Scanner SC;
	private int i,j,k;
	
	Matriz(){
		SC = new Scanner(System.in);
	}
	Matriz(int filas,int columnas){
		Matriz = new int[filas][columnas];
		this.filas = filas;
		this.columnas = columnas;
	}
	public void asignaFloat(int filas,int columnas){
		MatrizF = new float[filas][columnas];
		this.filas=filas;
		this.columnas=columnas;
	}
	public void asignaValores(int[][] Matriz){	
		this.Matriz = Matriz;
	}
	//asigna dimension desde el programa
	//y valores desde consola
	public void asignaDimValores(int filas,int columnas){
		Matriz = new int[filas][columnas];
		this.filas=filas;
		this.columnas=columnas;
		
		System.out.println("Ingrese los valores de la matriz:\n");
		
		for(i=0; i<filas; i++){
			for(j=0; j<columnas; j++){
				System.out.print("Ingrese la casilla ("+(i+1)+","+(j+1)+"): ");
				Matriz[i][j]=SC.nextInt();
			}
		}
	}
	//asigna dimension y valores desde consola
	public void asigValConsola(){
		System.out.println("Ingrese la dimension de la matriz");
		System.out.println("Filas: ");
		filas = SC.nextInt();
		System.out.println("Columnas: ");
		columnas = SC.nextInt();
		Matriz = new int[filas][columnas];
		
		System.out.println("Ingrese los valores de la matriz:\n");
		
		for(i = 0; i<filas ;i++){
			for(j = 0; j<columnas; j++){
				System.out.print("Ingrese la casilla ("+(i+1)+","+(j+1)+") ");
				Matriz[i][j]=SC.nextInt();
			}
		}	
	}
	/*metodo temporal*/
	public void asigValUnidim(int filas,int columnas){
		MatrizU = new int[filas*columnas];
		this.filas=filas;
		this.columnas=columnas;
		
		k=0;
		for(i=0; i<filas; i++){
			for(j=0; j<columnas; j++){
				System.out.print("Ingrese la casilla ("+(i+1)+","+(j+1)+"): ");
				MatrizU[k]=SC.nextInt();
				k++;
			}
		}
	}
	public void mostrarEnConsola() {
		for(i=0; i<filas; i++) {
			System.out.println();
			for(j=0; j<columnas; j++) {
				System.out.print(Matriz[i][j]+"\t");
			}
		}
		
	}
	public void mostEnConsolaFloat() {
		for(i=0; i<filas; i++) {
			System.out.println();
			for(j=0; j<columnas; j++) {
				System.out.print(MatrizF[i][j]+"\t");
			}
		}
	}
	/*metodo temporal*/
	private void uniToBidimensional(float[] Matriz) {
		MatrizF = new float[filas][columnas];
		k=0;
		for(i = 0; i<filas ;i++){
			for(j = 0; j<columnas ;j++){
				MatrizF[i][j] = Matriz[k];
				k++;
			}
		}
	}
	private boolean esPar(int valor) {
		return valor%2==0;
	}
	public float determinate() {
		Matriz original = new Matriz(filas,columnas);
		original.asignaValores(Matriz);
		detM=0;
		for (i=0; i<filas; i++){
			if(filas>1) {
				Matriz menor = new Matriz(filas-1,columnas-1);
				menor.menordeMatriz(0,i,original);
				//menor.mostrarEnConsola();
				//System.out.println();
				if(esPar(i+2))
					menor.detM = menor.determinate();
				else
					menor.detM = -1*menor.determinate();
				//depurador
				//System.out.println("detM = "+detM+" + "+Matriz[0][i]*menor.detM);	
				detM = detM + Matriz[0][i]*menor.detM;
				
			}
			else {
				detM = Matriz[0][i];
			}
		}	
		return detM;
		
	}
	public void  mMultiplica(Matriz M_B,Matriz M_A) {
		MatrizF = new float[M_B.filas][M_A.columnas];
		filas = M_B.filas;
		columnas = M_A.columnas;
		for(i=0;i<M_B.filas;i++) {
			for(j=0;j<M_A.columnas;j++) {																		
				MatrizF[i][j]=0;
				for(k=0;k<M_B.columnas;k++)
					MatrizF[i][j]=(MatrizF[i][j]+(M_B.MatrizF[i][k]*M_A.Matriz[k][j]));
			}
		}
	}
	public void calculaInver3x3(){
		MatrizFU = new float[filas*columnas];
		//Calula la transpuesta de la Matriz de Cofactores transpuest = adjunta
		// (Mc)^T=Madj 
		/*Ci,j=(-1)^(i+j)((Mij))*/
		MatrizFU[0] = ( MatrizU[4]*MatrizU[8]-(MatrizU[5]*MatrizU[7])) ;
		MatrizFU[3] = -1*( MatrizU[3]*MatrizU[8]-(MatrizU[5]*MatrizU[6])) ;
		MatrizFU[6] = ( MatrizU[3]*MatrizU[7]-(MatrizU[4]*MatrizU[6])) ;
		MatrizFU[1] = -1*( MatrizU[1]*MatrizU[8]-(MatrizU[2]*MatrizU[7])) ;
		MatrizFU[4] = ( MatrizU[0]*MatrizU[8]-(MatrizU[2]*MatrizU[6])) ;
		MatrizFU[7] = -1*( MatrizU[0]*MatrizU[7]-(MatrizU[1]*MatrizU[6])) ;
		MatrizFU[2] = ( MatrizU[1]*MatrizU[5]-(MatrizU[2]*MatrizU[4])) ;
		MatrizFU[5] = -1*( MatrizU[0]*MatrizU[5]-(MatrizU[2]*MatrizU[3])) ;
		MatrizFU[8] = ( MatrizU[0]*MatrizU[4]-(MatrizU[1]*MatrizU[3])) ;
		//Calucla el determinante de la Matriz
		//det(suma i hasta n aij*acij)
		detM = MatrizU[0]*MatrizFU[0]+MatrizU[1]*MatrizFU[3]+MatrizU[2]*MatrizFU[6];
		System.out.println("\ndetM = "+detM);
		//Matriz Inverza
		for(i=0; i<9; i++){
			MatrizFU[i]*=1/detM;
		}
		uniToBidimensional(MatrizFU);
	}
	public void calculaInvernxn (Matriz original) {
		asignaFloat(original.filas,original.columnas);
		//Calula la transpuesta de la Matriz de Cofactores = adjunta
		// (Mc)^T = Madj 
		//componente = C[i][j]
		//C[i][j]=((-1)^(i+j))  (det(Mij))
		for (i=0; i<original.filas; i++){
			for(j=0; j<original.columnas; j++){
				Matriz menor = new Matriz(original.filas-1,original.columnas-1);
				menor.menordeMatriz(i,j,original);
				//menor.mostrarEnConsola();
				//System.out.println();
				if(esPar(i+j+2)) 
					MatrizF[j][i] = menor.determinate();
				else
					MatrizF[j][i] = -1*menor.determinate();
								
			}
		}	
		detM = original.determinate();
		
		for(i=0; i<filas; i++) {
			for(j=0; j<columnas; j++) {
				MatrizF[i][j]*=1/detM;
			}
		}
	}
	public void menordeMatriz(int ipivote, int jpivote,Matriz fullMatriz){
	
		int k=0,l=0;
		for (i=0; i<fullMatriz.filas; i++) {
	    	for (j=0; j<fullMatriz.columnas; j++) {	
	    		if(ipivote!=i && jpivote!=j) {
	    			//System.out.println("  "+k+"  "+l+"\t"+i+"  "+j+" ");
	    			Matriz[k][l] = fullMatriz.Matriz[i][j]; 
		    		l++;
	    		}
	    	}
	    	if(l>0) {
    			k++;  l=0;
    		}
	    }
	}
}

 