package com.notkamui.javaisyou.engine.data;

import com.notkamui.javaisyou.engine.property.MovementProperty;
import com.notkamui.javaisyou.engine.property.PassiveProperty;
import com.notkamui.javaisyou.engine.property.Property;
import com.notkamui.javaisyou.engine.property.PropertyType;

import java.util.*;
import java.util.stream.Collectors;

class PropertyComponent implements EditableProperties {
  private final Map<PropertyType, Set<PassiveProperty>> passiveProps = new HashMap<>();
  private final Map<PropertyType, Set<MovementProperty>> movementProps = new HashMap<>();

  private static <T> Set<T> extractProps(Map<PropertyType, Set<T>> properties) {
    var optionals = new ArrayList<Optional<T>>();
    properties.values().forEach(s -> {
      optionals.add(s.stream().sorted().findFirst()); // collect 1 property of each PropertyType
    });
    return optionals.stream()
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toSet());
  }

  private static <T> void removeProperty(Map<PropertyType, Set<T>> propMap, PropertyType type,T prop) {
    Objects.requireNonNull(propMap);
    Objects.requireNonNull(type);
    Objects.requireNonNull(prop);
    var propSet = propMap.get(type);
    if (propSet == null) {
      return;
    }
    propSet.remove(prop);
    if (propSet.isEmpty()) {
      propMap.remove(type);
    }
  }

  private static <T> void addProperty(Map<PropertyType, Set<T>> propMap, PropertyType type, T prop) {
    Objects.requireNonNull(propMap);
    Objects.requireNonNull(type);
    Objects.requireNonNull(prop);
    var set = propMap.getOrDefault(type, new HashSet<>());
    set.add(prop);
    propMap.put(type, set);
  }

  @Override
  public void addProperty(PassiveProperty prop) {
    Objects.requireNonNull(prop);
    addProperty(passiveProps, prop.type(), prop);
  }

  @Override
  public void addProperty(MovementProperty prop) {
    Objects.requireNonNull(prop);
    addProperty(movementProps, prop.type(), prop);
  }

  @Override
  public void removeProperty(MovementProperty prop) {
    Objects.requireNonNull(prop);
    removeProperty(movementProps, prop.type(), prop);
  }

  @Override
  public void removeProperty(PassiveProperty prop) {
    Objects.requireNonNull(prop);
    removeProperty(passiveProps, prop.type(), prop);
  }

  @Override
  public Set<PropertyType> flags() {
    var flags = new HashSet<>(movementProps.keySet());
    flags.addAll(passiveProps.keySet());
    return flags;
  }

  @Override
  public Set<PassiveProperty> passiveProperties() {
    return extractProps(passiveProps);
  }

  @Override
  public Set<MovementProperty> movementProperties() {
    return extractProps(movementProps);
  }

}
