# Dependency Diagram

```mermaid
%%{
  init: {
    'theme': 'dark'
  }
}%%

graph TB
  :core:api-compose -- api --> :core:api

classDef android-library fill:#3BD482,stroke:#fff,stroke-width:2px,color:#fff;
classDef unknown fill:#676767,stroke:#fff,stroke-width:2px,color:#fff;
class :core:api-compose android-library
class :core:api unknown

```
# :core:api-compose

Extension for the [:core:api](../api/README.md) module that adds compose specific interfaces and components like different `LocalXyz`