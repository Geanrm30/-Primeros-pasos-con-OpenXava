package ni.edu.uam.facturacion.modelo;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.openxava.annotations.*;
import lombok.*;

@Entity @Getter @Setter
public class Categoria extends Identificable{

    @Column(length=50)
    String descripcion;
}