package gestoreesami;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Metodi {	
	
	public static void registraStudentiMaterie(ArrayList<Studente> studenti, ArrayList<Materia> materie) {
		try {	
			BufferedReader reader = new BufferedReader(new FileReader("files/studenti.txt"));
			BufferedReader reader2 = new BufferedReader(new FileReader("files/materie.txt"));
			String line;
			String line2;
	
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(",");
//				int studenteId = Integer.parseInt(parts[0]);
				String studenteNome = parts[1];
				Studente studente = new Studente(studenteNome);
				studenti.add(studente);
			}
			reader.close();
	
			while ((line2 = reader2.readLine()) != null) {
				String[] parts = line2.split(",");
//				int materiaCodice = Integer.parseInt(parts[0]);
				String materiaNome = parts[1];
				Materia materia = new Materia(materiaNome);
				materie.add(materia);
			}
			reader2.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}	

	public static void visualizzaStudenti(ArrayList<Studente> studenti) {
		System.out.println("Studenti:");
		for (Studente studente : studenti) {
			System.out.println(studente);
		}
	}
	
	public static void visualizzaMaterie(ArrayList<Materia> materie) {
		System.out.println("Materie:");
		for (Materia materia : materie) {
			System.out.println(materia);
		}
	}
	
	public static void visualizzaEsami(ArrayList<Esame> esami) {
		System.out.println("Esami:");
		for (Esame esame : esami) {
			System.out.println(esame);
		}
	}
	
	public static void nuovoStudente(ArrayList<Studente> studenti, Scanner s) {
		System.out.println("Inserisci nome studente -->");
		s.nextLine();
		String nomeStudente = s.nextLine();
		Studente studenteInput = new Studente(nomeStudente);
		studenti.add(studenteInput);
		PrintWriter pwStudenti;
		try {
			pwStudenti = new PrintWriter(new File("files/studenti.txt"));
			for (Studente studente : studenti) {
				pwStudenti.println(studente);
			}
			pwStudenti.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Studente aggiunto.");
	}

	public static void nuovaMateria(ArrayList<Materia> materie, Scanner s) {
		System.out.println("Inserisci nome materia -->");
		String nomeMateria = s.next();
		Materia materiaInput = new Materia(nomeMateria);
		materie.add(materiaInput);	
		PrintWriter pwMaterie;
		try {
			pwMaterie = new PrintWriter(new File("files/materie.txt"));
			for (Materia materia : materie) {
				pwMaterie.println(materia);
			}
			pwMaterie.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Materia aggiunta.");
	}
	
	public static void inserisciVoto(ArrayList<Studente> studenti, ArrayList<Materia> materie, ArrayList<Esame> esami, Scanner s, File out) {
		System.out.println("Studenti:");
		for (Studente studente : studenti) {
			System.out.println(studente);
		}

		System.out.println("Seleziona ID studente -->");
		int sceltaStudente = s.nextInt();
		System.out.println(studenti.get(sceltaStudente - 1));
		System.out.println("Materie:");
		for (Materia materia : materie) {
			System.out.println(materia);
		}

		System.out.println("Seleziona ID materia -->");
		int sceltaMateria = s.nextInt();
		System.out.println(materie.get(sceltaMateria - 101));

		System.out.println("Inserisci voto per " + materie.get(sceltaMateria - 101).getNome() + " allo studente " + studenti.get(sceltaStudente - 1) + " -->");
		int voto = s.nextInt();
		System.out.println("Vuoi registrare questo esame sostenuto da " + studenti.get(sceltaStudente - 1) + " in " + materie.get(sceltaMateria - 101).getNome() + "? y/n");
		String sceltaEsame = s.next();
		switch(sceltaEsame) {
			case "y":
				Esame esame = new Esame(studenti.get(sceltaStudente - 1), materie.get(sceltaMateria - 101), voto);
				esami.add(esame);
			PrintWriter pw;
			try {
				pw = new PrintWriter(out);
				for (Esame esame2 : esami) {
					pw.print(esame2);
				}
				pw.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
				System.out.println("Esame per " + esame.getStudente().getNome() + " in " + esame.getMateria().getNome() + " superato con un voto di " + esame.getVoto() + ": aggiunto con successo.");
				break;
			case "n":
				break;
		}
	}
	
	public static double calcolaMediaStudenti(ArrayList<Esame> esami, int idStudente) {
		double totale = 0;
		int numEsami = 0;

		for (Esame esame : esami) {
			if(idStudente == esame.getStudente().getId()) {
				totale += esame.getVoto();
				numEsami++;
			}else{
				System.out.println("Nessuno studente ha dato un esame in questa materia.");
			}
		}

		return totale / numEsami;
	}
	
	public static void calcolaMediaStudenti2(ArrayList<Studente> studenti, ArrayList<Esame> esami, Scanner s) {
		for (Studente studente : studenti) {
			System.out.println(studente);
		}
		System.out.println("Inserisci ID dello studente di cui sapere la media:");
		int idStudente = s.nextInt();
		System.out.println("La media voti dello studente è: " + calcolaMediaStudenti(esami, idStudente));
	}

	public static double calcolaMediaStudentiMateria(ArrayList<Esame> esami, int codiceMateria) {
		double totale = 0;
		int numMaterie = 0;

		for (Esame esame : esami) {
			if(codiceMateria == (esame.getMateria().getCodice())) {  //se il codice che inserisce l'utente corrisponde al codice della materia
				totale += esame.getVoto();  //tutti i voti della materia corrispondente all'input utente
				numMaterie++;
			}else{
				System.out.println("Lo studente non ha tenuto nessun esame.");
			}
		}
		
		return totale / numMaterie;		
	}
	
	public static void calcolaMediaStudentiMateria2(ArrayList<Materia> materie, ArrayList<Esame> esami, Scanner s) {
		for (Materia materia : materie) {
			System.out.println(materia);
		}
		System.out.println("Inserisci l'ID della materia -->");
		int materiaScelta = s.nextInt();
		System.out.println(materie.get(materiaScelta - 101));
		System.out.println("La media degli studenti in " + materie.get(materiaScelta - 101) + " è: " + calcolaMediaStudentiMateria(esami, materiaScelta));
	}
	
	
	public static ArrayList<String> mostraVotiStudente(ArrayList<Esame> esami, int idStudente, ArrayList<String> voti) {
		String voto = "";

		for (Esame esame : esami) {
			if(idStudente == esame.getStudente().getId()) {
				voto = esame.getMateria() + ", voto: " + esame.getVoto();
				voti.add(voto);
			}
			
		}
		return voti;
	} 
	
	public static void mostraVotiStudente2(ArrayList<Studente> studenti, ArrayList<Esame> esami, ArrayList<String> voti, Scanner s) {
		for (Studente studente : studenti) {
			System.out.println(studente);
		}

		System.out.println("Seleziona ID studente di cui visualizzare i voti -->");
		int sceltaStudente2 = s.nextInt();

		voti.clear();
		mostraVotiStudente(esami, sceltaStudente2, voti);
	
//		Comparator<String> comparatorePerNome = new Comparator<String>() {      //per ordinare (in ordine crescente) in base alla materia
//	        
//	        @Override
//	        public int compare(String materia1, String materia2) {
//	             String nome1 = materia1.split(":")[1]; // Ottieni il nome dalla stringa
//	             String nome2 = materia2.split(":")[1]; // Ottieni il nome dalla stringa
//	             return nome1.compareTo(nome2);
//	         }
//	     };
//
//	        
//	    Collections.sort(voti, comparatorePerNome);
		
		Comparator<String> comparatorePerVoto = new Comparator<String>() {            //ordine descrescente per voto
		    @Override
		    public int compare(String esame1, String esame2) {
		       
		        int voto1 = Integer.parseInt(esame1.split(": ")[1]);       // dove ci sono ": " divide in sottostringhe
		        int voto2 = Integer.parseInt(esame2.split(": ")[1]);

		        // Confronta i voti in modo decrescente
		        return Integer.compare(voto2, voto1);    //se il risultato è negativo, il primo numero viene inserito prima del secondo, altrimenti viene inserito dopo
		    }
		};

		Collections.sort(voti, comparatorePerVoto);        //Collectios.sort(voti) ordinerebbe secondo l'ordine di default, con il secondo parametro andiamo a dire secondo quale logica ordina
	    
		for (String voto3 : voti) {
			System.out.println(voto3);
		}
	}
	
	
	
	
	
	
	
	
	
	
	//ordinamento numeri in arraylist con selection sort
//	public static void scambia(ArrayList<Integer> v, int posizione1, int posizione2) {      //metodo per scambiare due valori di tipo int
//		int temp;
//		temp = v.get(posizione1);
//		v.set(posizione1, v.get(posizione2));
//		v.set(posizione2, temp);		
//	}
//	
//	
//	public static ArrayList<Integer> ordinaId(ArrayList<Integer> a){                        //algoritmo per ordinare in base ad un arraylist di numeri (quindi solo numeri, per l'arraylist voti occorreva altro)
//		ArrayList<Integer> ordinato = new ArrayList<>(a.size());
//		
//		ordinato.addAll(a);
//		
//		for(int i = 0; i < ordinato.size() - 1; i++) {
//			for(int j = i+1; j < ordinato.size(); j++) {
//				if(ordinato.get(i) < ordinato.get(j)) {
//					scambia(ordinato, i, j);
//				}
//			}
//		}
//		
//		return ordinato;
//	}

	
//	public static void scambiaStringhe(ArrayList<String> v, int posizione1, int posizione2) {
//		String temp;
//		temp = v.get(posizione1);
//		v.set(posizione1, v.get(posizione2));
//		v.set(posizione2, temp);		
//	}
//	
//	
//	public static ArrayList<String> ordinaStringhe(ArrayList<String> a) {                      //algoritmo per ordinare in base ad un arraylist di stringhe (partendo dalla prima parola)
//	    ArrayList<String> ordinato = new ArrayList<>(a.size());
//	    ordinato.addAll(a);
//
//	    for (int i = 0; i < ordinato.size() - 1; i++) {
//	        for (int j = i + 1; j < ordinato.size(); j++) {
//	            if (ordinato.get(i).compareToIgnoreCase(ordinato.get(j)) < 0) {
//	                scambiaStringhe(ordinato, i, j);
//	            }
//	        }
//	    }
//
//	    return ordinato;
//	}
}
