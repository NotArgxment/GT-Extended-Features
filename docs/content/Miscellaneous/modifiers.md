---
hide:
  - toc
title: Home
---

# How to use `ExtendedModifiers`

### MACHINE_PARALLEL

Allows parallels without a proper parallel hatch, has configurable duration and energy

```java
// Java
import static com.extendedfeatures.gtef.modifiers.ExtendedModifiers.*;

.recipeModifiers(
    MACHINE_PARALLEL(
        8, // Max Parallels
        0.5, // Duration Multiplier
        0 // Energy Multiplier
    )
)
```

```javascript
// KubeJS
const $ExtendedModifiers = Java.loadClass('com.extendedfeatures.gtef.modifiers.ExtendedModifiers');

.recipeModifiers(
    $ExtendedModifiers.MACHINE_PARALLEL(
        8, // Max Parallels
        0.5, // Duration Multiplier
        0 // Energy Multiplier
    )
)
```

### SCALING_PARALLEL

Parallels and duration increase every time a recipe cycle finishes, **EUt is not modified**
The cycle count resets when the machine goes idle

```java
// Java
import com.extendedfeatures.gtef.modifiers.ExtendedModifiers;

.recipeModifiers(
    ExtendedModifiers.SCALING_PARALLEL(
        1, // Base parallels
        64, // Max Parallels
        2, // Parallels per Cycle
        0.25 // Duration per Cycle
    )
)
```

```javascript
// KubeJS
const $ExtendedModifiers = Java.loadClass('com.extendedfeatures.gtef.modifiers.ExtendedModifiers');

.recipeModifiers(
    $ExtendedModifiers.SCALING_PARALLEL(
        1, // Base parallels
        64, // Max Parallels
        2, // Parallels per Cycle
        0.25 // Duration per Cycle
    )
)
```
