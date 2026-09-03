package br.edu.unifio.ecommerce.entidades;

import java.math.BigDecimal;
import java.time.LocalDateTime;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Setter;
import lombok.Getter;

@Entity
@Getter
@Setter

public class Pedido {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private LocalDateTime data;
    private String status;
    private BigDecimal ValorTotal;

    @ManyToOne
    private Cliente cliente;


}
