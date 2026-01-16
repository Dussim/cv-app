# Dependency Diagram

```mermaid
%%{
  init: {
    'theme': 'dark'
  }
}%%

graph TB
  subgraph :core
    :core:navigation["navigation"]
    :core:data["data"]
  end

  :core:navigation -- api --> :core:data

classDef android-library fill:#3BD482,stroke:#fff,stroke-width:2px,color:#fff;
classDef unknown fill:#676767,stroke:#fff,stroke-width:2px,color:#fff;
class :core:navigation android-library
class :core:data unknown

```
# :core:navigation

[Voyager](https://voyager.adriel.cafe/navigation/multi-module-navigation) based multi-module navigation.