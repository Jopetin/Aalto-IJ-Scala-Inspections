# Aalto IntelliJ Scala inspections plugin

This is an IntelliJ IDEA plugin that adds additional live code inspection rules for Scala.

New inspections can be found under the *Aalto* group under the *Scala* section of inspections.
By default they are ranked as warnings and disabled.

### Usage of *Option.get*

Reports any usage of Option.get since it can lead to runtime exceptions.

### Usage of *Option.head* or *Option.last*

Reports any usage of Option.head or Option.last since they too can lead to runtime exceptions.

### Usage of *null*

Reports any usage of *null* since Scala provides the safer *Option* wrapper for describing potentially undetermined values.

### Not using a value returned by a side-effect-free method

Reports cases where side-effect-free methods are used without utilizing the returned value, effectively doing nothing. These methods are recognized from an annotation which can be configured from the inspection settings.

There are two version of this inspection: one reacts to annotated methods whereas the other allows for annoting a class and then reacting to all of its methods.

Note that this inspection is still a work in progress and fails to recognize certain types of usage (e.g. inside loops).



