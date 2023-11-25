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
  subgraph easter-eggs
    gym
  end
  gym -- api --> navigation
  gym -- implementation --> api-compose
  gym -- implementation --> ui

```
# :feature:easter-eggs:gym