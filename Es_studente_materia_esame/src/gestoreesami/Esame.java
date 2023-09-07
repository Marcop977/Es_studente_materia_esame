package gestoreesami;

public class Esame {
	private Studente studente;
	private Materia materia;
	private int voto;
	
	public Esame(Studente studente, Materia materia, int voto) {
		this.studente = studente;
		this.materia = materia;
		this.voto = voto;
	}
	public Studente getStudente() {
		return studente;
	}
	public void setStudente(Studente studente) {
		this.studente = studente;
	}
	public Materia getMateria() {
		return materia;
	}
	public void setMateria(Materia materia) {
		this.materia = materia;
	}
	public int getVoto() {
		return voto;
	}
	public void setVoto(int voto) {
		this.voto = voto;
	}
	@Override
	public String toString() {
		return "Esame [studente=" + studente + ", materia=" + materia + ", voto=" + voto + "]";
	}
	
	
}
