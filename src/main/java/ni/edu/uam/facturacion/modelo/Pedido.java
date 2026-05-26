package ni.edu.uam.facturacion.modelo;

import javax.persistence.*;
import lombok.*;

@Entity @Getter @Setter
public class Pedido extends DocumentoComercial {

    @ManyToOne
    Factura factura;

}
