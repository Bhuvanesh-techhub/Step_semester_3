# Category C – Abstraction: Interface, Class vs Interface
## Part B — Quiz Answers

| Q  | Answer | Why |
|----|--------|-----|
| Q1 | **C** | Abstraction hides *how* an operation is done and exposes only *what* it does. |
| Q2 | **C** | An abstract class can never be instantiated directly (it may have zero abstract methods, and it can extend only one class). |
| Q3 | **B** | A concrete subclass must implement every inherited abstract method, otherwise it must itself be declared `abstract` or the code won't compile. |
| Q4 | **C** | `private` is not allowed: a private method can't be seen/overridden by a subclass. (`static` and `final` are also illegal on abstract methods.) |
| Q5 | **C** | Interface fields are implicitly `public static final` (constants). |
| Q6 | **C** | A class can implement any number of interfaces. |
| Q7 | **D** | Interface methods are implicitly `public`, and an implementation cannot reduce visibility, so it must be `public`. |
| Q8 | **C** | Interfaces carry no instance state, so multiple interface implementation avoids the ambiguity of the diamond problem with conflicting inherited data. |
| Q9 | **D** | Conflicting default methods cause a compile-time error until the class overrides the method. |
| Q10 | **B** | Abstract classes can have constructors; they run (via `super(...)`) when a subclass object is created. |

---

## Part C — Concept Questions

### Question 1 — Abstraction in my own words, with an example
Abstraction means exposing only *what* an object can do while hiding *how* it does it, so the user deals with a simple view of a complex thing.

**Example: an ATM.** What the user sees: a screen and a few choices (check balance, withdraw, deposit, change PIN). What is hidden: how the card is authenticated, how the ATM talks to the bank's servers, how the balance is updated in the database, how the cash dispenser counts and releases notes, and how failures/rollbacks are handled. The user doesn't need any of that to withdraw money, and the bank can change its internal systems without changing what the user does. In Java, this is done with abstract classes and interfaces.

### Question 2 — Abstraction vs encapsulation, and how they work together
**Abstraction** is a *design-level* idea: it hides complexity by exposing only the essential behaviour (the "what"). **Encapsulation** is about *data protection*: it bundles fields and methods together and restricts direct access to the data (using `private` fields with getters/setters) so the object's state can't be corrupted from outside. In short: abstraction hides *implementation complexity*; encapsulation hides *data*.

They work together in one class. Take a `BankAccount`: the `balance` field is `private` (encapsulation: nobody can set it to a negative value directly), while the public methods `deposit()`, `withdraw()` and `getBalance()` form a simple interface (abstraction: callers don't know about validation, logging, or transaction rules inside). Encapsulation guards the data; abstraction gives users a small, easy-to-use surface over it, which together keeps the class both safe and simple to use.

### Question 3 — Why an abstract class can have a constructor, and when it runs
Although an abstract class can't be instantiated directly, its subclasses can be, and every subclass object contains the abstract class's part. The constructor exists to **initialise the shared fields** that all subclasses have (e.g. `name` in `Staff`, `units` in `Connection`), so that code is written once instead of in each subclass.

It is executed when a **subclass object is created**: the subclass constructor calls `super(...)` (explicitly or implicitly) as its first step, which runs the abstract class's constructor before the subclass's own constructor body. It is never used to create an object of the abstract class on its own.

### Question 4 — Why an abstract method cannot be `private`, `static` or `final`
- **private:** An abstract method has no body and *must* be overridden by a subclass. A `private` method is invisible to subclasses and therefore can't be overridden, so the two ideas contradict each other.
- **static:** A static method belongs to the class, not to an object, and is not overridden (only hidden), so there is no polymorphic dispatch. An abstract method needs to be overridden per subclass, and a static method with no body could never actually be called.
- **final:** `final` means "this method can't be overridden", while `abstract` means "this method *must* be overridden". Together they would make the method impossible to ever implement.

### Question 5 — The diamond problem and how Java avoids it
The **diamond problem** happens with multiple inheritance of classes: class `D` inherits from `B` and `C`, which both inherit from `A`. If `B` and `C` each override a method (or hold their own copy of a field) from `A`, then `D` cannot tell which version it should inherit. That ambiguity, including conflicting inherited *data*, is the problem.

Java avoids it by allowing a class to **extend only one class**, so a class never inherits two competing implementations or two copies of state. Multiple **interfaces** are allowed because interfaces carry no instance state and (traditionally) no implementation, only method signatures and constants. Since there is nothing conflicting to inherit, there is no ambiguity. For the modern case where two interfaces provide the same `default` method, Java forces the implementing class to **override** it (it may call `InterfaceName.super.method()` to choose one), so the conflict is settled explicitly at compile time.
