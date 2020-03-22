package com.demente.ideas.learn.models.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

// @RequestScope: Durara lo que dure la peticion HTTP de usuario, cada usuario podra
// tener un componente ditinto y propio. Si modificamos un valor, este no repercutira
// en el resto de los componentes.

// @SessionScope: Durara hasta que cerremos el browser o navegador, tambien puede terminar
// si ocurre un timeout o cuando se invalida la sesion. Se debe implementar serializable si
// se quiere utilizar este scope

// @ApplicationScope:
// @Prototype: 

// Este POJO puede ser manejado por Spring, para esto anotamos al mismo
// con la anotacion @Componet
@Component
public class Factura {

    @Value("${factura.descripcion}")
    private String descripcion;

    @Autowired
    private Cliente cliente;

    @Autowired
    //@Qualifier("itemsFacturaFutbol")
    private List<ItemFactura> items;

    @PostConstruct
    public void inizializar() {
        cliente.setNombre(cliente.getNombre().concat(" Andres "));
        descripcion = descripcion.concat(" del cliente: " + cliente.getNombre());
    }

    // Por defecto, los componentes manejados por Spring son singleton y se destruyen
    // una vez que la aplicacion termine shutdown.
    @PreDestroy
    public void destroy() {
        System.out.println("Se procede a destruir el objeto Factura, descripcion: " + descripcion);
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemFactura> getItems() {
        return items;
    }

    public void setItems(List<ItemFactura> items) {
        this.items = items;
    }
}
