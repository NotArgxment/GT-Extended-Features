## Machine Parallels

Allows any multiblock to run parallels **without a parallel hatch part in its structure**.
New modifiers were added to extend how parallels behave

```java
// Java
import com.extendedfeatures.gtef.modifiers.MachineParallel;

.recipeModifiers(
        MACHINE_PARALLEL(
                8, // Parallels
                0.5, // Duration Multiplier
                0, // Energy Multiplier
        )
)
```

```javascript
// KubeJS
const $MACHINE_PARALLEL = Java.loadClass('com.extendedfeatures.gtef.modifiers.MachineParallel');

.recipeModifiers(
    $MACHINE_PARALLEL(
        8, // Parallels
        0.5, // Duration Multiplier
        0 // Energy Multiplier
    )
)
```

Try playing around the values to get balanced Machine Parallels