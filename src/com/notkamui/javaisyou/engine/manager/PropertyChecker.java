package com.notkamui.javaisyou.engine.manager;

import com.notkamui.javaisyou.engine.property.PropertyType;

public interface PropertyChecker {

  boolean hasProperty(PropertyType property, int id);

}
