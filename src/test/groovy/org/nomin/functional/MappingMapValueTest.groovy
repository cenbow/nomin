package org.nomin.functional

import org.junit.Test
import org.nomin.core.Nomin
import org.nomin.entity.*
import org.nomin.mappings.MappingMapValue

/**
 * Tests mapping map value with specified key.
 * @author Dmitry Dobrynin
 * Date: 07.10.2010 Time: 22:32:35
 */
class MappingMapValueTest {
  Nomin nomin = new Nomin(MappingMapValue)

  @Test
  void test() {
    Person p = new Person(options: [name: "Person's Name", lastName: "Person's Lastname"])
    Employee e = nomin.map(p, Employee)
    assert e && e.name == "Person's Name" && e.last == "Person's Lastname"
    p = nomin.map(e, Person)
    assert p && p.options && p.options["name"] == "Person's Name" && p.options["lastName"] == "Person's Lastname"
  }
}
