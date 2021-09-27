package com.demo.hibernate;

import com.demo.hibernate.model.AddressEntity;
import com.demo.hibernate.model.PersonEntity;

import java.util.Arrays;
import java.util.List;

public class LoadSampleData {

    public List<PersonEntity> loadData() {
        PersonEntity p1 = new PersonEntity();
        p1.setPersonId(1);
        p1.setFirstName("Ahmet");
        p1.setLastName("A");

        PersonEntity p2 = new PersonEntity();
        p2.setPersonId(2);
        p2.setFirstName("Burak");
        p2.setLastName("B");

        AddressEntity a1 = new AddressEntity();
        a1.setAddressId(1);

        AddressEntity a2 = new AddressEntity();
        a2.setAddressId(2);

        AddressEntity a3 = new AddressEntity();
        a3.setAddressId(3);

        AddressEntity a4 = new AddressEntity();
        a4.setAddressId(4);

        p1.setAddresses(Arrays.asList(a1, a2));
        a1.setPerson(p1);
        a2.setPerson(p1);
        p2.setAddresses(Arrays.asList(a3, a4));
        a3.setPerson(p2);
        a4.setPerson(p2);

        return Arrays.asList(p1,p2);
    }

}
