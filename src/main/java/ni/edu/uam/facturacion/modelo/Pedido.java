package ni.edu.uam.facturacion.modelo;

import javax.persistence.*;
import lombok.*;
import org.openxava.annotations.View;

@Entity @Getter @Setter
@View(extendsView="super.DEFAULT",
        members="factura { factura }"
)
@View(name="SinClienteNiFactura",
        members=
                "anyo, numero, fecha;" +
                        "detalles;" +
                        "observaciones"
)
public class Pedido extends DocumentoComercial {

    @ManyToOne
    Factura factura;

}
