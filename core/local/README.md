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
    local
  end
  local -- api --> api
  local -- api --> data

```
# :core:local

This module contains local/static data sources implementations