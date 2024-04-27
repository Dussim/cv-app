# Dependency Diagram

```mermaid
%%{
  init: {
    'theme': 'dark'
  }
}%%

graph TB
  :feature:easter-eggs:gym -- api --> :core:navigation
  :feature:easter-eggs:gym -- implementation --> :core:api-compose
  :feature:easter-eggs:gym -- implementation --> :core:ui

classDef android-library fill:#3BD482,stroke:#fff,stroke-width:2px,color:#fff;
classDef unknown fill:#676767,stroke:#fff,stroke-width:2px,color:#fff;
class :feature:easter-eggs:gym android-library
class :core:navigation unknown
class :core:api-compose unknown
class :core:ui unknown

```
# :feature:easter-eggs:gym