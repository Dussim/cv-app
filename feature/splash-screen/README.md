# Dependency Diagram

```mermaid
%%{
  init: {
    'theme': 'dark'
  }
}%%

graph TB
  subgraph :feature
    :feature:splash-screen["splash-screen"]
  end
  subgraph :core
    :core:navigation["navigation"]
    :core:api-compose["api-compose"]
  end

  :feature:splash-screen -- api --> :core:navigation
  :feature:splash-screen -- implementation --> :core:api-compose

classDef android-library fill:#3BD482,stroke:#fff,stroke-width:2px,color:#fff;
classDef unknown fill:#676767,stroke:#fff,stroke-width:2px,color:#fff;
class :feature:splash-screen android-library
class :core:navigation unknown
class :core:api-compose unknown

```
# :feature:splash-screen

This module contains implementation of splash screen feature. It doesn't do anything on its own, but it is used by the
app module while app
is loading.
It also uses this time to download all necessary data from the server so that next screen will open with all data ready.