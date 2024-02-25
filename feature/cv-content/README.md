# Dependency Diagram

```mermaid
%%{
  init: {
    'theme': 'dark'
  }
}%%

graph TB
  subgraph core
    api-compose
    navigation
  end
  subgraph feature
    cv-content
  end
  cv-content -- api --> navigation
  cv-content -- implementation --> api-compose
  cv-content -- implementation --> ui
```
# :feature:splash-screen

This module contains implementation of splash screen feature. It doesn't do anything on its own, but it is used by the app module while app
is loading.
It also uses this time to download all necessary data from the server so that next screen will open with all data ready.