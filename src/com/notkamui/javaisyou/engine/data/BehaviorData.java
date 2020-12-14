package com.notkamui.javaisyou.engine.data;

import com.notkamui.javaisyou.engine.boardelement.HasFlag;
import com.notkamui.javaisyou.engine.boardelement.HasImage;
import com.notkamui.javaisyou.engine.boardelement.HasProperty;
import com.notkamui.javaisyou.engine.property.MovementProperty;
import com.notkamui.javaisyou.engine.property.PassiveProperty;
import com.notkamui.javaisyou.engine.property.PropertyFlag;

import javax.swing.*;
import java.util.Set;
import java.util.Set;
import java.util.TreeSet;

public interface BehaviorData extends HasFlag, HasProperty, HasImage {
  static BehaviorData emptyData() {
    return new BehaviorData() {
      @Override
      public Set<PropertyFlag> flags() {
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
