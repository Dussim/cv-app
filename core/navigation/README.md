# Dependency Diagram

```mermaid
%%{
  init: {
    'theme': 'dark'
  }
}%%

graph TB
  subgraph core
    data
    navigation
  end
  navigation -- api --> data
```
# :core:navigation

[Voyager](https://voyager.adriel.cafe/navigation/multi-module-navigation) based multi-module navigation.