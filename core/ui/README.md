# Dependency Diagram

```mermaid
%%{
  init: {
    'theme': 'dark'
  }
}%%

graph TB
  subgraph core
    data
    design-system
    resources
  end
  ui -- api --> data
  ui -- api --> design-system
  ui -- api --> resources

```
# :core:ui

This module hosts reusable UI components that are used across the application.