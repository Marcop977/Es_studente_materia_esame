package gestoreesami;

public class Materia {
	private static int codiceCorrente = 101;
	private int codice;
	private String nome;
	
	public Materia(String nome) {
		this.codice = codiceCorrente++;
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
		return codice + "," + nome;
	}
	
	
}
