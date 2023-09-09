package gestoreesami;

public class Materia {
	private int codice;
	private String nome;
	
	public Materia(int codice, String nome) {
		this.codice = codice;
		this.nome = nome;
	}
	public int getCodice() {
		return codice;
	}
	public void setCodice(int codice) {
		this.codice = codice;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Override
	public String toString() {
		return codice + ". Materia: " + nome;
	}
	
	
}
