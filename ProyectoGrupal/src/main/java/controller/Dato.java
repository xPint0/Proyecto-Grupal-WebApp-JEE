package controller;

public class Dato {
    private String periodo;
    private String tipoVehiculo;
    private String combustible;
    private String unidades;

    public Dato(String periodo, String tipoVehiculo, String combustible, String unidades) {
        this.periodo = periodo;
        this.tipoVehiculo = tipoVehiculo;
        this.combustible = combustible;
        this.unidades = unidades;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public String getCombustible() {
        return combustible;
    }

    public void setCombustible(String combustible) {
        this.combustible = combustible;
    }

    public String getUnidades() {
        return unidades;
    }

    public void setUnidades(String unidades) {
        this.unidades = unidades;
    }

	@Override
	public String toString() {
		return "Dato [periodo=" + periodo + ", tipoVehiculo=" + tipoVehiculo + ", combustible=" + combustible
				+ ", unidades=" + unidades + "]";
	}
}

