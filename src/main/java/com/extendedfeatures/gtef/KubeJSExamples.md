### Greenhouse

```javascript
    ServerEvents.recipes(event => {
        event.recipes.extendedfeatures.arboreal_farming('recipe_name')
            .notConsumable()
            .itemInputs()
            .inputFluids()
            .itemOutputs()
            .circuit()
            .duration()
            .EUt(GTValues.VA[GTValues.(tier)])
    });
```

```javascript
    ServerEvents.recipes(event => {
        event.recipes.extendedfeatures.general_farming('recipe_name')
            .notConsumable()
            .itemInputs()
            .inputFluids()
            .itemOutputs()
            .circuit()
            .duration()
            .EUt(GTValues.VA[GTValues.(tier)])
    });
```

### Rock Processing Plant

```javascript
    ServerEvents.recipes(event => {
        event.recipes.extendedfeatures.rock_processing_plant('recipe_name')
            .itemInputs()
            .inputFluids()
            .itemOutputs()
            .outputFluids()
            .duration()
            .EUt(GTValues.VA[GTValues.(tier)])
    });
```

### Large Gas Collector

```javascript
    ServerEvents.recipes(event => {
        event.recipes.extendedfeatures.air_collection('recipe_name')
            .circuit()
            .outputFluid()
            .duration()
            .EUt(GTValues.VA[GTValues.(tier)])
    });
```

### Chemical Skips

```javascript
    ServerEvents.recipes(event => {
        event.recipes.extendedfeatures.chemical_skips('recipe_name')
            .itemInputs()
            .inputFluids()
            .itemOutputs()
            .outputFluids()
            .circuit()
            .duration()
            .EUt(GTValues.VA[GTValues.(tier)])
    });
```

### Casing Disassembly

**small note: avoid making new recipes for "machine_disassembly" mode, this recipe type has its own recipe logic already that fetches all singleblock machines and multiblock controllers**

```javascript
    ServerEvents.recipes(event => {
        event.recipes.extendedfeatures.component_disassembly('recipe_name')
            .itemInputs()
            .itemOutputs()
            .outputFluids()
            .circuit()
            .duration()
            .EUt(GTValues.VA[GTValues.(tier)])
    });
```
