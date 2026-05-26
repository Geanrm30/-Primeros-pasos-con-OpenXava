package ni.edu.uam.facturacion.modelo;

import javax.persistence.*;
import lombok.*;
import java.util.Collection;

@Entity @Getter @Setter
public class Factura extends DocumentoComercial {

    @OneToMany(mappedBy="factura")
    Collection<Pedido> pedidos;

}