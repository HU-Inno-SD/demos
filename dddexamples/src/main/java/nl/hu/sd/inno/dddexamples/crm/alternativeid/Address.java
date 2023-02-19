package nl.hu.sd.inno.dddexamples.crm.alternativeid;

//Ik zal eerlijk toegeven dat ik licht teleurgesteld ben in Java dat je hier geen private validating constructor van kan maken...
public record Address(String street, String housenr, String city, String zipcode) {
}
