---
hide:
  - navigation
  - toc
title: Home
---

# Welcome!

Gregtech Modern Extended Features, or **GT:EF** for short, an small addon 
for GTCEu Modern 1.20.1 **(7.5.3)** that contains a few QoL features for any GTM Playthrough, also 
contains a few utilites for Addon/Modpack developers

Quick mention that this project could also be great for people that's getting into GTM Addon development! since everything
is pretty basic and follows most of the native/public APIs GT provides

## How to include it
Through `gradle` dependencies using **Modrinth**:
```groovy
repositories {
    
  exclusiveConcent {
    forRepository {
      maven {
        name = "Modrinth"
        url = "https://api.modrinth.com/maven"
      }
    }
      
    filter {
      includeGroup "maven.modrinth"
    }
  }
    
}

dependencies {
  modImplementation("maven.modrinth:extendedfeatures:${minecraft_version}-${gtef_version}")
}
```
Your `gradle.properties`:
```properties
# Base properties
    minecraft_version = 1.20.1
# Dependencies
    gtef_version = 3.0.0-forge
```

Simple "mod import" using Curse Maven Snippets:
```groovy
// "1604172" = Project ID, "9064844" = Mod file, this one points to 3.0.0
dependencies {
    modCompileOnly("curse.maven:gtm-extended-features-1604172:9064844")
}
```