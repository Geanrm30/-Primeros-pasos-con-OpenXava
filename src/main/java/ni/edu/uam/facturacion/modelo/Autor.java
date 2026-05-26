package ni.edu.uam.facturacion.modelo;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.openxava.annotations.*;
import lombok.*;

import java.util.Collection;

@Entity @Getter @Setter
public class Autor extends Identificable {

    @Column(length=50) @Required
    String nombre;

    @OneToMany(mappedBy="autor")
    @ListProperties("numero, descripcion, precio")
    Collection<Producto> productos;
}
