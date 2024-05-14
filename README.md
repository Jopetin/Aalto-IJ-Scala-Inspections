# Aalto IntelliJ Scala inspections plugin

This is an IntelliJ IDEA plugin that adds additional live code inspection rules for Scala.

New inspections can be found under the *Aalto* group under the *Scala* section of inspections.
By default they are ranked as warnings and are disabled.

### Usage of *Option.get*

Reports any usage of Option.get since it can lead to runtime exceptions.

### Usage of *Option.head* or *Option.last*

Reports any usage of Option.head or Option.last since they too can lead to runtime exceptions.

### Usage of *null*

Reports any usage of *null* since Scala provides the safer *Option* wrapper for describing potentially undetermined values.



