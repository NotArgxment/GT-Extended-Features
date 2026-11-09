### MACHINE_PARALLEL

Allows parallels without a proper parallel hatch, has configurable duration and energy

```java
// Java
import static com.extendedfeatures.gtef.modifiers.ExtendedModifiers.*;

.recipeModifiers(
    MACHINE_PARALLEL(
        maxParallels: 8, durationMultiplier: 0.5, energyMultiplier: 0
    )
)
```

```javascript
// KubeJS
const $ExtendedModifiers = Java.loadClass('com.extendedfeatures.gtef.modifiers.ExtendedModifiers');

.recipeModifiers(
    $ExtendedModifiers.MACHINE_PARALLEL(
        maxParallels: 8, durationMultiplier: 0.5, energyMultiplier: 0
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
        baseParallels: 1, maxParallels: 64, parallelsPerCycle: 2, durationPerCycle: 0.25 /* +25% */
    )
)
```

```javascript
// KubeJS
const $ExtendedModifiers = Java.loadClass('com.extendedfeatures.gtef.modifiers.ExtendedModifiers');

.recipeModifiers(
    $ExtendedModifiers.SCALING_PARALLEL(
        baseParallels: 1, maxParallels: 64, parallelsPerCycle: 2, durationPerCycle: 0.25 /* +25% */
    )
)
```
