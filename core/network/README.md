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
    network
  end
  network -- api --> api

```
# :core:network

This module contains network components and data sources implementations