# Dependency Diagram

```mermaid
%%{
  init: {
    'theme': 'dark'
  }
}%%

graph TB

  subgraph core
    design-system
    local
    model
    network
  end
  app -- implementation --> design-system
  app -- implementation --> model
  app -- implementation --> local
  app -- implementation --> network

```
# :app

Android Application top module