package uniandes.edu.co.proyecto.modelo;
import java.sql.Date;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class TransferenciaPK {
    private Date fecha;
    private Integer valor;
    private String tipo;
    @ManyToOne
    @JoinColumn(name = "id_cuenta_1", referencedColumnName = "id")
    private Cuenta id_cuenta_1;
    @ManyToOne
    @JoinColumn(name = "id_cuenta_2", referencedColumnName = "id")
    private Cuenta id_cuenta_2;

    public TransferenciaPK()
    {
        super();
    }
    
    public TransferenciaPK( Cuenta id_cuenta_1,Cuenta id_cuenta_2,Date fecha, Integer valor, String tipo) {
        super();
        this.fecha = fecha;
        this.valor = valor;
        this.tipo = tipo;
        this.id_cuenta_1 = id_cuenta_1;
        this.id_cuenta_2 = id_cuenta_2;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Cuenta getId_cuenta_1() {
        return id_cuenta_1;
    }

    public void setId_cuenta_1(Cuenta id_cuenta_1) {
        this.id_cuenta_1 = id_cuenta_1;
    }

    public Cuenta getId_cuenta_2() {
        return id_cuenta_2;
    }

    public void setId_cuenta_2(Cuenta id_cuenta_2) {
        this.id_cuenta_2 = id_cuenta_2;
    }
}

