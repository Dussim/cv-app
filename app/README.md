# Dependency Diagram

```mermaid
%%{
  init: {
    'theme': 'dark'
  }
}%%

graph TB
  :app["app"]
  :baselineProfile["baselineProfile"]
  subgraph :core
    :core:api-compose["api-compose"]
    :core:ui["ui"]
    :core:model["model"]
    :core:local["local"]
    :core:network["network"]
  end
  subgraph :feature
    :feature:splash-screen["splash-screen"]
    :feature:cv-content["cv-content"]
    subgraph :easter-eggs
      :feature:easter-eggs:gym["gym"]
    end
  end

  :app -- baselineProfile --> :baselineProfile
  :app -- implementation --> :core:api-compose
  :app -- implementation --> :core:ui
  :app -- implementation --> :core:model
  :app -- implementation --> :core:local
  :app -- implementation --> :core:network
  :app -- implementation --> :feature:splash-screen
  :app -- implementation --> :feature:cv-content
  :app -- implementation --> :feature:easter-eggs:gym

classDef android-application fill:#2C4162,stroke:#fff,stroke-width:2px,color:#fff;
classDef unknown fill:#676767,stroke:#fff,stroke-width:2px,color:#fff;
class :app android-application
class :baselineProfile unknown
class :core:api-compose unknown
class :core:ui unknown
class :core:model unknown
class :core:local unknown
class :core:network unknown
class :feature:splash-screen unknown
class :feature:cv-content unknown
class :feature:easter-eggs:gym unknown

```
# :app

Android Application top module