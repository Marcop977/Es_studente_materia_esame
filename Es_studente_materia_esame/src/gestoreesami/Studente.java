package gestoreesami;

public class Studente {
	private static int idCorrente = 1;
	private int id;
	private String nome;
	
	public Studente(String nome) {
		this.id = idCorrente++;
		this.nome = nome;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Override
	public String toString() {
		return id + "," + nome;
	}
	
	
}
