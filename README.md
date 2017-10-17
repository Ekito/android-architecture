# todo-mvp-kotlin

This version of the app is called todo-mvp-kotlin, and provides a foundation for other kotlin samples in this project. The sample aims to:

* Provide a basic [Model-View-Presenter](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter) (MVP) architecture without using any architectural frameworks.
* Act as a reference point for comparing and contrasting the other kotlin samples in this project.
* Leverage idiomatic kotlin to improve readability and limit verbosity, as compared to the TODO-MVP java project.
* Simple use of Koin for dependency injection

This sample is a work in progress.

# Dependencies
*  Kotlin stdlib
*  kotlin-android plugin
*  Latest Koin framework

## Features

### Complexity - understandability

Medium: If you already understand TODO-MVP (Java), you need to [learn](http://kotlinlang.org/docs/reference/) the kotlin language.

### Testability

#### Unit testing

Same as TODO-MVP.

#### Integration testing

Same as TODO-MVP.

### Code metrics

Files were converted 1:1 from TODO-MVP's Java code.

```
-------------------------------------------------------------------------------
Language                     files          blank        comment           code
-------------------------------------------------------------------------------
Kotlin                          48            907           1539           3008 (3450 in MVP)
XML                             34             95            338            816
-------------------------------------------------------------------------------
SUM:                            82           1002           1977           3824
-------------------------------------------------------------------------------
```
### Maintainability

#### Ease of amending or adding a feature

Same as TODO-MVP.

#### Learning cost

Medium, if you are unfamiliar with kotlin.
