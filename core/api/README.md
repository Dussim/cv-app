# Dependency Diagram

```mermaid
%%{
  init: {
    'theme': 'dark'
  }
}%%

graph TB
  :core:api -- api --> :core:data

classDef android-library fill:#3BD482,stroke:#fff,stroke-width:2px,color:#fff;
classDef unknown fill:#676767,stroke:#fff,stroke-width:2px,color:#fff;
class :core:api android-library
class :core:data unknown

```
# :core:api

This is somewhat of a cheat, this module is place for all the interfaces and components etc. that should be shared across the whole project.

This comes with penalty of gradle potentially having to recompile the whole project when this module is changed.