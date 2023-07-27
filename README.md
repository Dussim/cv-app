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
  model -- api --> data
  api -- api --> data
  data -- api --> resources
  local -- api --> api
  local -- api --> data
  network -- api --> api
  network -- api --> data
  app -- implementation --> design-system
  app -- implementation --> model
  app -- implementation --> local
  app -- implementation --> network

```