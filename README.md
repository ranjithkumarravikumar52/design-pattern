# design-pattern

## Prototype

### Overview
* When it's easier to copy an existing object to fully initialize a new one

### Motivation
* Complicated objects aren't designed from scratch. Usually they reiterate existing designs. 
* To make any variations to an object, we usually make a copy(deep-copy) and customize it
* Have to provide cloning convenient (Usually Factory)
* Usually deep-copy becomes problematic when we have a huge arguments or array or composition. This can be solved through various approaches. 
