# Developer utilities

## Machine Parallels
Allows any multiblock to run parallels **without a parallel hatch part in its structure**. EU/t stays the same, but
duration is multiplied ×2 for each parallel achieved.

```java
// Java
import com.extendedfeatures.init.contents.modifiers.CustomRecipeModifiers;

.recipeModifiers(MACHINE_PARALLEL(n), GTRecipeModifiers.[...])
```

```javascript
// KubeJS
const CustomModifier = Java.loadClass('com.extendedfeatures.init.contents.modifiers')

.recipeModifiers(CustomModifier.MACHINE_PARALLEL(n))
```

### Gradients based on the GTCEu Energy Tiers (LV -> MAX)

<img width="482" height="458" alt="Howeachlooks-ezgif com-video-to-gif-converter (1)" src="https://github.com/user-attachments/assets/f8a2ef73-22e1-43cf-aab7-d034d8df3da2" />

### How to use

```java
    // Basic tooltip builder
    .tooltipBuilder((stack, list) -> list.add(
            Component.translatable("extendedfeatures.regular.tooltip.0")
                .append(
                    Component.translatable("extendedfeatures.styled.tooltip.1")
                        .withStyle(CustomTooltipStyles.HV_GRADIENT))
        // Where "HV_GRADIENT" can be replaced with other styles from CustomTooltipStyles or TooltipHelper from GTCEu
        // Check CustomTooltipStyles.java for the tier you want to use as tooltip
        );
    })
```
