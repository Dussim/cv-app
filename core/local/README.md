# Dependency Diagram

```mermaid
%%{
  init: {
    'theme': 'dark'
  }
}%%

graph TB
  subgraph :core
    :core:local["local"]
    :core:api["api"]
  end

  :core:local -- api --> :core:api

classDef android-library fill:#3BD482,stroke:#fff,stroke-width:2px,color:#fff;
classDef unknown fill:#676767,stroke:#fff,stroke-width:2px,color:#fff;
class :core:local android-library
class :core:api unknown

```
# :core:local

This module contains local/static data sources implementations