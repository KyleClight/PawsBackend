package io.paws.paws.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/*
Автоматически создаёт:
getters/setters
toString()
hashCode()/equals()
конструктор без аргументов
 */
@Data

@Entity //узнать, что класс является сущностью JPA т.е он будет отображаться в таблице в БД
@Table(name = "pets")
public class Pets {
    private enum Sex {
        Male, Female
    }

    @Id //Первичный ключ в таблице БД
    //Автоматическая генерация поля ID
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String type;
    private String breed;
    private String chipNumber;
    private Sex sex;
    private LocalDate birthDate;
    private boolean medication;
    private int age;

    //Текущее состояние животного
    private LocalDateTime lastFeed;
    private LocalDateTime lastWalk;
    private LocalDateTime lastMedication;
}
