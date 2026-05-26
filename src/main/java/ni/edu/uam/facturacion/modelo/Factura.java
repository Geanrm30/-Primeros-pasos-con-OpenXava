package ni.edu.uam.facturacion.modelo;

import java.time.*;
import java.util.Collection;
import javax.persistence.*;

import ni.edu.uam.facturacion.calculadores.CalculadorSiguienteNumeroParaAnyo;
import org.hibernate.annotations.GenericGenerator;
import org.openxava.annotations.*;
import org.openxava.calculators.*;
import lombok.*;

@Entity @Getter @Setter
@View(members= // Esta vista no tiene nombre, por tanto será la vista usada por defecto
        "anyo, numero, fecha;" + // Separados por coma significa en la misma línea
                "cliente;" + // Punto y coma significa nueva línea
                "detalles;" +
                "observaciones"
)
public class Factura {

    @Id
    @GeneratedValue(generator="system-uuid")
    @Hidden
    @GenericGenerator(name="system-uuid", strategy="uuid")
    @Column(length=32)
    String oid;

    @Column(length=4)
    @DefaultValueCalculator(CurrentYearCalculator.class) // Año actual
    int anyo;

    @Column(length=6)
    @DefaultValueCalculator(value= CalculadorSiguienteNumeroParaAnyo.class,
            properties=@PropertyValue(name="anyo") // Para inyectar el valor de anyo de Factura
            // en el calculador antes de llamar a calculate()
    )
    int numero;

    @Required
    @DefaultValueCalculator(CurrentLocalDateCalculator.class) // Fecha actual
    LocalDate fecha;

    @ManyToOne(fetch=FetchType.LAZY, optional=false) // El cliente es obligatorio
    Cliente cliente;

    @ElementCollection
    @ListProperties("producto.numero, producto.descripcion, cantidad")
    Collection<Detalle> detalles;

    @TextArea
    String observaciones;
}
