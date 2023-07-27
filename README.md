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
    data
    design-system
    local
    model
    network
    resources
  end
  model -- api --> api
  api -- api --> data
  local -- api --> api
  local -- api --> resources
  network -- api --> api
  app -- implementation --> design-system
  app -- implementation --> model
  app -- implementation --> local
  app -- implementation --> network

```