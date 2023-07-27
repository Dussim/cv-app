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
    local
    resources
  end
  local -- api --> api
  local -- api --> resources

```
# :core:local

This module contains local/static data sources implementations