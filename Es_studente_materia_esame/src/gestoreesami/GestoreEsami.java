package gestoreesami;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class GestoreEsami {
	public static void main(String[] args) {
		ArrayList<Studente> studenti = new ArrayList<>();
		ArrayList<Materia> materie = new ArrayList<>();
		ArrayList<Esame> esami = new ArrayList<>();
		
		ArrayList<String> voti = new ArrayList<>();

		File out = new File("files/esami.txt");

		Metodi.registraStudentiMaterie(studenti, materie);

		Scanner s = new Scanner(System.in);

		boolean condizione = true;		
		while(condizione) {
			System.out.println("1 --> Visualizza studenti\n2 --> Visualizza materie\n3 --> Visualizza esami\n4 --> Inserisci nuovo studente\n5 --> Inserisci nuova materia\n6 --> Inserisci voto agli studenti\n7 --> Calcola media voti studente per ogni materia\n8 --> Calcola media di tutti gli studenti per materia\n9 --> Visualizza voti studenti per materia\n0 --> Esci");
			int scelta = s.nextInt();
			switch(scelta) {
				case 1:
					Metodi.visualizzaStudenti(studenti);
					break;
				case 2:
					Metodi.visualizzaMaterie(materie);
					break;
				case 3:
					Metodi.visualizzaEsami(esami);
					break;
				case 4:
					Metodi.nuovoStudente(studenti, s);
					break;
				case 5:
					Metodi.nuovaMateria(materie, s);
					break;
				case 6:
					Metodi.inserisciVoto(studenti, materie, esami, s, out);
					break;
				case 7:
					Metodi.calcolaMediaStudenti2(studenti, esami, s);
					break;
				case 8:
					Metodi.calcolaMediaStudentiMateria2(materie, esami, s);
					break;
				case 9:
					Metodi.mostraVotiStudente2(studenti, esami, voti, s);
					break;
				case 0:
					s.close();
					System.out.println("Programma terminato.");
					System.exit(0);
			}
		}

	}
	

}



