package com.escola.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Participante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório.")
    @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres.")
    private String nome;

    @NotNull(message = "A idade é obrigatória.")
    @Min(value = 5, message = "A idade mínima é 5.")
    @Max(value = 100, message = "A idade máxima é 100.")
    private Integer idade;

    @NotNull(message = "A nota do 1º semestre é obrigatória.")
    @Min(value = 0, message = "A nota mínima do 1º semestre é 0.")
    @Max(value = 10, message = "A nota máxima do 1º semestre é 10.")
    private Double notaPrimeiroSemestre;

    @NotNull(message = "A nota do 2º semestre é obrigatória.")
    @Min(value = 0, message = "A nota mínima do 2º semestre é 0.")
    @Max(value = 10, message = "A nota máxima do 2º semestre é 10.")
    private Double notaSegundoSemestre;

    private double mediaFinal;
}
