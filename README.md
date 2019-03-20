# design-pattern

## Prototype

### Overview
* When it's easier to copy an existing object to fully initialize a new one

### Motivation
* Complicated objects aren't designed from scratch. Usually they reiterate existing designs. 
* To make any variations to an object, we usually make a copy(deep-copy) and customize it
* Have to provide cloning convenient (Usually Factory)
* Usually deep-copy becomes problematic when we have a huge arguments or array or composition. This can be solved through various approaches. 

### Summary
* Partially/fully construct an object and store it somewhere to replicate it in future
* How do we clone? 
    * [Why cloneable interface is bad?](https://www.artima.com/intv/bloch13.html)
    * Custom Deep-copy like copy-constructors (usually painful to follow along the entire object hierarchy)
    * Take the object graph and serialize and deserialize(Serialization.roundtrip())
* Once cloning is done, customize and use it
