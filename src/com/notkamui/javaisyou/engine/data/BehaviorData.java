package com.notkamui.javaisyou.engine.data;

import com.notkamui.javaisyou.engine.HasImage;
import com.notkamui.javaisyou.engine.property.MovementProperty;
import com.notkamui.javaisyou.engine.property.PassiveProperty;
import com.notkamui.javaisyou.engine.property.PropertyType;

import javax.swing.*;
import java.util.Set;

public interface BehaviorData extends HasProperty, HasImage {
  static BehaviorData emptyData() {
    return new BehaviorData() {
      @Override
      public Set<PropertyType> flags() {
        return Set.of();
      }

      @Override
      public ImageIcon image() {
        return new ImageIcon();
      }

      @Override
      public Set<MovementProperty> movementProperties() {
        return Set.of();
      }

      @Override
      public Set<PassiveProperty> passiveProperties() {
        return Set.of();
      }
    };
  }
}
