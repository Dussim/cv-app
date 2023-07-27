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
    network
  end
  network -- api --> api
  network -- api --> data

```
# :core:network

This module contains network components and data sources implementations