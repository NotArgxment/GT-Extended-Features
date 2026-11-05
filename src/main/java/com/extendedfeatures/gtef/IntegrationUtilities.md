## Machine Parallels

Allows any multiblock to run parallels **without a parallel hatch part in its structure**.

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

Try playing around the values to get good Machine Parallels

### Gradients based on the GTCEu Energy Tiers (LV -> MAX)

<img width="482" height="458" alt="Howeachlooks-ezgif com-video-to-gif-converter (1)" src="https://github.com/user-attachments/assets/f8a2ef73-22e1-43cf-aab7-d034d8df3da2" />

### How to use

```java
import com.extendedfeatures.gtef.misc.ElectricTiersGradientStyles;

// Basic tooltip builder
.tooltipBuilder((stack, list) -> list.add(
        Component.translatable("extendedfeatures.regular.tooltip.0")
            .append(Component.translatable("extendedfeatures.styled.tooltip.1")
                .withStyle(ElectricTiersGradientStyles.HV_GRADIENT_FAST))
        // Where "HV_GRADIENT_FAST" can be replaced with other styles from ElectricTiersGradientStyles or TooltipHelper from GTCEu
        // Check ElectricTiersGradientStyles.java for the tier you want to use as tooltip
        ))
```
