# Dependency Diagram

```mermaid
%%{
  init: {
    'theme': 'dark'
  }
}%%

graph TB
  subgraph :core
    :core:ui["ui"]
    :core:data["data"]
    :core:design-system["design-system"]
  end

  :core:ui -- api --> :core:data
  :core:ui -- api --> :core:design-system

classDef android-library fill:#3BD482,stroke:#fff,stroke-width:2px,color:#fff;
classDef unknown fill:#676767,stroke:#fff,stroke-width:2px,color:#fff;
class :core:ui android-library
class :core:data unknown
class :core:design-system unknown

```
# :core:ui

This module hosts reusable UI components that are used across the application.