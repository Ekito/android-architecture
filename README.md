# Android Architecture Blueprints - MVP + Kotlin + Koin

## About

This version of the app is called todo-mvp-kotlin-koin, and provides a foundation for other kotlin samples in this project. The sample aims to:

* Provide a basic [Model-View-Presenter](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter) (MVP) architecture without using any architectural frameworks.
* Act as a reference point for comparing and contrasting the other kotlin samples in this project.
* Leverage idiomatic kotlin to improve readability and limit verbosity, as compared to the TODO-MVP java project.
* Simple use of Koin for dependency injection

This sample is a work in progress.

## Dependencies
*  Kotlin stdlib
*  kotlin-android plugin
*  Koin framework

## Koin

KOIN is a simple (but powerful) dependency injection framework for Android. It uses Kotlin and its functional power to get things done! No proxy/CGLib, no code generation, no introspection. Just functional Kotlin and DSL magic ;)

KOIN is a very small library, that aims to be as simple as possible and let's you write dependency injection in a breath.

### Start your context

To start Koin, you just need to call ‘startAndroidContext()’ function. No need of interface or any other special class. 

```kotlin

class TodoApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Start Koin
        startAndroidContext(this, todoAppModules())
    }
}

```

### Declare your dependencies

To declare our components, we just need to make a module class (extends AndroidModule class), and use the Koin DSL to declare our components.

```kotlin

class TodoAppModule : AndroidModule() {
    override fun context() = applicationContext {
        context(name = "Tasks") {
            provide { TasksFragment() }
            provide { TasksPresenter(get()) } bind TasksContract.Presenter::class
        }

        //...
    }
}

```

Koin DSL is composed of only 5 keywords :

* applicationContext — declare your root application context
* context —  declare a sub context of your application (with a given name)
* provide —  declare a component (with lambda expression)
* bind — declare an assignable class/interface to the provided component
* get — retrieve a component, for provided definition function

The function provided by components can use the “get” function, to lazily resolve dependency needed for your component constructor.

### Just make dependency injection

To retrieve your declared components with Koin, you can inject: 

* into Android classes with “inject()” function (TasksActivity, TasksFragment) 
* into class constructors (TasksPresenter, TasksRepository)

```kotlin
class TasksActivity : AppCompatActivity() {

    private val tasksPresenter: TasksPresenter by inject()
    private val tasksFragment: TasksFragment by inject()

}
```

```kotlin
class TasksFragment : ContextAwareFragment(), TasksContract.View {

    override val presenter by inject<TasksContract.Presenter>()
}

```

```kotlin
class TasksPresenter(private val tasksRepository: TasksRepository)
    : TasksContract.Presenter { /.../ }
```

### Lifecyle & context management

Koin modules make you describe an application context and sub contexts. Those sub contexts can be dropped to suit Android components lifecycle. For the given context:

```kotlin
class TodoAppModule : AndroidModule() {
    override fun context() = applicationContext {
        context(name = "Tasks") { ... }
    }
}
```

Components from this context will be dropped with calling `releaseContex("Tasks")`

To help automate this, we can use `ContextAwareFragment`:

```kotlin
abstract class ContextAwareFragment : Fragment() {
    /**
     * Koin context name
     */
    abstract val contextName: String

    override fun onPause() {
        releaseContext(contextName)
        super.onPause()
    }
}
```

Here you we can directly use a context aware Fragment:

```Kotlin
class TasksFragment : ContextAwareFragment(), TasksContract.View {
    // associated context
    override val contextName: String = "Tasks"
}
```

On `TasksFragment.onPause()`context "Tasks" will be released.


### Dry Run

You can check your Koin configuration at any time:

```kotlin
/**
 * Test Dry run / Koin Context
 */
class DryRunTest {
    @Test
    fun todoAppModuleDryRun() {
        Koin()
                // mock Android context
                .init(Mockito.mock(Application::class.java))
                // bootstrap with default values
                .properties(
                        mapOf(TaskDetailActivity.EXTRA_TASK_ID to "",
                                AddEditTaskFragment.ARGUMENT_EDIT_TASK_ID to "",
                                AddEditTaskActivity.SHOULD_LOAD_DATA_FROM_REPO_KEY to false))
                .build(todoAppModules()).dryRun()
    }
}
```


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
