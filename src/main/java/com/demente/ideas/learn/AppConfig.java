package com.demente.ideas.learn;

import com.demente.ideas.learn.models.domain.ItemFactura;
import com.demente.ideas.learn.models.domain.Producto;
import com.demente.ideas.learn.models.services.IUserServiceBean;
import com.demente.ideas.learn.models.services.UserServiceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Configuration
public class AppConfig {

    // Otra forma de indicarle al contenedor de Spring
    // que sera un componente a utilizar (inyectar)
    // En este caso se registrara una lista como un componente
    @Bean("itemsFactura")
    @Primary
    public List<ItemFactura> registrarItems() {

        Producto producto = new Producto("Raqueta", 50);
        Producto productoB = new Producto("Pelota", 8);
        Producto productoC = new Producto("Camiseta", 15);

        ItemFactura factura = new ItemFactura(producto, 10);
        ItemFactura facturaB = new ItemFactura(productoB, 25);
        ItemFactura facturaC = new ItemFactura(productoC, 100);

        return Arrays.asList(factura, facturaB, facturaC);
    }

    @Bean("itemsFacturaFutbol")
    public List<ItemFactura> registrarItemsFutbol() {

        Producto producto = new Producto("Zapato", 50);
        Producto productoB = new Producto("Pelota", 8);
        Producto productoC = new Producto("Canillera", 15);
        Producto productoD = new Producto("Camiseta", 5);
        Producto productoE = new Producto("Tobillera", 28);

        ItemFactura factura = new ItemFactura(producto, 10);
        ItemFactura facturaB = new ItemFactura(productoB, 25);
        ItemFactura facturaC = new ItemFactura(productoC, 100);
        ItemFactura facturaD = new ItemFactura(productoD, 78);
        ItemFactura facturaE = new ItemFactura(productoE, 34);

        return Arrays.asList(factura, facturaB, facturaC, facturaD, facturaE);
    }
}
