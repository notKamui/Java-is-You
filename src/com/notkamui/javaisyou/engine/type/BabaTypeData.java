package com.notkamui.javaisyou.engine.type;

sealed interface BabaTypeData extends Flaggable, HasProperties permits BasicBabaTypeData, BabaWordTypeData {}
