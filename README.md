# Dependency Diagram

```mermaid
%%{
  init: {
    'theme': 'dark'
  }
}%%

graph TB

  subgraph core
    api
    api-compose
    data
    design-system
    local
    model
    navigation
    network
    resources
  end
  subgraph feature
    cv-content
    splash-screen
  end
  model -- api --> api
  navigation -- api --> data
  api -- api --> data
  network -- api --> api
  api-compose -- api --> api
  splash-screen -- api --> navigation
  splash-screen -- implementation --> api-compose
  local -- api --> api
  local -- api --> resources
  cv-content -- api --> navigation
  cv-content -- api --> ui
  cv-content -- implementation --> design-system
  cv-content -- implementation --> api-compose
  cv-content -- implementation --> resources
  ui -- api --> data
  ui -- api --> design-system
  ui -- api --> resources
  app -- implementation --> api-compose
  app -- implementation --> ui
  app -- implementation --> model
  app -- implementation --> local
  app -- implementation --> network
  app -- implementation --> splash-screen
  app -- implementation --> cv-content

```