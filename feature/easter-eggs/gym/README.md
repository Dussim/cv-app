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
# :feature:easter-eggs:gym