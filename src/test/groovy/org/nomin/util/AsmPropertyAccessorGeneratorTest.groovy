package org.nomin.util

import org.junit.Test
import org.nomin.entity.Person
import org.nomin.core.TypeInfo
import org.nomin.core.NominException

/**
 * Tests AsmPropertyAccessorGenerator.
 * @author Dmitry Dobrynin
 * Created 24.05.2010 17:01:47
 */
class AsmPropertyAccessorGeneratorTest {
  AsmPropertyAccessorGenerator gen = new AsmPropertyAccessorGenerator()
  int i
  long l
  double d
  float f
  byte bt
  char c
  short s
  boolean bool

  @Test
  void testGenerateAccessor() {
    PropertyAccessor pa = gen.generateAccessor("name", TypeInfo.typeInfo(String), Person.getMethod("getName"),
            Person.getMethod("setName", String))
    assert pa
    Person p = new Person(name: "Person")
    assert pa.get(p) == p.name
    pa.set p, "New name"
    assert p.name == "New name"
    assert pa.is(gen.generateAccessor("name", TypeInfo.typeInfo(String), Person.getMethod("getName"),
            Person.getMethod("setName", String)))
  }

  @Test
  void testNoGetter() {
    PropertyAccessor pa = gen.generateAccessor("name", TypeInfo.typeInfo(String), null, Person.getMethod("setName", String))
    try {
      pa.get(new Person())
      assert false
    } catch (NominException e) {
      assert e.message == "Property org.nomin.entity.Person.name has no getter!"
    }
  }

  @Test
  void testNoSetter() {
    PropertyAccessor pa = gen.generateAccessor("name", TypeInfo.typeInfo(String), Person.getMethod("getName"), null)
    try {
      pa.set(new Person(), null)
      assert false
    } catch (NominException e) {
      assert e.message == "Property org.nomin.entity.Person.name has no setter!"
    }
  }

  @Test
  void testSetterForPrimitive() {
    PropertyAccessor pa = gen.generateAccessor("i", TypeInfo.typeInfo(Integer.TYPE), getClass().getMethod("getI"), getClass().getMethod("setI", Integer.TYPE))
    pa.set(this, 5)
    assert i == 5 && pa.get(this) == i
    pa = gen.generateAccessor("l", TypeInfo.typeInfo(Long.TYPE), getClass().getMethod("getL"), getClass().getMethod("setL", Long.TYPE))
    pa.set(this, 10L)
    assert l == 10L && pa.get(this) == l
    pa = gen.generateAccessor("d", TypeInfo.typeInfo(Double.TYPE), getClass().getMethod("getD"), getClass().getMethod("setD", Double.TYPE))
    pa.set(this, 10D)
    assert d == 10D && pa.get(this) == d
    pa = gen.generateAccessor("f", TypeInfo.typeInfo(Float.TYPE), getClass().getMethod("getF"), getClass().getMethod("setF", Float.TYPE))
    pa.set(this, 10F)
    assert d == 10F && pa.get(this) == f
    pa = gen.generateAccessor("bt", TypeInfo.typeInfo(Byte.TYPE), getClass().getMethod("getBt"), getClass().getMethod("setBt", Byte.TYPE))
    pa.set(this, new Byte("1"))
    assert bt == new Byte("1") && pa.get(this) == bt
    pa = gen.generateAccessor("bool", TypeInfo.typeInfo(Boolean.TYPE), getClass().getMethod("getBool"), getClass().getMethod("setBool", Boolean.TYPE))
    pa.set(this, true)
    assert bool && pa.get(this)
    pa = gen.generateAccessor("c", TypeInfo.typeInfo(Character.TYPE), getClass().getMethod("getC"), getClass().getMethod("setC", Character.TYPE))
    pa.set(this, "C".charAt(0))
    assert c == "C".charAt(0) && pa.get(this) == c
    pa = gen.generateAccessor("s", TypeInfo.typeInfo(Short.TYPE), getClass().getMethod("getS"), getClass().getMethod("setS", Short.TYPE))
    pa.set(this, Short.valueOf("3"))
    assert s == new Short("3") && pa.get(this) == s
  }
}
