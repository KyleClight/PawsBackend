package io.paws.paws.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/*
Автоматически создаёт:
getters/setters
toString()
hashCode()/equals()
конструктор без аргументов
 */
@Data

@Entity //узнать, что класс является сущностью JPA т.е он будет отображаться в таблице в БД
@Table(name="pets")
public class Pets {

    private enum Sex {
        Male, Female
    }

    @Id //Первичный ключ в таблице БД
    //Автоматическая генерация поля ID
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    private String imageUrl;
    private String name;
    private String type;
    private String breed;
    private String chipNumber;
    private int age;

    private String birthDate;
    private boolean medication;
    private boolean vaccine;

    //Текущее состояние животного
    private String lastFeed;
    private String lastWalk;
    private String lastMedication;
}
