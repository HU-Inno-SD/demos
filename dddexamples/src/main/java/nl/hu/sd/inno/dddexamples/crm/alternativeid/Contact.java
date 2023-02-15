package nl.hu.sd.inno.dddexamples.crm.alternativeid;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "ContactAltId")
public class Contact {

    @GeneratedValue
    @Id
    private Long id;

    private String name; //Even de rest van de properties negerend, zie daarvoor valueobjects/noddd
}
