package helloeventsourcing.events;

import helloeventsourcing.SomeEntity;

public interface SomeEntityEventHandler {
    SomeEntity apply(ContactDataChanged event);
    SomeEntity apply(NameTypoFixed event);
    //De created Event is 'anders', want dan is er nog geen object om 'm te accepteren
}
