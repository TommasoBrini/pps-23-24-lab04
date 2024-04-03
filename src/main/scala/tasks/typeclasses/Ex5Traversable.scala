package u04lab
import u03.Sequences.* 
import Sequence.*
import u03.Optionals.Optional
import Optional.*
import u04lab.Ex5Traversable.logAll

/*  Exercise 5: 
 *  - Generalise by ad-hoc polymorphism logAll, such that:
 *  -- it can be called on Sequences but also on Optional, or others... 
 *  -- it does not necessarily call log, but any function with analogous type
 *  - Hint: introduce a type class Traversable[T[_]]], capturing the ability of calling a
 *    "consumer function" on all elements (with type A) of a datastructure T[A] 
 *    Note Traversable is a 2-kinded trait (similar to Filterable, or Monad)
 *  - Write givens for Traversable[Optional] and Traversable[Sequence]
 *  - Show you can use the generalisation of logAll to:
 *  -- log all elements of an Optional, or of a Traversable
 *  -- println(_) all elements of an Optional, or of a Traversable
 */

object Ex5Traversable:

  def log[A](a: A): Unit = println("The next element is: "+a)
  
  trait Traversable[T[_]]:
    def logAll[A](t: T[A]): Unit

  def logAll[B, A[_]: Traversable](t: A[B]): Unit = summon[Traversable[A]].logAll(t)

  given Traversable[Optional] with
    def logAll[A](o: Optional[A]): Unit = o match
      case Just(a) => log(a)
      case _ => 

  given Traversable[Sequence] with
    def logAll[A](t: Sequence[A]): Unit = t match
      case Cons(head, tail) => log(head); logAll(tail)
      case _ => 
      

@main def testTraversable =
  val seq = Cons(10, Cons(20, Cons(30, Nil())))
  val opt = Just(10)

  logAll(seq)
  logAll(opt)

  

  
