package ni.edu.uam.facturacion.modelo;

import java.time.*;
import java.util.Collection;
import javax.persistence.*;

import ni.edu.uam.facturacion.calculadores.CalculadorSiguienteNumeroParaAnyo;
import org.openxava.annotations.*;
import org.openxava.calculators.*;
import lombok.*;

@Entity @Getter @Setter
@View(members=
        "anyo, numero, fecha;" +
                "datos {" +
                "cliente;" +
                "detalles;" +
                "observaciones" +
                "}"
)
abstract public class DocumentoComercial extends Identificable{

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

    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @ReferenceView("Simple") // La vista llamada 'Simple' se usará para visualizar esta referencia
    Cliente cliente;

    @ElementCollection
    @ListProperties("producto.numero, producto.descripcion, cantidad")
    Collection<Detalle> detalles;

    @TextArea
    String observaciones;
}


