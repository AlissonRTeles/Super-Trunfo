
public class Carta {
	private String cNome;
	private String cCod;
	private float nPeso;
	private float nVelocidade;
	private float nAltitude;
	private float nVoo;
	private float nAltura;
	private Boolean isSTrunfo;
	
	
	public Carta() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Carta(String cNome, String cCod, float nPeso, float nVelocidade,
			float nAltitude, float nVoo, float nAltura, Boolean isSTrunfo) {
		super();
		this.cNome = cNome;
		this.cCod = cCod;
		this.nPeso = nPeso;
		this.nVelocidade = nVelocidade;
		this.nAltitude = nAltitude;
		this.nVoo = nVoo;
		this.nAltura = nAltura;
		this.isSTrunfo = isSTrunfo;
	}
	public String getcNome() {
		return cNome;
	}
	public void setcNome(String cNome) {
		this.cNome = cNome;
	}
	public String getcCod() {
		return cCod;
	}
	public void setcCod(String cCod) {
		this.cCod = cCod;
	}
	public float getnPeso() {
		return nPeso;
	}
	public void setnPeso(float nPeso) {
		this.nPeso = nPeso;
	}
	public float getnVelocidade() {
		return nVelocidade;
	}
	public void setnVelocidade(float nVelocidade) {
		this.nVelocidade = nVelocidade;
	}
	public float getnAltitude() {
		return nAltitude;
	}
	public void setnAltitude(float nAltitude) {
		this.nAltitude = nAltitude;
	}
	public float getnVoo() {
		return nVoo;
	}
	public void setnVoo(float nVoo) {
		this.nVoo = nVoo;
	}
	public float getnAltura() {
		return nAltura;
	}
	public void setnAltura(float nAltura) {
		this.nAltura = nAltura;
	}
	public Boolean getIsSTrunfo() {
		return isSTrunfo;
	}
	public void setIsSTrunfo(Boolean isSTrunfo) {
		this.isSTrunfo = isSTrunfo;
	}
	
	
}
