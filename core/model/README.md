# Dependency Diagram

```mermaid
%%{
  init: {
    'theme': 'dark'
  }
}%%

graph TB
  :core:model -- api --> :core:api

classDef android-library fill:#3BD482,stroke:#fff,stroke-width:2px,color:#fff;
classDef unknown fill:#676767,stroke:#fff,stroke-width:2px,color:#fff;
class :core:model android-library
class :core:api unknown

```
# :core:model

This is the main domain model module that defines top level domain component. 

This also place where more complicated classes like network first data sources.