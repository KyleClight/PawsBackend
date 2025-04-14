package io.paws.paws.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

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

    private Date birthDate;
    private boolean medication;
    private boolean vaccine;

    //Текущее состояние животного
    private Date lastFeed;
    private Date lastWalk;
    private Date lastMedication;
}
