package ni.edu.uam.facturacion.modelo;

import javax.persistence.*;
import lombok.*;
import org.openxava.annotations.View;
import java.util.Collection;

@Entity @Getter @Setter
@View(extendsView="super.DEFAULT",
        members="pedidos { pedidos }"
)
@View(name="SinClienteNiPedidos",
        members=
                "anyo, numero, fecha;" +
                        "detalles;" +
                        "observaciones"
)
public class Factura extends DocumentoComercial {

    @OneToMany(mappedBy="factura")
    Collection<Pedido> pedidos;

}