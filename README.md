## Import

[![Tag](https://jitpack.io/v/justADeni/LogicallyAPI.svg?style=flat-square)](https://jitpack.io/#justADeni/LogicallyAPI)

### Gradle

```kotlin
repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    compileOnly("com.github.justADeni:LogicallyAPI:Tag")
}
```

### Maven
```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```
```xml
<dependency>
    <groupId>com.github.justADeni</groupId>
    <artifactId>LogicallyAPI</artifactId>
    <version>Tag</version>
    <scope>provided</scope>
</dependency>
```

## API

Call static method `LogicallyAPI.get()` to get an instance.
It offers following methods:

```java
/**
* Checks whether tree chopping is enabled for the given player.
*
* @param player the player to check
* @return {@code true} if tree chopping is enabled, {@code false} otherwise
*/
boolean isEnabled(Player player);

/**
* Enables or disables tree chopping for the given player.
*
* @param player the player to modify
* @param state {@code true} to enable chopping, {@code false} to disable it
*/
void set(Player player, boolean state);
```

## StartChopTreeEvent

- asynchronous
- cancelling the event will lead to the block breaking as if the plugin wasn't installed
- using thread-safe Set implementation
- following can be mutated:
  - `Set<Block> logs`
  - `Set<Block> logs`
  - `Vector3f axis` - normalized!

Here's the full list of properties: 

```java
/**
* @param logs            the detected log blocks
* @param leaves          the detected leaf blocks
* @param axis            the main tree axis vector
* @param player          the player who started chopping
* @param root            the base block of the tree
* @param world           the world where the event occurred
* @param height          the total height of the tree
* @param logMaterials    the log materials in this tree
* @param leavesMaterials the leaf materials in this tree
*/
```

## DropItemsEvent

- asynchronous
- cancelling the event will lead to the tree dropping nothing
- using thread-safe List implementation
- following can be mutated:
  - `List<Drop> drops` where `Drop` is a record class consisting of `Location` and `ItemStack`

Here's the full list of properties:
```java
/**
* @param dropList        list of items to be dropped
* @param player          the player who started chopping
* @param world           the world where the event occurred
*/
```
 
